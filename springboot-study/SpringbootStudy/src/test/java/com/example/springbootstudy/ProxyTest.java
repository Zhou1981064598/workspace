package com.example.springbootstudy;

import com.example.springbootstudy.proxy.statics.StaticProxy;
import com.example.springbootstudy.proxy.statics.Target;
import com.example.springbootstudy.proxy.statics.TargetInterface;
import org.junit.jupiter.api.Test;

public class ProxyTest {
    @Test
    void test(){
        Target target=new Target();
        TargetInterface targetInterface=new StaticProxy(target);
        targetInterface.test();
    }
}
