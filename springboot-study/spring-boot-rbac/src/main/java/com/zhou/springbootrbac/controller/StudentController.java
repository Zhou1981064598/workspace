package com.zhou.springbootrbac.controller;


import com.zhou.springbootrbac.eneity.StudentEntity;


import com.zhou.springbootrbac.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/student")
public class StudentController extends BaseController<StudentEntity> {
    protected StudentService studentService;
    public StudentController(StudentService studentService){
        super(studentService);
        this.studentService=studentService;
    }
}
