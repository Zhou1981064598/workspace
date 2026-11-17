package com.example.springbootstudy.db;

import com.example.springbootstudy.eneity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
@Component
public class JdbcUtil {
    @Autowired
    Jdbc jdbc;
    public List<User> find(){
        Connection connection=jdbc.getConnection();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from user");
            List<User> userList=new LinkedList<>();
            while (resultSet.next()){
                int id=resultSet.getInt(1);
                String username=resultSet.getString(2);
                String password=resultSet.getString("password");
                User user=new User();

                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                userList.add(user);
            }
            System.out.println(userList);
            return userList;
        }
        catch (SQLException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
