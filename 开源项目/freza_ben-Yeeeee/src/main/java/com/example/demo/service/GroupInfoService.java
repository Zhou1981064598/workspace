package com.example.demo.service;

import com.example.demo.bo.AddModifyGroupBO;
import com.example.demo.bo.GetGroupBO;
import com.example.demo.bo.GetGroupMembersBO;
import com.example.demo.entity.GroupInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.vo.GroupMembersVO;
import com.example.demo.vo.GroupVO;
import com.example.demo.vo.PageVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-26
 */
public interface GroupInfoService extends IService<GroupInfo> {

    Boolean addGroup(AddModifyGroupBO addModifyGroupBO, Integer operatorId) throws ValidationFailException;

    PageVO<List<GroupVO>> listGetGroup(GetGroupBO getGroupBO);

    Boolean addGroupMembers(Integer[] studentId, Integer groupId) throws Exception;

    PageVO<List<GroupMembersVO>> listGetGroupMembers(GetGroupMembersBO getGroupMembersBO) throws ValidationFailException;

    Boolean delGroupMembers(Integer[] studentId, Integer groupId) throws Exception;

    Boolean disableGroup(Integer groupId, Integer operatorId) throws Exception;

    Boolean setGroupLeader(Integer groupId, Integer leaderId, Integer operatorId) throws ValidationFailException;
}
