package com.example.SpringCloudStudy.microservice.rbac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.SpringCloudStudy"})
public class RbacApplication {
    public static void main(String[] args) {
        SpringApplication.run(RbacApplication.class,args);
    }
}

