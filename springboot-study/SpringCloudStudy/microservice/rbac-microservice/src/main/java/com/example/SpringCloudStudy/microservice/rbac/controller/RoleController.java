package com.example.SpringCloudStudy.microservice.rbac.controller;

import com.example.SpringCloudStudy.common.microservice.base.BaseController;
import com.example.SpringCloudStudy.common.microservice.pojo.Result;
import com.example.SpringCloudStudy.microservice.rbac.entity.RoleEntity;
import com.example.SpringCloudStudy.microservice.rbac.service.RoleService;
import com.example.SpringCloudStudy.microservice.rbac.vo.EditRoleVo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author star zhang
 * @since /
 */
@RestController
@RequestMapping("/rbac/role")
public class RoleController extends BaseController<RoleEntity> {
    protected RoleService roleService;

    public RoleController(RoleService service) {
        super(service);
        this.roleService = service;
    }

    /**
     * 获取用户的角色
     *
     * @param userId /
     * @return /
     */
    @GetMapping("/role")
    @JsonView(RoleEntity.SimpleView.class)
    public Result<List<RoleEntity>> role(@RequestParam("id") Long userId) {
        return Result.success("获取角色成功", roleService.getUserRole(userId));
    }

    /**
     * 修改用户的角色
     *
     * @param editRoleVo /
     * @return /
     */
    @PostMapping("/editrole")
    public Result<Object> editrole(@RequestBody EditRoleVo editRoleVo) {
        roleService.editUserRole(editRoleVo.getUserId(), editRoleVo.getRoleIds());
        return Result.success("修改角色成功");
    }
}
