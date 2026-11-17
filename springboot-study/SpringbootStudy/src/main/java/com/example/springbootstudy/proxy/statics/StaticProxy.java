package com.example.springbootstudy.proxy.statics;



public class StaticProxy implements  TargetInterface{
    private Target target;
    public StaticProxy(Target target){
        this.target=target;
    }
    @Override
    public void test() {
        System.out.println("1");
        target.test();
        System.out.println("2");
    }
}
