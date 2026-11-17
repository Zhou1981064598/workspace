package com.zhou.deliverysystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.deliverysystem.dao.RoleDao;
import com.zhou.deliverysystem.enenty.Role;
import com.zhou.deliverysystem.pojo.LoginUser;
import com.zhou.deliverysystem.service.RoleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 19810
* @description 针对表【role】的数据库操作Service实现
* @createDate 2023-04-21 18:34:03
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService{
    @Resource
    RoleDao roleDao;



    @Override
    public void addUserRole(Long userId) {
        roleDao.addUserRole(userId);
    }


}
