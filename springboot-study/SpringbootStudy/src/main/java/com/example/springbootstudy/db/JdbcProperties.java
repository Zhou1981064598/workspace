package com.example.springbootstudy.db;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("jdbc")
@Data
public class JdbcProperties {
    String url;
    String username;
    String password;
}
