package com.example.springbootstudy.proxy.statics;


public class Target implements TargetInterface{
    @Override
    public void test() {
        System.out.println("test");
    }
}
