package com.example.SpringCloudStudy.microservice.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SpringCloudStudy.microservice.rbac.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author star zhang
 * @since /
 */
public interface UserService extends IService<UserEntity> {
    /**
     * 获取登录用户的信息
     *
     * @return /
     */
    UserEntity getInfo(HttpServletRequest request);

    /**
     * 修改密码
     *
     * @param oldPassword /
     * @param newPassword /
     * @param request     /
     * @return /
     */
    boolean changePassword(String oldPassword, String newPassword, HttpServletRequest request);

    /**
     * 修改密码
     *
     * @param file /
     * @return /
     */
    String changeAvatar(MultipartFile file, HttpServletRequest request);
}
