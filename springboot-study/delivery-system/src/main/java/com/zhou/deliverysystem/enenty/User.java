package com.zhou.deliverysystem.enenty;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("user")
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("realname")
    private String realName;

    @TableField("profession")
    private String profession;

    @TableField("gender")
    private String gender;

    @TableField("phone")
    private String phone;

    @TableField("email")
    private String email;

    @TableField("score")
    private Double score;

    @TableField("preferences")
    private String preferences;

    @TableField("avatar_path")
    private String avatarPath;

    @TableField("status")
    private int status;

    @TableField("pwd_reset_time")
    private LocalDateTime pwdResetTime;


    public String getPassWord() {
        return this.password;
    }

    public String getUserName() {
        return this.username;
    }

}
