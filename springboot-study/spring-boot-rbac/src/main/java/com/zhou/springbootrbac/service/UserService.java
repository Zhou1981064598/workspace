package com.zhou.springbootrbac.service;

public interface UserService {
    /**
     *
     * @param username 用户名
     * @param password 密码
     * @return token 唯一的用户标识
     */
    String login(String username,String password);
}
