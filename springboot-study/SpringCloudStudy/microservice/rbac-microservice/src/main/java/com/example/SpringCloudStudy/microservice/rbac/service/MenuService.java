package com.example.SpringCloudStudy.microservice.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SpringCloudStudy.microservice.rbac.entity.MenuEntity;

import java.util.List;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author star zhang
 * @since /
 */
public interface MenuService extends IService<MenuEntity> {
    /**
     * 查询指定模块的菜单
     *
     * @param module /
     * @return /
     */
    List<MenuEntity> findMenusByModule(String module);

    /**
     * 查询下级菜单
     *
     * @param pid /
     * @return /
     */
    List<MenuEntity> findMenusByPid(long pid, boolean isRecovery);

    /**
     * 获取角色权限
     * @param roleID    /
     * @return  /
     */
    List<MenuEntity> getRoleMenuByRoleId(Long roleID);

    /**
     * 修改角色权限
     * @param roleID    /
     * @param menuIds    /
     */
    void editRoleMenu(Long roleID, List<Long> menuIds);
}
