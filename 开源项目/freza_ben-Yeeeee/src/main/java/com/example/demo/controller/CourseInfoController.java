package com.example.demo.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.bo.AddModifyCourseBO;
import com.example.demo.bo.AddStudentIntoCourseBO;
import com.example.demo.bo.GetCourseBO;
import com.example.demo.enums.Code;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.service.impl.CourseInfoServiceImpl;
import com.example.demo.util.JwtUtil;
import com.example.demo.vo.CourseVO;
import com.example.demo.vo.PageVO;
import com.example.demo.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "课程接口")
@RestController
@RequestMapping("/demo/course-info")
public class CourseInfoController {
    @Resource
    CourseInfoServiceImpl service;

    @ApiOperation("条件查询课程")
    @ResponseBody
    @GetMapping("**/listGetCourseInfo")
    public ResultVO listGetCourseInfo(GetCourseBO getCourseBO) throws ValidationFailException {
        PageVO<List<CourseVO>> pageVO = service.listGetCourseInfo(getCourseBO);
        if (pageVO != null) {
            if (pageVO.getRecordCount() != null && pageVO.getRecordCount() != 0) {
                return new ResultVO(pageVO);
            }
        }
        return new ResultVO(Code.NULL_DATA.getCode(), Code.NULL_DATA.getMessage());
    }

    @ApiOperation("添加学生")
    @ResponseBody
    @PostMapping("/addStudentIntoCourse")
    public ResultVO addStudentIntoCourse(@RequestBody AddStudentIntoCourseBO addStudentIntoCourseBO) throws Exception {
        if (service.addStudentIntoCourse(addStudentIntoCourseBO)) {
            return new ResultVO();
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());
    }

    @ApiOperation("编辑（包含停用）课程")
    @ResponseBody
    @PostMapping("/modifyCourse")
    public ResultVO modifyCourse(@RequestBody AddModifyCourseBO courseBO, HttpServletRequest request) throws Exception {
        Integer operatorId = tokenParsing(request.getHeader("token"));
        if (service.modifyCourse(courseBO, operatorId)) {
            return new ResultVO();
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());
    }

    public Integer tokenParsing(String token) throws ValidationFailException {
        DecodedJWT verify = JwtUtil.SelectToken(token);
        return verify.getClaim("id").asInt();
    }
}

