package com.zhou.service;

import com.zhou.domain.User;
import org.springframework.stereotype.Service;

/**
 * 用户接口
 */
@Service
public interface UserService {
//通过User用户账号密码查询用户信息
    User login(User user);
}
