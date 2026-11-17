package com.example.SpringCloudStudy.microservice.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SpringCloudStudy.common.microservice.constant.SecurityConstant;
import com.example.SpringCloudStudy.common.microservice.util.JwtUtil;
import com.example.SpringCloudStudy.microservice.rbac.config.WebMvcProperties;
import com.example.SpringCloudStudy.microservice.rbac.entity.UserEntity;
import com.example.SpringCloudStudy.microservice.rbac.repository.UserRepository;
import com.example.SpringCloudStudy.microservice.rbac.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author star zhang
 * @since /
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserRepository, UserEntity> implements UserService {
    @Autowired
    private WebMvcProperties webMvcProperties;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 获取登录用户的信息
     *
     * @return /
     */
    @Override
    public UserEntity getInfo(HttpServletRequest request) {
        // 1、请求头中的 token
        String requestToken = request.getHeader(SecurityConstant.REQUEST_HEADER_AUTHORIZATION_KEY);
        String username = null;
        try {
            Claims claims = JwtUtil.parseJWT(requestToken);
            username = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // 2、根据用户名查询数据库，获取用户信息
        return userRepository.getInfoByUsername(username);
    }

    /**
     * 修改密码
     *
     * @param oldPassword /
     * @param newPassword /
     * @param request     /
     * @return /
     */
    @Override
    public boolean changePassword(String oldPassword, String newPassword, HttpServletRequest request) {
        // 1、请求头中的 token
        String requestToken = request.getHeader(SecurityConstant.REQUEST_HEADER_AUTHORIZATION_KEY);
        String username;
        try {
            Claims claims = JwtUtil.parseJWT(requestToken);
            username = claims.getSubject();
            // 判断原密码是否正确
            String password = userRepository.getPassword(username);
            if (passwordEncoder.matches(oldPassword, password)) {
                // 修改密码
                return userRepository.changePassword(username, passwordEncoder.encode(newPassword)) == 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改密码
     *
     * @param file /
     * @return /
     */
    public String changeAvatar(MultipartFile file, HttpServletRequest request) {
        // 1、请求头中的 token
        String requestToken = request.getHeader(SecurityConstant.REQUEST_HEADER_AUTHORIZATION_KEY);
        String username;
        try {
            Claims claims = JwtUtil.parseJWT(requestToken);
            username = claims.getSubject();
            String filename = file.getOriginalFilename();
            if (!ObjectUtils.isEmpty(filename)) {
                //通过CommonsMultipartFile的方法直接写到文件
                File path = new File(webMvcProperties.getResource().getLocation()
                        .concat(webMvcProperties.getResource().getDir()) + filename);
                file.transferTo(path);

                String avatar = "/".concat(webMvcProperties.getResource().getDir()).concat(filename);
                userRepository.changeAvatar(username, avatar);
                return avatar;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
