package com.zhou.dao;

import com.zhou.domain.Book;
import com.zhou.domain.User;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from user where user_email=#{email} AND user_password=#{password} AND user_status!='1' ")
    @Results(id="userMap",value = {
            @Result(id=true,column = "user_id",property = "id"),
            @Result(column="user_name",property="name"),
            @Result(column="user_password",property="password"),
            @Result(column="user_email",property="email"),
            @Result(column="user_status",property="status"),
            @Result(column="user_role",property="role")

    })

     User login(User user);

}
/**
 * 管理系统所有Mapper接口查询的方法基于@Select注解完成，其他方法都使用Mapper
 * 的映射文件完成，比如    @Select("select * from user where user_email=#{emial}
 * AND user_password=#{password} AND user_status!='1' ")通过注解的方式查询用户信息，
 * 由于User持久化类当中的属性名称与user数据库中的字段是不一样的，无法直接将查询的结果封装到持久化
 * 类，所以使用Results注解将查询的结果映射到User持久化类中的属性去。从而完成查询的封装效果
 *
 */