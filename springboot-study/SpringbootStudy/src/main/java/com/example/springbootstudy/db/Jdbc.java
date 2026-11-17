package com.example.springbootstudy.db;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
@Configuration
public class Jdbc {
    @Autowired
    JdbcProperties jdbcProperties;
    @SneakyThrows
    public Connection getConnection(){
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcProperties.getUrl(),jdbcProperties.getUsername(),jdbcProperties.getPassword());

    }
}
