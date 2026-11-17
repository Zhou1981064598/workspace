package com.zhou.deliverysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.deliverysystem.enenty.Role;

/**
* @author 19810
* @description 针对表【role】的数据库操作Service
* @createDate 2023-04-21 18:34:03
*/
public interface RoleService extends IService<Role> {
    void addUserRole(Long userId);
}
