package com.example.springbootstudy;

import com.example.springbootstudy.db.Jdbc;
import com.example.springbootstudy.eneity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
public class JdbcTest {
    @Autowired
    Jdbc jdbc;
    @Test
    void test(){
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
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
