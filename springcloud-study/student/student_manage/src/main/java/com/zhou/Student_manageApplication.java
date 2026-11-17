
package com.zhou;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class Student_manageApplication {

    public static void main(String[] args) {
        SpringApplication.run(Student_manageApplication.class, args);
    }

}
