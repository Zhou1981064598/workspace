package com.zjj.org;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class StudentsSpringbootApplicationTests {

    @Test
    void contextLoads() {
        Random random = new Random();
        System.out.println("生成的随机数为:"+(random.nextInt(51)+1));
    }

}
