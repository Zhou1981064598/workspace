package com.zhou.springbootrbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.springbootrbac.eneity.UserEntity;
import com.zhou.springbootrbac.repository.UserRepository;
import com.zhou.springbootrbac.service.UserService;
import net.sf.jsqlparser.statement.select.AllColumns;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Service
public class UserServiceImpl implements UserService {

    @Resource(name = "userRepository")
    UserRepository userRepository;

    /**
     *
     * @param username 用户名
     * @param password 密码
     * @return token 唯一的用户标识
     */
    @Override
    public String login(@NotNull String username, @NotNull String password) {
        //1、查询账号和密码、根据用户名
        UserEntity user=userRepository.selectOne(
                new QueryWrapper<UserEntity>()
                        .select("username","password")
                        .eq("username",username));
        //2、判断密码是否正确
        if (!ObjectUtils.isEmpty(user)&&!user.getPassword().isEmpty()){
            if (user.getPassword().equals(password)){
                return "token-"+username;
            }
        }
        return null;
    }


}
