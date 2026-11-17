package com.example.springbootstudy.AOP;

import org.springframework.stereotype.Component;

@Component
public class Target {
    public String test1(Boolean isThorw)throws Exception{
        System.out.println("目标对象：test1");
       if (isThorw){
           throw new Exception("异常");
       }
        return "test1";
    }
}
