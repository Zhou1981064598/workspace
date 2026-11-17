package com.example.springbootstudy;

import com.example.springbootstudy.proxy.statics.TargetInterface;
import com.example.springbootstudy.sync.TargetSync;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

public class SyncProxyTest {
    @Test
    void sync(){
        Object proxyInstance= Proxy.newProxyInstance(TargetSync.class.getClassLoader(),TargetSync.class.getInterfaces(),
                (proxy, method, args) -> {
            System.out.println("around1");
            Object returns=null;
            try {
                returns=method.invoke(new TargetSync(),args);

            }catch (Exception e){
                System.out.println("AfterThrowing");
                e.printStackTrace();
            }
                    System.out.println("AfterReturing");
            return returns;
        });
        TargetInterface target= (TargetInterface) proxyInstance;
        target.test();
    }
}
