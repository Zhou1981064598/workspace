package com.zhou.controller;

import com.zhou.feign.StudentFeign;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@CrossOrigin(origins = "*") //允许所有域名访问

@RequestMapping(value = "/tiaozhuan")
public class TiaoZhuanController {
    @Resource
    StudentFeign studentFeign;

@RequestMapping(value = "/students/tolist")
@ResponseBody
public String studenttolist(){
        return studentFeign.studenttoList();
    }
@RequestMapping(value = "/building/tolist")
@ResponseBody
public String buildingtolist(){
    return studentFeign.buildingtoList();
    }
@RequestMapping(value = "/score/tolist")
@ResponseBody
    public String scoretolist(){
        return studentFeign.scoretoList();
    }
}
