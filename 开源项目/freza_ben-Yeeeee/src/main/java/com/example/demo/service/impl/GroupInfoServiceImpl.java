package com.example.demo.service.impl;

import com.example.demo.bo.AddModifyGroupBO;
import com.example.demo.bo.GetGroupBO;
import com.example.demo.bo.GetGroupMembersBO;
import com.example.demo.entity.GroupInfo;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.mapper.GroupInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.GroupInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.vo.GroupMembersVO;
import com.example.demo.vo.GroupVO;
import com.example.demo.vo.PageVO;
import com.example.demo.vo.StudentVO;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
public class GroupInfoServiceImpl extends ServiceImpl<GroupInfoMapper, GroupInfo> implements GroupInfoService {

    @Resource
    GroupInfoMapper mapper;
    @Resource
    UserInfoMapper userInfoMapper;

    @Override
    public synchronized Boolean addGroup(AddModifyGroupBO group, Integer operatorId) throws ValidationFailException {
        GroupInfo groupInfo = new GroupInfo();
        if (group.getGroupGraduate() != null) {
            groupInfo.setGroupGraduate(group.getGroupGraduate());
        } else {
            throw new ValidationFailException("是否结业不能为空");
        }
        if (group.getGroupName() != null) {
            if (mapper.isGroupExistByName(group.getGroupName()) > 0) {
                throw new ValidationFailException("小组名已被使用");
            }
            groupInfo.setGroupName(group.getGroupName());
        } else {
            throw new ValidationFailException("小组名称不能为空");
        }
        groupInfo.setLastUpgradeTime(new Date());
        groupInfo.setLastUpgradePerson(operatorId);
        groupInfo.setCreateTime(new Date());
        groupInfo.setCreatePerson(operatorId);
        return mapper.addGroup(groupInfo) > 0;
    }

    @Override
    public PageVO<List<GroupVO>> listGetGroup(GetGroupBO group) {

        PageVO<List<GroupVO>> result;
        List<GroupVO> list = mapper.listGetGroup(group);
        if (list == null) {
            return new PageVO<>();
        }
        result = new PageVO<>(list);
        result.setPageNo(group.getPage());
        result.setPageSize(group.getPageSize());
        result.setRecordCount(list.size());
        result.setTotalCount(mapper.listGetGroupCount(group));
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
    public synchronized Boolean addGroupMembers(Integer[] id, Integer groupId) throws Exception {
        if (id == null) {
            throw new ValidationFailException("学生不可为空");
        }
        if (id.length < 1) {
            throw new ValidationFailException("学生不可为空");
        }
        if (groupId == null) {
            throw new ValidationFailException("小组不可为空");
        }
        if (mapper.isGroupExistById(groupId) < 1) {
            throw new ValidationFailException("小组不可用");
        }
        for (Integer studentId : id) {
            try {
                if (userInfoMapper.isUserExist(studentId, 1) < 1) {
                    throw new ValidationFailException("学生不可用");
                }
            } catch (NullPointerException e) {
                throw new ValidationFailException("学生不可用");
            }
            if (mapper.isGroupMembersExist(null, studentId) > 0) {
                throw new ValidationFailException("学生已经在其他小组了");
            }
            if (mapper.isGroupMembersExist(groupId, studentId) > 0) {
                throw new ValidationFailException("学生已经在该小组了");
            }
            if (mapper.addGroupMembers(studentId, groupId) < 1) {
                throw new Exception("插入失败");
            }
        }
        return true;
    }

    @Override
    public PageVO<List<GroupMembersVO>> listGetGroupMembers(GetGroupMembersBO groupMembers) throws ValidationFailException {
        if (groupMembers.getGroupName() != null && groupMembers.getGroupName().length() > 20) {
            throw new ValidationFailException("名字太长啦，根本不会有这么长的名字嘛！");
        }
        PageVO<List<GroupMembersVO>> result;
        List<GroupMembersVO> list = mapper.listGetGroupMembers(groupMembers);
        if (list == null) {
            return new PageVO<>();
        }
        result = new PageVO<>(list);
        result.setPageNo(groupMembers.getPage());
        result.setPageSize(groupMembers.getPageSize());
        result.setRecordCount(list.size());
        result.setTotalCount(mapper.listGetGroupMembersCount(groupMembers));
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
    public synchronized Boolean delGroupMembers(Integer[] id, Integer groupId) throws Exception {
        if (id == null) {
            throw new ValidationFailException("学生不可为空");
        }
        if (id.length < 1) {
            throw new ValidationFailException("学生不可为空");
        }
        if (groupId == null) {
            throw new ValidationFailException("小组不可为空");
        }

        if (mapper.isGroupExistById(groupId) < 1) {
            throw new ValidationFailException("小组不可用");
        }
        for (Integer studentId : id) {
            try {
                if (userInfoMapper.isUserExist(studentId, 1) < 1) {
                    throw new ValidationFailException("学生不可用");
                }
            } catch (NullPointerException e) {
                throw new ValidationFailException("学生不可用");
            }
            if (mapper.isGroupMembersExist(null, studentId) <= 0) {
                throw new ValidationFailException("学生不在任何小组中");
            }
            if (mapper.delGroupMembers(studentId, groupId) < 1) {
                throw new Exception("删除失败");
            }
        }
        return true;
    }

    @Override
    public Boolean disableGroup(Integer groupId, Integer operatorId) throws Exception {
        if (groupId == null) {
            throw new ValidationFailException("小组不可为空");
        }
        if (mapper.isGroupExistById(groupId) < 1) {
            throw new ValidationFailException("小组不可用");
        }
        if (mapper.delGroupMembersByGroupId(groupId) == null) {
            throw new Exception("操作失败");
        }
        return mapper.disableGroupById(groupId, operatorId, new Date()) > 0;
    }

    @Override
    public Boolean setGroupLeader(Integer groupId, Integer leaderId, Integer operatorId) throws ValidationFailException {
        if (groupId == null) {
            throw new ValidationFailException("未能指定目标小组");
        }
        if (leaderId == null) {
            throw new ValidationFailException("未能指定目标学生");
        }
        if (mapper.isGroupExistById(groupId) < 1) {
            throw new ValidationFailException("小组不存在");
        }
        if (mapper.isGroupMembersExist(groupId, leaderId) < 1) {
            throw new ValidationFailException("指定的学生不在小组内");
        }

        return mapper.setGroupLeader(groupId, leaderId, operatorId, new Date()) > 0;
    }


}
