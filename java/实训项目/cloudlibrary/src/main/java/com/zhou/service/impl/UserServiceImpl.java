package com.zhou.service.impl;

import com.zhou.dao.UserMapper;
import com.zhou.domain.User;
import com.zhou.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public User login(User user) {
        return userMapper.login(user);
    }
}
