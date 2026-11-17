package com.zhou.deliverysystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.deliverysystem.dao.UserRoleDao;
import com.zhou.deliverysystem.enenty.User;
import com.zhou.deliverysystem.enenty.UserRole;
import com.zhou.deliverysystem.service.UserRoleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {
    @Resource
    UserRoleDao userRoleDao;
    @Override
    public void addRider(Long userId) {
        if (userId!=null){
        UserRole userRole=new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(3L);
        userRoleDao.insert(userRole);
    }
    }
}
