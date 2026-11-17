package com.example.SpringCloudStudy.microservice.rbac.controller;


import com.example.SpringCloudStudy.common.microservice.base.BaseController;
import com.example.SpringCloudStudy.common.microservice.pojo.Paging;
import com.example.SpringCloudStudy.common.microservice.pojo.Result;
import com.example.SpringCloudStudy.microservice.rbac.entity.MenuEntity;
import com.example.SpringCloudStudy.microservice.rbac.service.MenuService;
import com.example.SpringCloudStudy.microservice.rbac.vo.RoleMenuVo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统菜单 前端控制器
 * </p>
 *
 * @author star zhang
 * @since /
 */
@RestController
@RequestMapping("/rbac/menu")
public class MenuController extends BaseController<MenuEntity> {
    protected MenuService menuService;

    public MenuController(MenuService service) {
        super(service);
        this.menuService = service;
    }

    /**
     * 获取分页数据
     *
     * @param paging /
     * @return /
     */
    @Override
    public Result<Paging<MenuEntity>> page(Paging<MenuEntity> paging) {
        long pid = ObjectUtils.isEmpty(paging.getPid()) ? 0 : paging.getPid();
        List<MenuEntity> menus = menuService.findMenusByPid(pid, paging.getIsRecovery());
        return Result.success("获取分页成功",
                Paging.build(menus.size(), paging.getCurrentPage(), paging.getPageSize(), menus));
    }

    /**
     * 获取菜单列表
     *
     * @param module /
     * @return /
     */
    @GetMapping("/list")
    public Result<List<MenuEntity>> list(String module) {
        return Result.success("获取菜单成功", menuService.findMenusByModule(module));
    }

    /**
     * 获取角色的权限
     *
     * @param roleId /
     * @return /
     */
    @GetMapping("/auth")
    @JsonView(MenuEntity.SimpleView.class)
    public Result<List<MenuEntity>> role(@RequestParam("id") Long roleId) {
        return Result.success("获取角色成功", menuService.getRoleMenuByRoleId(roleId));
    }

    /**
     * 修改角色的权限
     *
     * @param roleMenuVo /
     * @return /
     */
    @PostMapping("/editauth")
    public Result<Object> editrole(@RequestBody RoleMenuVo roleMenuVo) {
        menuService.editRoleMenu(roleMenuVo.getRoleId(), roleMenuVo.getMenuIds());
        return Result.success("修改权限成功");
    }
}
