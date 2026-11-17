package com.example.demo.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.bo.*;
import com.example.demo.enums.Code;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.service.impl.ClassInfoServiceImpl;
import com.example.demo.service.impl.CourseInfoServiceImpl;
import com.example.demo.service.impl.UserInfoServiceImpl;
import com.example.demo.util.JwtUtil;
import com.example.demo.vo.ClassVO;
import com.example.demo.vo.PageVO;
import com.example.demo.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "管理员操作")
@Controller
@RequestMapping("/Admin")
public class AdminController {

    @Resource
    UserInfoServiceImpl service;
    @Resource
    ClassInfoServiceImpl classInfoService;
    @Resource
    CourseInfoServiceImpl courseInfoService;

    //-----------------------------------------------teacher---------------------------------------------------
    @ApiOperation("新增教师")
    @ResponseBody
    @PostMapping("/addTeacher")
    public ResultVO addTeacher(@RequestBody AddModifyUserBO addModifyUserBo, HttpServletRequest request) {
        Integer id = tokenParsing(request.getHeader("token"));
        addModifyUserBo.setUserRole(2);
        try {
            if (service.addUser(addModifyUserBo, id)) {
                return new ResultVO();
            }
        } catch (ValidationFailException e) {
            e.printStackTrace();
            return new ResultVO(Code.ILLEGAL_PARAMETER.getCode(), e.getMessage());
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());
    }

    @ApiOperation("修改教师")
    @ResponseBody
    @PostMapping("/modifyTeacher")
    public ResultVO modifyTeacher(@RequestBody AddModifyUserBO addModifyUserBo, HttpServletRequest request) {
        Integer id = tokenParsing(request.getHeader("token"));
        addModifyUserBo.setUserRole(2);
        try {
            if (service.modifyUser(addModifyUserBo, addModifyUserBo.getId(), id)) {
                return new ResultVO();//返回成功
            }
        } catch (ValidationFailException e) {
            e.printStackTrace();
            return new ResultVO(Code.ILLEGAL_PARAMETER.getCode(), e.getMessage());
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());//返回失败
    }

    //======================================================================================================================


    //--------------------------------------------------class-----------------------------------------------------------

    @ApiOperation("新增班级")
    @ResponseBody
    @PostMapping("/addClass")
    public ResultVO addClass(@RequestBody AddDModifyClassBO addDModifyClassBO, HttpServletRequest request) {
        Integer id = tokenParsing(request.getHeader("token"));
        try {
            if (classInfoService.addClass(addDModifyClassBO, id)) {
                return new ResultVO();//返回成功
            }
        } catch (ValidationFailException e) {
            e.printStackTrace();
            return new ResultVO(Code.ILLEGAL_PARAMETER.getCode(), e.getMessage());
        }

        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());//返回失败
    }

    @ApiOperation("条件查询班级")
    @ResponseBody
    @GetMapping("/listGetClass")
    public ResultVO listGetClass(GetClassBO classBO) {
        PageVO<List<ClassVO>> pageVO = classInfoService.listGetClassInfo(classBO);
        if (pageVO != null) {
            return new ResultVO(pageVO);
        }
        return new ResultVO(Code.NULL_DATA.getCode(), Code.NULL_DATA.getMessage());
    }

    @ApiOperation("修改班级")
    @ResponseBody
    @PostMapping("/modifyClass")
    public ResultVO modifyClass(AddDModifyClassBO classBO, HttpServletRequest request) {
        Integer id = tokenParsing(request.getHeader("token"));
        try {
            if (classInfoService.modifyClass(classBO, classBO.getId(), id)) {
                return new ResultVO();//返回成功
            }
        } catch (ValidationFailException e) {
            e.printStackTrace();
            return new ResultVO(Code.ILLEGAL_PARAMETER.getCode(), e.getMessage());
        }

        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());//返回失败
    }
    //===================================================================================================================

    //----------------------------------student-------------------------------------------------------------------------

    @ApiOperation("新增学生")
    @ResponseBody
    @PostMapping("/addStudent")
    public ResultVO addStudent(@RequestBody AddModifyUserBO userBO, HttpServletRequest request) {
        Integer id = tokenParsing(request.getHeader("token"));
        userBO.setUserRole(1);
        try {
            if (service.addUser(userBO, id)) {
                return new ResultVO();
            }
        } catch (ValidationFailException e) {
            e.printStackTrace();
            return new ResultVO(Code.ILLEGAL_PARAMETER.getCode(), e.getMessage());
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());
    }

    @ApiOperation("修改学生")
    @ResponseBody
    @PostMapping("/modifyStudent")
    public ResultVO modifyStudent(@RequestBody AddModifyUserBO addModifyUserBo, HttpServletRequest request) {
        Integer id = tokenParsing(request.getHeader("token"));
        addModifyUserBo.setUserRole(1);
        try {
            if (service.modifyStudent(addModifyUserBo, addModifyUserBo.getId(), id)) {
                return new ResultVO();//返回成功
            }
        } catch (ValidationFailException e) {
            e.printStackTrace();
            return new ResultVO(Code.ILLEGAL_PARAMETER.getCode(), e.getMessage());
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());//返回失败
    }

    @ApiOperation("删除学生/停用教师")
    @ResponseBody
    @PostMapping("/disableUser")
    public ResultVO disableUser(@RequestBody Integer[] id, HttpServletRequest request) throws ValidationFailException {
        Integer operatorId = tokenParsing(request.getHeader("token"));
        if (service.disableUser(id, operatorId)) {
            return new ResultVO();//返回成功
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());//返回失败
    }


    @ApiOperation("新增课程")
    @ResponseBody
    @PostMapping("/addCourse")
    public ResultVO addCourse(@RequestBody AddModifyCourseBO addModifyCourseBO, HttpServletRequest request) throws Exception {
        Integer operatorId = tokenParsing(request.getHeader("token"));
        if (courseInfoService.addCourse(addModifyCourseBO, operatorId)) {
            return new ResultVO();
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());//返回失败
    }

    public Integer tokenParsing(String token) {
        DecodedJWT verify = JwtUtil.SelectToken(token);
        return verify.getClaim("id").asInt();
    }
}
