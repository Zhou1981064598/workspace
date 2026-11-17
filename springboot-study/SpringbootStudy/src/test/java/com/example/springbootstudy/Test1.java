package com.example.springbootstudy;

import com.example.springbootstudy.AOP.Target;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test1 {
    @Autowired
    Target target;

    @Test
    public void test() {

        try {
            target.test1(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}