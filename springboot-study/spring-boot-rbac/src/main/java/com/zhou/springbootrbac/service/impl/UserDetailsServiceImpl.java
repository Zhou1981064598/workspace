package com.zhou.springbootrbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.springbootrbac.eneity.AuthEntity;
import com.zhou.springbootrbac.eneity.RoleEntity;
import com.zhou.springbootrbac.eneity.UserEntity;
import com.zhou.springbootrbac.repository.AuthRepository;
import com.zhou.springbootrbac.repository.RoleRepository;
import com.zhou.springbootrbac.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource(name = "userRepository")
    UserRepository userRepository;

    @Resource(name = "roleRepository")
    RoleRepository roleRepository;

    @Resource(name = "authRepository")
    AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //用户信息.权限
        //1、根据用户名查询用户信息
        UserEntity user=userRepository
                .selectOne(new QueryWrapper<UserEntity>()
                .select("id","username","password").eq("username",username));
        //判断用户是否存在
        if(ObjectUtils.isEmpty(user)){
            return User.withUsername("anonymity")
                    .password("")
                    .authorities(Collections.emptyList())
                    .build();
        }
        //2、根据用户id查询权限(角色+权限)
        //查出角色
        List<RoleEntity> roles=roleRepository.selectListByUserId(user.getId());
        //3、根据角色ID查询权限
        List<AuthEntity> auths=authRepository.selectListByRoleIds(roles.stream().map(RoleEntity::getId).collect(Collectors.toList()));
        //4、组装成UserDetails对象
        List<GrantedAuthority> authorities=new LinkedList<>();
        for (RoleEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleName())));
        }
        for (AuthEntity auth : auths) {
            authorities.add(new SimpleGrantedAuthority(auth.getUri()));
        }

        return User.withUsername(username).password(user.getPassword()).authorities(authorities).build();
}}
