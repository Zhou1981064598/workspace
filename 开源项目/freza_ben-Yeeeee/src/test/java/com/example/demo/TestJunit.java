package com.example.demo;

import com.example.demo.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)//测试启动类入口，这里填写自己的
public class TestJunit {
    @Resource
    private UserInfoMapper userMapper;//如有必要，可以直接注入mapper

    @Test//测试方法入口注解
    public void testJunit() {
        System.out.println("我是单元测试");

    }
}