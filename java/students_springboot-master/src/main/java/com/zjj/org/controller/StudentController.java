package com.zjj.org.controller;

import com.alibaba.fastjson.JSONObject;
import com.zjj.org.base.BaseController;
import com.zjj.org.base.Result;
import com.zjj.org.entity.Student;
import com.zjj.org.entity.vo.StudentVo;
import com.zjj.org.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 学生管理
 * @author zjj
 * @date 2022-05-11
 */
@Controller
@RequestMapping(value = "/students")
public class StudentController extends BaseController {
    @Autowired
    private StudentService studentService;
    /**
     * 跳转到学生列表查询页面
     * @return
     */
    @RequestMapping(value = "/toList")
    public String toList(){
        return "student/list";
    }

    /**
     * 分页查询
     * @param studentVo
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public JSONObject list(StudentVo studentVo){
        Result result =studentService.selectStuList(studentVo);
        return createResponseJson(result);
    }

    /**
     * 删除学生
     * @param id
     * @return
     */
    @RequestMapping(value = "/del")
    @ResponseBody
    public JSONObject del(int id){
        Result result =studentService.deleteById(id);
        return createResponseJson(result);
    }

    /**
     * 添加或者修改
     * @param student
     * @return
     */
    @RequestMapping(value = "/handling")
    @ResponseBody
    public JSONObject handling(@RequestBody Student student){
        Result result =studentService.addAndEditStu(student);
        return createResponseJson(result);
    }

}
