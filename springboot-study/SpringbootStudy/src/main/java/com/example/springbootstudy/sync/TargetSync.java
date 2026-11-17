package com.example.springbootstudy.sync;


import com.example.springbootstudy.proxy.statics.TargetInterface;

public class TargetSync implements TargetInterface {
    @Override
    public void test() {
        System.out.println("test");
    }
}
