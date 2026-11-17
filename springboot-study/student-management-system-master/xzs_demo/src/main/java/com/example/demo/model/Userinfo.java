package com.example.demo.model;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Date:2022-11-07
 * Time:19:55
 *
 * @Author: 东莞呵呵
 */
@Data
public class Userinfo {
    int id;
    String user_uuid;
    String user_name;
    String password;
    String real_name;
    int age;
    int sex;
    String birth_day;
    int user_level;
    String phone;
    int role;
    int status;
    String image_path;
    String create_time;
    String modify_time;
    String last_active_time;
    byte deleted;
    String wx_open_id;
}
