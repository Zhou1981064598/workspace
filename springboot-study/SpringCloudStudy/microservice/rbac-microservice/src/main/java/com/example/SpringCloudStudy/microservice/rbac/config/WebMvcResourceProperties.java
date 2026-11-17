package com.example.SpringCloudStudy.microservice.rbac.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class WebMvcResourceProperties {
    @Value("location")
    private String location;

    @Value("dir")
    private String dir;

    @Value("mapping")
    private String mapping;
}