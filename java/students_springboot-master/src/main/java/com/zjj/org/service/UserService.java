package com.zjj.org.service;

import com.zjj.org.base.Result;
import com.zjj.org.entity.User;
import com.zjj.org.entity.vo.LoginVo;
import com.zjj.org.entity.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User逻辑层
 * @author zjj
 * @date 2022-04-27
 */
@Service
public interface UserService {
    /**
     * 根据用户名密码查询用户信息
     * @param name
     * @param pwd
     * @return
     */
    User selectByNameAndPwd(String name,String pwd);

    /**
     * 校验登录
     * @param userName 用户名
     * @param pwd 密码
     * @return
     */
    Result checkLogin(String userName,String pwd);

    /**
     * 查询用户信息
     * @param user 条件
     * @return
     */
    Result selectUserList(UserVo user);

    /**
     * 添加或者修改用户信息
     * @param user 实体类
     * @return
     */
    Result addAndUpdate(User user);

    /**
     * 删除用户
     * @param id 主键
     * @return
     */
    Result deleteUser(int id);

    /**
     * 修改登录用户密码
     * @param user 登录用户信息
     * @param loginVo 密码信息
     * @return
     */
    Result updatePwd(User user, LoginVo loginVo);

}
