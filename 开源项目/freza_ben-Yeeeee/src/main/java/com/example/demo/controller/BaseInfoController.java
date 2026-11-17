package com.example.demo.controller;


import com.example.demo.entity.ClassBaseInfo;
import com.example.demo.entity.DepartmentInfo;
import com.example.demo.entity.GradeInfo;
import com.example.demo.entity.MajorInfo;
import com.example.demo.enums.Code;
import com.example.demo.service.impl.BaseInfoServiceImpl;
import com.example.demo.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-23
 */
@RestController
@Api(tags = "基础信息获取")
@RequestMapping("**/BaseInfo")
public class BaseInfoController {
    @Resource
    BaseInfoServiceImpl service;

    @ApiOperation("获取专业")
    @GetMapping("/getMajorInfo")
    public ResultVO getMajorInfo() {
        List<MajorInfo> list = service.listGetMajorInfo();
        if (list.size() > 0) {
            return new ResultVO(list);
        }
        return new ResultVO(Code.NULL_DATA.getCode(), Code.NULL_DATA.getMessage());
    }

    @ApiOperation("获取院系")
    @GetMapping("/getDepartmentInfo")
    public ResultVO getDepartmentInfo() {
        List<DepartmentInfo> list = service.listGetDepartmentInfo();
        if (list.size() > 0) {
            return new ResultVO(list);
        }
        return new ResultVO(Code.NULL_DATA.getCode(), Code.NULL_DATA.getMessage());
    }

    @ApiOperation("获取班级")
    @GetMapping("/getClassBaseInfo")
    public ResultVO getClassBaseInfo() {
        List<ClassBaseInfo> list = service.listGetClassBaseInfo();
        if (list.size() > 0) {
            return new ResultVO(list);
        }
        return new ResultVO(Code.NULL_DATA.getCode(), Code.NULL_DATA.getMessage());
    }

    @ApiOperation("获取年级")
    @GetMapping("/getGradeInfo")
    public ResultVO getGradeInfo() {
        List<GradeInfo> list = service.listGetGradeInfo();
        if (list.size() > 0) {
            return new ResultVO(list);
        }
        return new ResultVO(Code.NULL_DATA.getCode(), Code.NULL_DATA.getMessage());
    }


}

