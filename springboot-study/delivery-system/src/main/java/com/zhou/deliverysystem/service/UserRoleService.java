package com.zhou.deliverysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.deliverysystem.enenty.UserRole;

public interface UserRoleService extends IService<UserRole> {
    void addRider(Long userId);
}
