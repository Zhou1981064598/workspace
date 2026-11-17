package com.example.SpringCloudStudy.microservice.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.SpringCloudStudy.microservice.rbac.entity.RoleEntity;
import com.example.SpringCloudStudy.microservice.rbac.repository.RoleRepository;
import com.example.SpringCloudStudy.microservice.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author star zhang
 * @since /
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleRepository, RoleEntity> implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    /**
     * 获取用户角色
     *
     * @param userId /
     * @return /
     */
    public List<RoleEntity> getUserRole(Long userId) {
        // 获取用户的角色
        List<RoleEntity> userRoles = roleRepository.findRolesByUserId(userId);
        // 获取所有的角色
        List<RoleEntity> roles = roleRepository.findRoles();
        // 将用户具有的权限标记为 true
        for (RoleEntity role : roles) {
            for (RoleEntity userRole : userRoles) {
                if (Objects.equals(role.getId(), userRole.getId())) {
                    role.setIsChecked(true);
                }
            }
        }
        return roles;
    }

    /**
     * 修改用户角色
     *
     * @param userId  /
     * @param roleIds /
     */
    public void editUserRole(Long userId, List<Long> roleIds) {
        roleRepository.deleteRolesByUserId(userId);
        if (ObjectUtils.isEmpty(roleIds)) return;
        roleRepository.insertRolesByUserId(userId, roleIds);
    }
}
