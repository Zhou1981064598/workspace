package com.example.demo.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.bo.*;
import com.example.demo.enums.Code;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.service.GroupInfoService;
import com.example.demo.util.JwtUtil;
import com.example.demo.vo.GroupMembersVO;
import com.example.demo.vo.GroupVO;
import com.example.demo.vo.PageVO;
import com.example.demo.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-26
 */
@Controller
@Api(tags = "小组")
@RequestMapping("/demo/group-info")
public class GroupInfoController {

    @Resource
    GroupInfoService service;

    @ApiOperation("新增项目组")
    @PostMapping("/addGroup")
    public ResultVO addGroup(@RequestBody AddModifyGroupBO addModifyGroupBO, HttpServletRequest request) throws ValidationFailException {
        Integer operatorId = tokenParsing(request.getHeader("token"));
        if (service.addGroup(addModifyGroupBO, operatorId)) {
            return new ResultVO();
        }
        return new ResultVO(Code.NULL_DATA.getCode(), Code.NULL_DATA.getMessage());
    }

    @ApiOperation("条件查询项目组")
    @ResponseBody
    @GetMapping("/selectGroup")
    public ResultVO selectGroup(GetGroupBO getGroupBO) {

        PageVO<List<GroupVO>> pageVO = service.listGetGroup(getGroupBO);
        if (pageVO != null) {
            if (pageVO.getRecordCount() != 0) {
                return new ResultVO(pageVO);//返回成功
            }
        }
        return new ResultVO(Code.NULL_DATA.getCode(), Code.NULL_DATA.getMessage());
    }

    @ApiOperation("项目组增加成员")
    @ResponseBody
    @PostMapping("/addGroupMembers")
    public ResultVO addGroupMembers(@RequestBody AddModifyGroupMembersBO addGroupMembersBO) throws Exception {
        if (service.addGroupMembers(addGroupMembersBO.getStudentId(), addGroupMembersBO.getGroupId())) {
            return new ResultVO();
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());
    }

    @ApiOperation("条件查询学生(项目组视图)")
    @ResponseBody
    @GetMapping("**/listGetStudentGroup")
    public ResultVO listGetStudentGroup(GetGroupMembersBO getGroupMembersBO) throws ValidationFailException {
        PageVO<List<GroupMembersVO>> pageVO = service.listGetGroupMembers(getGroupMembersBO);
        if (pageVO != null) {
            if (pageVO.getRecordCount() != 0) {
                return new ResultVO(pageVO);//返回成功
            }
        }
        return new ResultVO(Code.NULL_DATA.getCode(), Code.NULL_DATA.getMessage());
    }

    @ApiOperation("项目组删除成员")
    @ResponseBody
    @PostMapping("/delGroupMembers")
    public ResultVO delGroupMembers(@RequestBody AddModifyGroupMembersBO addGroupMembersBO) throws Exception {
        if (service.delGroupMembers(addGroupMembersBO.getStudentId(), addGroupMembersBO.getGroupId())) {
            return new ResultVO();
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());
    }

    @ApiOperation("设置组长")
    @ResponseBody
    @PostMapping("/setGroupLeader")
    public ResultVO setGroupLeader(@RequestBody SetGroupLeaderBO setGroupLeaderBO, HttpServletRequest request) throws Exception {
        Integer operatorId = tokenParsing(request.getHeader("token"));
        if (service.setGroupLeader(setGroupLeaderBO.getGroupId(), setGroupLeaderBO.getStudentId(), operatorId)) {
            return new ResultVO();
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());
    }

    @ApiOperation("停用项目组")
    @ResponseBody
    @PostMapping("/disableGroup")
    public ResultVO disableGroup(@RequestBody Integer groupId, HttpServletRequest request) throws Exception {
        Integer operatorId = tokenParsing(request.getHeader("token"));
        if (service.disableGroup(groupId, operatorId)) {
            return new ResultVO();
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());
    }

    public Integer tokenParsing(String token) throws ValidationFailException {
        DecodedJWT verify = JwtUtil.SelectToken(token);
        return verify.getClaim("id").asInt();
    }
}

