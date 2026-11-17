package com.example.SpringCloudStudy.microservice.rbac.controller;


import com.example.SpringCloudStudy.common.microservice.base.BaseController;
import com.example.SpringCloudStudy.common.microservice.pojo.Result;
import com.example.SpringCloudStudy.common.microservice.pojo.ResultCode;
import com.example.SpringCloudStudy.microservice.rbac.entity.UserEntity;
import com.example.SpringCloudStudy.microservice.rbac.service.UserService;
import com.example.SpringCloudStudy.microservice.rbac.vo.ChangePasswordVo;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author star zhang
 * @since /
 */
@RestController
@RequestMapping("/rbac/user")
public class UserController extends BaseController<UserEntity> {

    protected UserService service;

    public UserController(UserService service) {
        super(service);
        this.service = service;
    }

    /**
     * 获取登录用户信息
     *
     * @return /
     */
    @GetMapping("/info")
    public Result<UserEntity> info(HttpServletRequest request) {
        UserEntity user = service.getInfo(request);
        if (ObjectUtils.isEmpty(user)) {
            return Result.build(ResultCode.ERROR_TOKEN_EXPIRE, user);
        }
        return Result.success("获取用户信息成功", user);
    }

    /**
     * 修改密码
     *
     * @param changePasswordVo     /
     * @param request /
     * @return /
     */
    @PostMapping("/chpswd")
    public Result<Object> chpswd(@RequestBody ChangePasswordVo changePasswordVo, HttpServletRequest request) {
        String oldPassword = changePasswordVo.getOldPassword();
        String newPassword = changePasswordVo.getNewPassword();
        String fixPassword = changePasswordVo.getFixPassword();
        if (ObjectUtils.isEmpty(newPassword) || !newPassword.equals(fixPassword)) {
            return Result.build(ResultCode.ERROR_PASSWORD_NOT_CHECK);
        }
        if (!service.changePassword(oldPassword, newPassword, request)) {
            return Result.build(ResultCode.ERROR_PASSWORD_ERROR);
        }
        return Result.success("密码修改成功");
    }

    /**
     * 修改头像
     *
     * @param file    /
     * @param request /
     * @return /
     */
    @PostMapping("/avatar")
    public Result<Object> avatar(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String avatar = service.changeAvatar(file, request);
        if (ObjectUtils.isEmpty(avatar)) {
            return Result.build(ResultCode.ERROR_AVATAR_UPLOAD_FAULER);
        } else {
            return Result.success("头像修改成功", avatar);
        }
    }
}
