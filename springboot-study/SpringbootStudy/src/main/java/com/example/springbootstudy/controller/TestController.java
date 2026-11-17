package com.example.springbootstudy.controller;

import com.example.springbootstudy.Application;

import com.example.springbootstudy.eneity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
    private final Logger logger= LoggerFactory.getLogger(Application.class);

    private final ObjectMapper objectMapper;
    @Autowired
    public TestController(ObjectMapper objectMapper){
        this.objectMapper=objectMapper;
    }
    @SneakyThrows
    @RequestMapping("/test")
    public String test(){
        User user=new User();
        user.setId(1);
        user.setUsername("张三");
        //将对象转换成json字符串
        String userJson=objectMapper.writeValueAsString(user);
        //将json字符串转换为对象
        User user1 = objectMapper .readValue("{\"id\" :1,\"username\":\"张三\", gender\":\"男\"}",User.class);
        return userJson;

}
}
