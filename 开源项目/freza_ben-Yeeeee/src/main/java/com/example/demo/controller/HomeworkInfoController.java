package com.example.demo.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.bo.*;
import com.example.demo.enums.Code;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.HomeworkInfoService;
import com.example.demo.util.JwtUtil;
import com.example.demo.vo.PageVO;
import com.example.demo.vo.ResultVO;
import com.example.demo.vo.StudentHomeworkVO;
import com.example.demo.vo.TeacherHomeworkVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-28
 */
@Api(tags = "作业")
@RestController
@RequestMapping("/demo/homework-info")
public class HomeworkInfoController {

    @Resource
    HomeworkInfoService service;
    @Resource
    UserInfoMapper userInfoMapper;

    @ApiOperation("新增作业")
    @PostMapping("/addHomework")
    public ResultVO addHomework(@RequestBody AddModifyHomeworkBO homeworkBO, HttpServletRequest request) throws ValidationFailException {
        Integer operatorId = tokenParsing(request.getHeader("token"));
        if (service.addHomework(homeworkBO, operatorId)) {
            return new ResultVO();
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());
    }

    @ApiOperation("条件查询作业")
    @GetMapping("/listGetHomework")
    public ResultVO listGetHomework(GetHomeworkBO getHomeworkBO, HttpServletRequest request) throws ValidationFailException {
        Integer operatorId = tokenParsing(request.getHeader("token"));
        Integer userRole = userInfoMapper.getUserRole(operatorId);
        if (userRole == null) {
            return new ResultVO(Code.ACCESS_DENIED.getCode(), Code.ACCESS_DENIED.getMessage());
        }
        if (userRole <= 1) {
            PageVO<List<StudentHomeworkVO>> pageVO = service.listGetStudentHomework(getHomeworkBO, operatorId, userRole);
            if (pageVO != null) {
                if (pageVO.getRecordCount() != 0) {
                    return new ResultVO(pageVO);
                }
            }
        } else {
            PageVO<List<TeacherHomeworkVO>> pageVO = service.listGetTeacherHomework(getHomeworkBO, operatorId, userRole);
            if (pageVO != null) {
                if (pageVO.getRecordCount() != 0) {
                    return new ResultVO(pageVO);
                }
            }
        }
        return new ResultVO(Code.NULL_DATA.getCode(), Code.NULL_DATA.getMessage());
    }

    @ApiOperation("删除作业")
    @PostMapping("/delHomework")
    public ResultVO delHomework(Integer id, HttpServletRequest request) throws ValidationFailException {
        Integer operatorId = tokenParsing(request.getHeader("token"));
        if (service.delHomework(id, operatorId)) {
            return new ResultVO();
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());
    }

    @ApiOperation("教师审批查询作业")
    @GetMapping("/listGetHomeworkTeacher")
    public ResultVO listGetHomeworkTeacher(GetCorrectHomeworkBO getHomeworkBO, HttpServletRequest request) throws ValidationFailException {
        Integer operatorId = tokenParsing(request.getHeader("token"));

        PageVO<List<StudentHomeworkVO>> pageVO = service.listGetTeacherCorrectHomework(getHomeworkBO, operatorId);
        if (pageVO != null) {
            if (pageVO.getRecordCount() != 0) {
                return new ResultVO(pageVO);
            }
        }
        return new ResultVO(Code.NULL_DATA.getCode(), Code.NULL_DATA.getMessage());
    }

    @ApiOperation("教师审批作业")
    @PostMapping("/correctHomework")
    public ResultVO correctHomework(CorrectHomeworkBO HomeworkBO, HttpServletRequest request) throws ValidationFailException {
        Integer operatorId = tokenParsing(request.getHeader("token"));
        if (service.correctHomework(HomeworkBO, operatorId)) {
            return new ResultVO();
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());
    }

    @ApiOperation("学生提交作业")
    @PostMapping("/commitHomework")
    public ResultVO commitHomework(@RequestBody CommitHomeworkBO homeworkBO, HttpServletRequest request) throws ValidationFailException, ParseException {
        Integer operatorId = tokenParsing(request.getHeader("token"));
        if (service.commitHomework(homeworkBO, operatorId)) {
            return new ResultVO();
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());
    }

    public Integer tokenParsing(String token) throws ValidationFailException {
        DecodedJWT verify = JwtUtil.SelectToken(token);
        return verify.getClaim("id").asInt();
    }
}

