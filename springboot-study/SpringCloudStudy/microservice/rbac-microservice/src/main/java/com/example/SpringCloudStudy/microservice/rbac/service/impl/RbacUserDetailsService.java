package com.example.SpringCloudStudy.microservice.rbac.service.impl;


import com.example.SpringCloudStudy.microservice.rbac.entity.MenuEntity;
import com.example.SpringCloudStudy.microservice.rbac.entity.RoleEntity;
import com.example.SpringCloudStudy.microservice.rbac.entity.UserEntity;
import com.example.SpringCloudStudy.microservice.rbac.repository.MenuRepository;
import com.example.SpringCloudStudy.microservice.rbac.repository.RoleRepository;
import com.example.SpringCloudStudy.microservice.rbac.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RbacUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.根据用户名查询用户信息
        UserEntity userEntity = userRepository.getUserByUsername(username);
        if (ObjectUtils.isEmpty(userEntity)) {
            return User.builder().username("Anonymous").build();
        }

        // 将角色和菜单都转成权限
        List<GrantedAuthority> authorities = new LinkedList<>();

        // 2.根据用户ID查询用户对应的角色
        List<RoleEntity> roleEntities = roleRepository.findRolesByUserId(userEntity.getId());
        if (ObjectUtils.isEmpty(roleEntities)) {
            return User.builder().username(username).password(userEntity.getPassword()).authorities(new ArrayList<>(0)).build();
        }
        // 到这一步就意味着 角色 存在
        for (RoleEntity role : roleEntities) {
            authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRolename())));
        }

        // 3.根据角色ID查权限
        List<MenuEntity> menuEntities = menuRepository.findMenusByRoleIds(roleEntities
                .stream()
                .map(RoleEntity::getId)
                .collect(Collectors.toList()));
        for (MenuEntity menu : menuEntities) {
            if (!StringUtils.isEmpty(menu.getUri())) {
                authorities.add(new SimpleGrantedAuthority(menu.getUri()));
            }
        }

        return User.builder()
                .username(username)
                .password(userEntity.getPassword())
                .authorities(authorities)
                .build();
    }
}