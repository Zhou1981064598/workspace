package com.example.demo.service.impl;

import com.example.demo.bo.AddModifyCourseBO;
import com.example.demo.bo.AddStudentIntoCourseBO;
import com.example.demo.bo.GetCourseBO;
import com.example.demo.entity.CourseInfo;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.mapper.CourseInfoMapper;
import com.example.demo.mapper.GroupInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.CourseInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.util.RegExpValidatorUtils;
import com.example.demo.util.TimeUtil;
import com.example.demo.vo.CourseVO;
import com.example.demo.vo.GroupVO;
import com.example.demo.vo.PageVO;
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
 * @since 2021-07-26
 */
@Service
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoMapper, CourseInfo> implements CourseInfoService {

    @Resource
    CourseInfoMapper mapper;
    @Resource
    UserInfoMapper userInfoMapper;
    @Resource
    GroupInfoMapper groupInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_UNCOMMITTED)
    public Boolean addCourse(AddModifyCourseBO course, Integer operatorId) throws Exception {
        if (course == null) {
            throw new ValidationFailException("未能获取到输入信息");
        }
        if (course.getCourseDate() == null) {
            throw new ValidationFailException("课程开始时间不能为空");
        }
        if (course.getCourseClassroom() == null) {
            throw new ValidationFailException("课程教室不能为空，总要挑块地吧");
        }
        if (course.getCourseName() == null) {
            throw new ValidationFailException("说真的，给你的课程取个名字吧，不要传空过来了");
        }
        if (course.getCourseSignTime() == null) {
            throw new ValidationFailException("解锁成就：时候未到（开始时间为空）");
        }
        if (course.getCourseOutTime() == null) {
            throw new ValidationFailException("解锁成就：永不停止（结束时间为空）");
        }
        if (course.getTeacherId() == null) {
            throw new ValidationFailException("解锁成就：赛博教室2077（无人授课）");
        }
        if (course.getLeaderTeacher() == null) {
            throw new ValidationFailException("解锁成就：群龙无主（缺少责任老师）");
        }
        String date = course.getCourseDate();
        long courseDate;
        long courseSign;
        long courseOut;
        try {
            courseDate = TimeUtil.changeStringToDateYMD(date).getTime();
            courseSign = TimeUtil.changeStringToDateHM(course.getCourseSignTime()).getTime();
            courseOut = TimeUtil.changeStringToDateHM(course.getCourseOutTime()).getTime();
        } catch (ParseException e) {
            throw new ValidationFailException("是看不懂的时间格式啦，换一种试试吧");
        }
        if (courseDate < new Date().getTime()) {
            throw new ValidationFailException("设置的时间太近啦，预留多一点时间再试试吧");
        }
        if (courseOut - courseSign <= 2700) {
            if (courseOut - courseSign <= 0) {
                throw new ValidationFailException("喂喂，这时间设置的完全不对嘛,结束时间怎么能小于开始时间呢");
            }
            throw new ValidationFailException("一节课的时间也太少了吧，延长到45分钟再试试吧");
        }
        if (userInfoMapper.isUserExist(course.getLeaderTeacher(), 2) < 1) {
            throw new ValidationFailException("可不能乱选个不存在的责任教师哦");
        }
        boolean t = false;
        for (Integer teacherId : course.getTeacherId()) {
            if (course.getLeaderTeacher().equals(teacherId)) {
                t = true;
            }
            if (userInfoMapper.isUserExist(teacherId, 2) < 1) {
                throw new ValidationFailException("实训教师课不能乱选不存在的哦");
            }
        }
        if (!t) {
            throw new ValidationFailException("责任教师必须是实训教师中的一员哦");
        }
        if (mapper.isCourseTimeEnable(course.getCourseDate(), course.getCourseSignTime(), course.getCourseOutTime(), course.getCourseClassroom()) > 0) {
            throw new ValidationFailException("来晚了一步，教室已经被占用了");
        }
        if (mapper.addCourse(course, new Date(), operatorId) < 1) {
            throw new Exception("插入失败");
        }
        Integer courseId = mapper.getCourseId(course.getCourseDate(), course.getCourseSignTime(), course.getCourseOutTime(), course.getCourseClassroom());
        if (courseId == null) {
            throw new Exception("操作失败");
        }
        course.setCourseId(courseId);
        for (int item : course.getTeacherId()) {
            if (mapper.addTeacherIntoTeacherCourse(item, course.getCourseId()) < 1) {
                throw new Exception("插入实训教师失败");
            }
        }
        return true;
    }

    @Override
    public PageVO<List<CourseVO>> listGetCourseInfo(GetCourseBO getCourseBO) throws ValidationFailException {
        if (getCourseBO == null) {
            throw new ValidationFailException("未能获取到参数");
        }
        List<Integer> courseId;
        if (getCourseBO.getCourseId() == null) {
            courseId = mapper.getCourseIdByBO(getCourseBO);
        } else {
            courseId = getCourseBO.getCourseId();
        }

        PageVO<List<CourseVO>> result;
        List<CourseVO> list = mapper.listGetCourseInfo(courseId, getCourseBO.getPageOfSet(), getCourseBO.getPageSize());
        if (list == null) {
            return new PageVO<>();
        }
        result = new PageVO<>(list);
        result.setPageNo(getCourseBO.getPage());
        result.setPageSize(getCourseBO.getPageSize());
        result.setRecordCount(list.size());
        result.setTotalCount(mapper.getCourseIdByBOCount(getCourseBO));
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
    @Transactional(rollbackFor = Exception.class)
    public Boolean addStudentIntoCourse(AddStudentIntoCourseBO addStudentIntoCourseBO) throws Exception {
        if (addStudentIntoCourseBO.getCourseId() == null) {
            throw new ValidationFailException("未能指定课程");
        } else {
            if (mapper.isCourseExistById(addStudentIntoCourseBO.getCourseId()) < 1) {
                throw new ValidationFailException("指定的课程不可用");
            }
        }
        if (addStudentIntoCourseBO.getGroupId().length == 0) {
            throw new ValidationFailException("小组不能为空");
        } else {
            if (addStudentIntoCourseBO.getStudentId().length != 0) {
                for (Integer groupId : addStudentIntoCourseBO.getGroupId()) {
                    if (groupInfoMapper.getGroupLeader(groupId) == null || groupInfoMapper.getGroupLeader(groupId) < 1) {
                        throw new ValidationFailException("小组还未指定小组长，指定小组长后再试试吧");
                    }
                    boolean t = false;
                    for (Integer item : addStudentIntoCourseBO.getStudentId()) {
                        if (groupInfoMapper.isGroupLeader(groupId, item) > 0) {
                            t = true;
                        }
                    }
                    if (!t) {
                        throw new ValidationFailException("小组长必须在选择的范围内");
                    }
                }
                for (Integer item : addStudentIntoCourseBO.getStudentId()) {
                    if (userInfoMapper.isUserExist(item, 1) < 1) {
                        throw new ValidationFailException("指定的学生不可用");
                    }
                    if (mapper.isStudentInGroup(item, addStudentIntoCourseBO.getGroupId()) < 1) {
                        throw new ValidationFailException("学生不在这些小组内");
                    }
                    if (mapper.isStudentInCourse(item, addStudentIntoCourseBO.getCourseId()) > 0) {
                        throw new ValidationFailException("学生已经在课程里了");
                    }
                    if (mapper.addStudentIntoCourse(item, addStudentIntoCourseBO.getCourseId()) < 1) {
                        throw new Exception("插入学生失败");
                    }
                }
            } else {
                for (Integer groupId : addStudentIntoCourseBO.getGroupId()) {
                    Integer[] studentId = groupInfoMapper.listGetGroupMembersId(groupId);
                    for (Integer item : studentId) {
                        if (mapper.isStudentInCourse(item, addStudentIntoCourseBO.getCourseId()) > 0) {
                            throw new ValidationFailException("学生已经在课程里了");
                        }
                        if (mapper.addStudentIntoCourse(item, addStudentIntoCourseBO.getCourseId()) < 1) {
                            throw new Exception("插入学生失败");
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean modifyCourse(AddModifyCourseBO courseBO, Integer operatorId) throws Exception {
        if (courseBO.getCourseId() != null) {
            if (mapper.isCourseExistById(courseBO.getCourseId()) < 1) {
                throw new ValidationFailException("选择的课程不可用");
            }
        } else {
            throw new ValidationFailException("未获取到指定课程");
        }
        if (courseBO.getLeaderTeacher() != null) {
            if (userInfoMapper.isUserExist(courseBO.getLeaderTeacher(), 2) < 1) {
                throw new ValidationFailException("责任老师不可用");
            }
        } else {
            throw new ValidationFailException("责任教师不能为空");
        }
        boolean t = false;
        if (courseBO.getTeacherId().length != 0) {
            for (Integer id : courseBO.getTeacherId()) {
                if (id.equals(courseBO.getLeaderTeacher())) {
                    t = true;
                }
                if (userInfoMapper.isUserExist(id, 2) < 1) {
                    throw new ValidationFailException("选择的实训教师不可用");
                }
            }
        } else {
            throw new ValidationFailException("实训教师不能为空");
        }
        if (!t) {
            throw new ValidationFailException("责任教师必须在实训教师之内");
        }
        if (courseBO.getCourseDate() == null) {
            throw new ValidationFailException("课程开始时间不能为空");
        }
        if (courseBO.getCourseClassroom() == null) {
            throw new ValidationFailException("课程教室不能为空，总要挑块地吧");
        }
        if (courseBO.getCourseName() == null) {
            throw new ValidationFailException("说真的，给你的课程取个名字吧，不要传空过来了");
        }
        if (courseBO.getCourseSignTime() == null) {
            throw new ValidationFailException("解锁成就：时候未到（开始时间为空）");
        }
        if (courseBO.getCourseOutTime() == null) {
            throw new ValidationFailException("解锁成就：永不停止（结束时间为空）");
        }
        String date = courseBO.getCourseDate();
        long courseDate;
        long courseSign;
        long courseOut;
        try {
            courseDate = TimeUtil.changeStringToDateYMD(date).getTime();
            courseSign = TimeUtil.changeStringToDateHM(courseBO.getCourseSignTime()).getTime();
            courseOut = TimeUtil.changeStringToDateHM(courseBO.getCourseOutTime()).getTime();
        } catch (ParseException e) {
            throw new ValidationFailException("是看不懂的时间格式啦，换一种试试吧");
        }
        if (courseDate < new Date().getTime()) {
            throw new ValidationFailException("设置的时间太近啦，预留多一点时间再试试吧");
        }
        if (courseOut - courseSign <= 2700) {
            if (courseOut - courseSign <= 0) {
                throw new ValidationFailException("喂喂，这时间设置的完全不对嘛,结束时间怎么能小于开始时间呢");
            }
            throw new ValidationFailException("一节课的时间也太少了吧，延长到45分钟再试试吧");
        }
        if (mapper.isCourseTimeEnable(courseBO.getCourseDate(), courseBO.getCourseSignTime(), courseBO.getCourseOutTime(), courseBO.getCourseClassroom(), courseBO.getCourseId()) > 0) {
            throw new ValidationFailException("来晚了一步，教室已经被占用了");
        }

        if (mapper.updateCourse(courseBO, new Date(), operatorId) < 1) {
            throw new Exception("更新失败");
        }
        mapper.delAllTeacherByCourseId(courseBO.getCourseId());
        for (int item : courseBO.getTeacherId()) {
            if (mapper.addTeacherIntoTeacherCourse(item, courseBO.getCourseId()) < 1) {
                throw new Exception("插入实训教师失败");
            }
        }
        return true;
    }


}
