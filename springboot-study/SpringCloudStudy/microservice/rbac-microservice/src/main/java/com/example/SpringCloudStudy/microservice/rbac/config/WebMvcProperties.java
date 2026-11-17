package com.example.SpringCloudStudy.microservice.rbac.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("web.mvc")
public class WebMvcProperties {
    private WebMvcResourceProperties resource;
}
