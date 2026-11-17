package com.zhou.entity;

import com.zhou.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author zjj
 * @date 2022-04-27
 */
@Entity //映射到数据库表
@Table(name = "t_user") //映射到数据库中的表名
@Data  //lombok注解，用于实现属性的get和set方法
public class User extends BaseEntity {

//    表字段，长度50，唯一索引，不为空
    @Column(name = "user_name", length = 50, unique = true, nullable = false)
    private String userName;

    @Column(name = "user_pwd",length = 50,nullable = false)
    private String userPwd;

    private String nickName;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                "} " + super.toString();
    }
}
