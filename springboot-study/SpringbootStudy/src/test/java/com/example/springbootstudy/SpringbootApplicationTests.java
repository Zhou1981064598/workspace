package com.example.springbootstudy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SpringbootApplicationTests {
    @BeforeAll
    void before(){
        System.out.println("所有测试方法前执行");
    }

    @AfterAll
    void after(){
        System.out.println("所有测试方法后执行");
    }

    @Test
    void contextLoads() {
        // 断言 ： 预期的结果
        // assert  a == 5;
        System.out.println("单个测试方法");
    }
}
