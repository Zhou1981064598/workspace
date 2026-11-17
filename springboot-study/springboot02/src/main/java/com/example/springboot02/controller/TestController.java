package com.example.springboot02.controller;

import com.example.springboot02.entity.Test;
import com.example.springboot02.mapper.TestMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {
    @Resource
    TestMapper testMapper;
    @GetMapping("/test")
    public List<Test> test(){
        return testMapper.findAll();
    }
}
