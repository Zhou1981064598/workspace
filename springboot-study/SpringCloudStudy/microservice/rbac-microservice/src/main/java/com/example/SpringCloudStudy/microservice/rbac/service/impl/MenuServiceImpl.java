package com.example.SpringCloudStudy.microservice.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.SpringCloudStudy.microservice.rbac.entity.MenuEntity;
import com.example.SpringCloudStudy.microservice.rbac.repository.MenuRepository;
import com.example.SpringCloudStudy.microservice.rbac.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author star zhang
 * @since /
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuRepository, MenuEntity> implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    /**
     * 获取指定模块的树形菜单
     * @param module /
     * @return  /
     */
    @Override
    public List<MenuEntity> findMenusByModule(String module) {
        return getChildrenMenus(menuRepository.findMenusByModule(module));
    }
    private List<MenuEntity> getChildrenMenus(List<MenuEntity> menus){
        List<MenuEntity> list = new LinkedList<>();
        for (MenuEntity menu : menus) {
            if (menu.getPid() == 0) {
                list.add(menu);
                // 递归：判断 pid 是否和上级菜单的 id 相同
                getChildrenMenusRecursion(menu, menus);
            }
        }
        return list;
    }
    private void getChildrenMenusRecursion(MenuEntity parent, List<MenuEntity> menus) {
        List<MenuEntity> children = new LinkedList<>();
        for (MenuEntity menu : menus) {
            if (Objects.equals(menu.getPid(), parent.getId())) {
                children.add(menu);
                getChildrenMenusRecursion(menu, menus); // 递归调用
            }
        }
        parent.setChildren(children);
    }

    @Override
    public List<MenuEntity> findMenusByPid(long pid, boolean isRecovery) {
        return menuRepository.findMenusByPid(pid, isRecovery);
    }

    /**
     * 获取角色权限
     *
     * @param roleID /
     * @return /
     */
    @Override
    public List<MenuEntity> getRoleMenuByRoleId(Long roleID) {
        // 获取角色菜单
        List<MenuEntity> roleMenus = menuRepository.findMenusByRoleIdAndModule(roleID);
        // 获取所有的菜单
        List<MenuEntity> menus = menuRepository.findSimpleMenusByModule("rbac");
        // 将用户具有的权限标记为 true
        for (MenuEntity menu : menus) {
            for (MenuEntity roleMenu : roleMenus) {
                if (Objects.equals(menu.getId(), roleMenu.getId())) {
                    menu.setIsChecked(true);
                }
            }
        }
        return getChildrenMenus(menus);
    }

    /**
     * 修改角色权限
     *
     * @param roleID  /
     * @param menuIds /
     */
    @Override
    public void editRoleMenu(Long roleID, List<Long> menuIds) {
        menuRepository.deleteMenusByRoleId(roleID);
        if (ObjectUtils.isEmpty(menuIds)) return;
        menuRepository.insertMenusByRoleId(roleID, menuIds);
    }
}
