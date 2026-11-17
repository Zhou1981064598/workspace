package com.zhou.deliverysystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.zhou.deliverysystem.dao.AuthDao;
import com.zhou.deliverysystem.dao.UserDao;
import com.zhou.deliverysystem.enenty.User;
import com.zhou.deliverysystem.pojo.LoginUser;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserDao userDao;


    @Resource
    private AuthDao authDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        User user=userDao
                .selectOne(new QueryWrapper<User>()
                        .select("id","username","password","status").eq("username",username));
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
         if (user.getStatus()==0){
            throw new DisabledException("用户已被禁用,请联系管理员解封");
        }
        //TODO 根据用户查询权限信息 添加到LoginUser中
        List<String > list=authDao.selectPermsByUserId(user.getId());
        //封装成UserDetails对象返回
        return new LoginUser(user,list);
    }
}

