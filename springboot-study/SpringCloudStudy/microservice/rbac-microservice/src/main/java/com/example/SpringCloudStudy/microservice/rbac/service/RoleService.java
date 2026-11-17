package com.example.SpringCloudStudy.microservice.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SpringCloudStudy.microservice.rbac.entity.RoleEntity;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author star zhang
 * @since  / 
 */
public interface RoleService extends IService<RoleEntity> {
    /**
     * 获取用户角色
     * @param userId    /
     * @return  /
     */
    List<RoleEntity> getUserRole(Long userId);

    /**
     * 修改用户角色
     * @param userId    /
     */
    void editUserRole(Long userId, List<Long> roleIds);
}
