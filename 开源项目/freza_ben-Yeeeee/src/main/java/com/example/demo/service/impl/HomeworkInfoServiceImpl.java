package com.example.demo.service.impl;

import com.example.demo.bo.*;
import com.example.demo.entity.HomeworkInfo;
import com.example.demo.enums.HomeworkType;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.mapper.*;
import com.example.demo.service.HomeworkInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.util.TimeUtil;
import com.example.demo.vo.PageVO;
import com.example.demo.vo.StudentHomeworkVO;
import com.example.demo.vo.TeacherHomeworkVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-28
 */
@Service
public class HomeworkInfoServiceImpl extends ServiceImpl<HomeworkInfoMapper, HomeworkInfo> implements HomeworkInfoService {

    @Resource
    HomeworkInfoMapper mapper;
    @Resource
    CourseInfoMapper courseInfoMapper;
    @Resource
    GroupInfoMapper groupInfoMapper;
    @Resource
    UserInfoMapper userInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_UNCOMMITTED)
    public synchronized Boolean addHomework(AddModifyHomeworkBO homeworkBO, Integer operatorId) throws ValidationFailException {
        if (homeworkBO == null) {
            throw new ValidationFailException("未获取到输入数据");
        }
        if (userInfoMapper.isUserExist(operatorId, 2) < 1) {
            throw new ValidationFailException("操作员权限不足");
        }
        if (homeworkBO.getCourseId() == null) {
            throw new ValidationFailException("未指定课程");
        }
        if (homeworkBO.getGroupId() == null) {
            throw new ValidationFailException("未指定小组");
        }
        if (homeworkBO.getHomeworkExpiry() == null) {
            throw new ValidationFailException("未指定截止时间");
        }
        if (homeworkBO.getHomeworkTitle() == null) {
            throw new ValidationFailException("未输入作业主题");
        }
        if (homeworkBO.getHomeworkMain() == null) {
            throw new ValidationFailException("作业内容不能为空");
        }
        if (homeworkBO.getHomeworkType() == null) {
            throw new ValidationFailException("未指定作业类型");
        }
        if (courseInfoMapper.isCourseExistById(homeworkBO.getCourseId()) < 1) {
            throw new ValidationFailException("课程不可用");
        }
        if (courseInfoMapper.isTeacherTeachCourseById(homeworkBO.getCourseId(), operatorId) < 1) {
            throw new ValidationFailException("没有添加该课程作业的权限");
        }
        int permission = courseInfoMapper.isLeaderTeacher(homeworkBO.getCourseId(), operatorId) >= 1 ? 2 : 1;
        if (!HomeworkType.permissionConfirm(homeworkBO.getHomeworkType(), permission)) {
            throw new ValidationFailException("没有下发该类型作业权限");
        }
        Date expiry;
        try {
            expiry = TimeUtil.getDateFromStringYMDHMS(homeworkBO.getHomeworkExpiry());
        } catch (ParseException e) {
            throw new ValidationFailException("错误的时间格式");
        }
        if (expiry == null) {
            return false;
        }
        if (expiry.getTime() <= new Date().getTime()) {
            throw new ValidationFailException("解锁成就：尚未开始，便已结束（截止时间小于当前时间）");
        }
        if (expiry.getTime() - new Date().getTime() < 60) {
            throw new ValidationFailException("解锁成就：生死时速（截止时间小于一分钟）");
        }
        if (homeworkBO.getGroupId().length == 0) {
            throw new ValidationFailException("小组不能为空");
        } else {
            if (mapper.addHomework(homeworkBO, new Date(), operatorId) < 1) {
                throw new ValidationFailException("插入失败");
            }
            Integer id = mapper.getInsertId();
            homeworkBO.setHomeworkId(id);
            if (homeworkBO.getStudentId().length != 0) {
                for (Integer groupId : homeworkBO.getGroupId()) {

                    if (groupInfoMapper.getGroupLeader(groupId) == null || groupInfoMapper.getGroupLeader(groupId) < 1) {
                        throw new ValidationFailException("小组还未指定小组长，指定小组长后再试试吧");
                    }
                    boolean t = false;
                    for (Integer item : homeworkBO.getStudentId()) {
                        if (groupInfoMapper.isGroupLeader(groupId, item) > 0) {
                            t = true;
                        }
                    }
                    if (!t) {
                        throw new ValidationFailException("小组长必须在选择的范围内");
                    }
                }
                if (homeworkBO.getHomeworkType() > 1) {
                    for (Integer groupId : homeworkBO.getGroupId()) {
                        Integer leaderId = groupInfoMapper.getGroupLeaderById(groupId);
                        if (leaderId == null) {
                            throw new ValidationFailException("小组长不存在");
                        }
                        if (mapper.addStudentHomework(leaderId, homeworkBO.getHomeworkId(), groupId) < 1) {
                            throw new ValidationFailException("添加作业至学生失败");
                        }
                    }
                } else {
                    for (Integer item : homeworkBO.getStudentId()) {
                        if (userInfoMapper.isUserExist(item, 1) < 1) {
                            throw new ValidationFailException("指定的学生不可用");
                        }
                        if (courseInfoMapper.isStudentInGroup(item, homeworkBO.getGroupId()) < 1) {
                            throw new ValidationFailException("学生不在这些小组内");
                        }
                        if (courseInfoMapper.isStudentInCourse(item, homeworkBO.getCourseId()) < 1) {
                            throw new ValidationFailException("学生不在课程里");
                        }
                        Integer groupId = groupInfoMapper.getStudentGroupId(item);
                        if (mapper.addStudentHomework(item, homeworkBO.getHomeworkId(), groupId) < 1) {
                            throw new ValidationFailException("添加作业至学生失败");
                        }
                    }
                }
            } else {
                if (homeworkBO.getHomeworkType() > 1) {
                    for (Integer groupId : homeworkBO.getGroupId()) {
                        Integer leaderId = groupInfoMapper.getGroupLeaderById(groupId);
                        if (leaderId == null) {
                            throw new ValidationFailException("小组长不存在");
                        }
                        if (mapper.addStudentHomework(leaderId, homeworkBO.getHomeworkId(), groupId) < 1) {
                            throw new ValidationFailException("添加作业至学生失败");
                        }
                    }
                } else {
                    for (Integer groupId : homeworkBO.getGroupId()) {
                        Integer[] studentId = groupInfoMapper.listGetGroupMembersId(groupId);
                        for (Integer item : studentId) {
                            if (courseInfoMapper.isStudentInCourse(item, homeworkBO.getCourseId()) < 1) {
                                throw new ValidationFailException("学生不在课程里");
                            }
                            if (mapper.addStudentHomework(item, homeworkBO.getHomeworkId(), groupId) < 1) {
                                throw new ValidationFailException("添加作业至学生失败");
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    @Override
    public PageVO<List<StudentHomeworkVO>> listGetStudentHomework(GetHomeworkBO homeworkBO, Integer operatorId, Integer userRole) throws ValidationFailException {
        if (homeworkBO == null) {
            throw new ValidationFailException("未能获取到参数");
        }
        if (homeworkBO.getCourseId() != null) {
            if (courseInfoMapper.isCourseExistById(homeworkBO.getCourseId()) < 1) {
                throw new ValidationFailException("课程不存在");
            }
        }

        if (homeworkBO.getDateStart() != null) {
            try {
                TimeUtil.getDateFromStringYMDHMS(homeworkBO.getDateStart());
            } catch (ParseException e) {
                throw new ValidationFailException("时间格式错误");
            }
        }
        if (homeworkBO.getHomeworkType() != null) {
            if (homeworkBO.getHomeworkType() <= 0 || homeworkBO.getHomeworkType() >= 4) {
                throw new ValidationFailException("错误的作业状态输入");
            }
        }
        if (homeworkBO.getDateEnd() != null) {
            try {
                TimeUtil.getDateFromStringYMDHMS(homeworkBO.getDateEnd());
            } catch (ParseException e) {
                throw new ValidationFailException("时间格式错误");
            }
        }
        if (homeworkBO.getFinishDate() != null) {
            try {
                TimeUtil.getDateFromStringYMDHMS(homeworkBO.getFinishDate());
            } catch (ParseException e) {
                throw new ValidationFailException("时间格式错误");
            }
        }
        if (homeworkBO.getHomeworkType() != null) {
            if (!(homeworkBO.getHomeworkType() > 0 && homeworkBO.getHomeworkType() <= 3)) {
                throw new ValidationFailException("错误的作业类型");
            }
        }
        PageVO<List<StudentHomeworkVO>> result;
        List<StudentHomeworkVO> list = mapper.listGetStudentHomework(homeworkBO, operatorId);
        result = new PageVO<>(list);
        result.setPageNo(homeworkBO.getPage());
        result.setPageSize(homeworkBO.getPageSize());
        result.setRecordCount(list.size());
        result.setTotalCount(mapper.listGetStudentHomeworkCount(homeworkBO, operatorId));
        if (result.getTotalCount() == null) {
            result.setTotalPage(0);
        } else {
            result.setTotalPage(result.getTotalCount() % result.getPageSize() == 0 ?
                    result.getTotalCount() / result.getPageSize()
                    : result.getTotalCount() / result.getPageSize() + 1);
        }
        return result;
    }

    @Override
    public PageVO<List<TeacherHomeworkVO>> listGetTeacherHomework(GetHomeworkBO homeworkBO, Integer operatorId, Integer userRole) throws ValidationFailException {
        if (homeworkBO == null) {
            throw new ValidationFailException("未能获取到参数");
        }
        if (homeworkBO.getCourseId() != null) {
            if (courseInfoMapper.isCourseExistById(homeworkBO.getCourseId()) < 1) {
                throw new ValidationFailException("课程不存在");
            }
        }

        if (homeworkBO.getDateStart() != null) {
            try {
                TimeUtil.getDateFromStringYMDHMS(homeworkBO.getDateStart());
            } catch (ParseException e) {
                throw new ValidationFailException("时间格式错误");
            }
        }
        if (homeworkBO.getHomeworkType() != null) {
            if (homeworkBO.getHomeworkType() <= 0 || homeworkBO.getHomeworkType() >= 4) {
                throw new ValidationFailException("错误的作业状态输入");
            }
        }
        if (homeworkBO.getDateEnd() != null) {
            try {
                TimeUtil.getDateFromStringYMDHMS(homeworkBO.getDateEnd());
            } catch (ParseException e) {
                throw new ValidationFailException("时间格式错误");
            }
        }
        if (homeworkBO.getFinishDate() != null) {
            try {
                TimeUtil.getDateFromStringYMDHMS(homeworkBO.getFinishDate());
            } catch (ParseException e) {
                throw new ValidationFailException("时间格式错误");
            }
        }
        if (homeworkBO.getGroupId() != null) {
            if (groupInfoMapper.isGroupExistById(homeworkBO.getGroupId()) < 1) {
                throw new ValidationFailException("小组不存在");
            }
        }
        if (homeworkBO.getHomeworkType() != null) {
            if (!(homeworkBO.getHomeworkType() > 0 && homeworkBO.getHomeworkType() <= 3)) {
                throw new ValidationFailException("错误的作业类型");
            }
        }
        PageVO<List<TeacherHomeworkVO>> result;
        List<TeacherHomeworkVO> list = mapper.listGetTeacherHomework(homeworkBO, operatorId);
        result = new PageVO<>(list);
        result.setPageNo(homeworkBO.getPage());
        result.setPageSize(homeworkBO.getPageSize());
        result.setRecordCount(list.size());
        result.setTotalCount(mapper.listGetTeacherHomeworkCount(homeworkBO, operatorId));
        if (result.getTotalCount() == null) {
            result.setTotalPage(0);
        } else {
            result.setTotalPage(result.getTotalCount() % result.getPageSize() == 0 ?
                    result.getTotalCount() / result.getPageSize()
                    : result.getTotalCount() / result.getPageSize() + 1);
        }
        return result;
    }

    @Override
    public Boolean delHomework(Integer id, Integer operatorId) throws ValidationFailException {
        if (id == null || operatorId == null) {
            throw new ValidationFailException("未能获取参数");
        }
        Integer teacherID = mapper.isHomeworkExistById(id);
        if (teacherID == null) {
            throw new ValidationFailException("作业不存在");
        }
        if (!teacherID.equals(operatorId)) {
            throw new ValidationFailException("权限不足");
        }

        return mapper.delHomeworkByID(id, operatorId, new Date()) >= 1;
    }

    @Override
    public Boolean commitHomework(CommitHomeworkBO homeworkBO, Integer operatorId) throws ValidationFailException, ParseException {
        if (operatorId == null) {
            throw new ValidationFailException("非法访问");
        }
        if (userInfoMapper.isUserExist(operatorId, 1) < 1) {
            throw new ValidationFailException("未知用户,非法访问");
        }
        if (homeworkBO.getHomeworkId() == null) {
            throw new ValidationFailException("提交的目标未指定");
        }
        if (mapper.isHomeworkExistByStudentIdAndHomeworkId(homeworkBO.getHomeworkId(), operatorId) < 1) {
            throw new ValidationFailException("提交目标不存在");
        }
        Date expiryTime = TimeUtil.getDateFromStringYMDHMS(mapper.getHomeworkExpiryTime(homeworkBO.getHomeworkId()));
        if (expiryTime == null) {
            throw new ValidationFailException("截止时间获取失败");
        }
        Date date = new Date();
        if (date.getTime() > expiryTime.getTime()) {
            throw new ValidationFailException("作业已经截止");
        }
        return mapper.commitHomework(homeworkBO, operatorId, date) > 0;
    }

    @Override
    public PageVO<List<StudentHomeworkVO>> listGetTeacherCorrectHomework(GetCorrectHomeworkBO homeworkBO, Integer operatorId) throws ValidationFailException {
        if (homeworkBO == null) {
            throw new ValidationFailException("未能获取到参数");
        }
        if (homeworkBO.getCourseId() != null) {
            if (courseInfoMapper.isCourseExistById(homeworkBO.getCourseId()) < 1) {
                throw new ValidationFailException("课程不存在");
            }
        }

        if (homeworkBO.getDateStart() != null) {
            try {
                TimeUtil.getDateFromStringYMDHMS(homeworkBO.getDateStart());
            } catch (ParseException e) {
                throw new ValidationFailException("时间格式错误");
            }
        }
        if (homeworkBO.getHomeworkType() != null) {
            if (homeworkBO.getHomeworkType() <= 1 || homeworkBO.getHomeworkType() >= 4) {
                throw new ValidationFailException("错误的作业状态输入");
            }
        }
        if (homeworkBO.getDateEnd() != null) {
            try {
                TimeUtil.getDateFromStringYMDHMS(homeworkBO.getDateEnd());
            } catch (ParseException e) {
                throw new ValidationFailException("时间格式错误");
            }
        }
        if (homeworkBO.getFinishDate() != null) {
            try {
                TimeUtil.getDateFromStringYMDHMS(homeworkBO.getFinishDate());
            } catch (ParseException e) {
                throw new ValidationFailException("时间格式错误");
            }
        }
        if (homeworkBO.getGroupId() != null) {
            if (groupInfoMapper.isGroupExistById(homeworkBO.getGroupId()) < 1) {
                throw new ValidationFailException("小组不存在");
            }
        }
        if (homeworkBO.getHomeworkType() != null) {
            if (!(homeworkBO.getHomeworkType() > 0 && homeworkBO.getHomeworkType() <= 3)) {
                throw new ValidationFailException("错误的作业类型");
            }
        }
        PageVO<List<StudentHomeworkVO>> result;
        List<StudentHomeworkVO> list = mapper.listGetCorrectHomework(homeworkBO, operatorId);
        result = new PageVO<>(list);
        result.setPageNo(homeworkBO.getPage());
        result.setPageSize(homeworkBO.getPageSize());
        result.setRecordCount(list.size());
        result.setTotalCount(mapper.listGetCorrectHomeworkCount(homeworkBO, operatorId));
        if (result.getTotalCount() == null) {
            result.setTotalPage(0);
        } else {
            result.setTotalPage(result.getTotalCount() % result.getPageSize() == 0 ?
                    result.getTotalCount() / result.getPageSize()
                    : result.getTotalCount() / result.getPageSize() + 1);
        }
        return result;
    }

    @Override
    public Boolean correctHomework(CorrectHomeworkBO homeworkBO, Integer operatorId) throws ValidationFailException {
        if (homeworkBO == null) {
            throw new ValidationFailException("未获取到参数");
        }
        if (homeworkBO.getHomeworkScore() == null) {
            throw new ValidationFailException("得分不能为空");
        }
        if (homeworkBO.getId() == null) {
            throw new ValidationFailException("未指定学生答案");
        }
        if (homeworkBO.getHomeworkId() == null) {
            throw new ValidationFailException("未指定作业");
        }
        if (mapper.getHomeworkStatusById(homeworkBO.getId()) != 2) {
            throw new ValidationFailException("该作业不能评审");
        }
        if (homeworkBO.getHomeworkScore() < 0 || homeworkBO.getHomeworkScore() > 100) {
            throw new ValidationFailException("离谱的分数");
        }
        Integer teacherID = mapper.isHomeworkExistById(homeworkBO.getHomeworkId());
        if (teacherID == null) {
            throw new ValidationFailException("作业不存在");
        }
        if (!teacherID.equals(operatorId)) {
            throw new ValidationFailException("权限不足,请不要审批其他老师布置的作业");
        }
        return mapper.correctHomework(homeworkBO, operatorId) > 0;
    }


}
