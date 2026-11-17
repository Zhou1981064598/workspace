package com.example.demo.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.bo.GetStudentBO;
import com.example.demo.bo.GetTeacherBO;
import com.example.demo.bo.ModifyPasswordBO;
import com.example.demo.bo.UserBO;
import com.example.demo.entity.UserInfo;
import com.example.demo.enums.Code;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.service.UserInfoService;
import com.example.demo.util.JwtUtil;
import com.example.demo.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-21
 */
@Api(tags = "用户公共接口")
@RestController
public class UserInfoController {
    @Resource
    UserInfoService service;

    @ApiOperation("获取所有教师(不分页)")
    @GetMapping("**/listGetAllTeacher")
    public ResultVO listGetAllTeacher() {
        PageVO<List<CheckBoxTeacherVO>> pageVO = service.listGetAllTeacher();
        if (pageVO == null) {
            return new ResultVO(Code.NULL_DATA.getCode(), Code.NULL_DATA.getMessage());
        }
        return new ResultVO(pageVO);
    }

    @ApiOperation("登录")
    @PostMapping("**/login")
    @ResponseBody
    public ResultVO login(@RequestBody UserBO user, HttpServletResponse response) {
        UserInfo userInfo = service.userLogin(user);
        if (userInfo == null) {
            return new ResultVO(Code.USER_LOGIN_FAIL.getCode(), Code.USER_LOGIN_FAIL.getMessage());
        }
        response.addHeader("Access-Control-Expose-headers","token");
        response.addHeader("token",userInfo.getUserToken());
        return new ResultVO();
    }

    @ApiOperation("退出登录")
    @PostMapping("**/quit")
    @ResponseBody
    public ResultVO quit(HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.SelectToken(token);
        Integer id = verify.getClaim("id").asInt();
        if (service.userQuit(id)) {
            return new ResultVO();
        }
        return new ResultVO(Code.USER_QUIT_FAIL.getCode(), Code.USER_QUIT_FAIL.getMessage());
    }

    @ApiOperation("修改密码")
    @PostMapping("**/modifyPassword")
    @ResponseBody
    public ResultVO modifyPassword(@RequestBody ModifyPasswordBO modifyPasswordBO, HttpServletRequest request) throws ValidationFailException {
        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.SelectToken(token);
        Integer id = verify.getClaim("id").asInt();
        if (service.modifyPassword(modifyPasswordBO, id)) {
            return new ResultVO();
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());
    }

    @ApiOperation("条件查询教师")
    @ResponseBody
    @GetMapping("**/listGetTeacher")
    public ResultVO listGetTeacher(GetTeacherBO getTeacherBO) {
        getTeacherBO.setUserRole(2);
        PageVO<List<TeacherVO>> pageVO;
        try {
            pageVO = service.listGetUser(getTeacherBO);
        } catch (ValidationFailException e) {
            e.printStackTrace();
            return new ResultVO(Code.ILLEGAL_PARAMETER.getCode(), e.getMessage());
        }
        if (pageVO != null) {
            if (pageVO.getRecordCount() != 0) {
                return new ResultVO(pageVO);//返回成功
            }
        }
        return new ResultVO(Code.NULL_DATA.getCode(), Code.NULL_DATA.getMessage());//返回空数据
    }

    @ApiOperation("条件查询学生")
    @ResponseBody
    @GetMapping("**/listGetStudent")
    public ResultVO listGetStudent(GetStudentBO getStudentBO) {
        getStudentBO.setUserRole(1);
        PageVO<List<StudentVO>> pageVO;
        try {
            pageVO = service.listGetStudent(getStudentBO);
        } catch (ValidationFailException e) {
            e.printStackTrace();
            return new ResultVO(Code.ILLEGAL_PARAMETER.getCode(), e.getMessage());
        }
        if (pageVO != null) {
            if (pageVO.getRecordCount() != 0) {
                return new ResultVO(pageVO);//返回成功
            }
        }
        return new ResultVO(Code.NULL_DATA.getCode(), Code.NULL_DATA.getMessage());//返回空数据
    }
}

