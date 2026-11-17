package com.zjj.org.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjj.org.base.Result;
import com.zjj.org.base.ResultCode;
import com.zjj.org.entity.User;
import com.zjj.org.entity.vo.LoginVo;
import com.zjj.org.entity.vo.UserVo;
import com.zjj.org.mapper.UserMapper;
import com.zjj.org.service.UserService;
import com.zjj.org.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjj
 * @date 2022-04-27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 根据用户名密码查询用户信息
     *
     * @param name
     * @param pwd
     * @return
     */
    @Override
    public User selectByNameAndPwd(String name, String pwd) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper.eq("user_name",name);
        userQueryWrapper.eq("user_pwd",pwd);
        return userMapper.selectOne(userQueryWrapper);
    }

    /**
     * 校验登录
     *
     * @param userName 用户名
     * @param pwd      密码
     * @return
     */
    @Override
    public Result checkLogin(String userName, String pwd) {
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(pwd)){
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper.eq("user_name",userName);
        userQueryWrapper.eq("user_pwd", MD5Util.string2MD5(pwd));
        User user= userMapper.selectOne(userQueryWrapper);
        if (user == null){
            return Result.failure(ResultCode.USER_NOT_EXIST);
        }
        return Result.success(user);
    }

    /**
     * 查询用户信息
     *
     * @param user 条件
     * @return
     */
    @Override
    public Result selectUserList(UserVo user) {
//        分页
        PageHelper.startPage(user.getPage(),user.getLimit());

//        查询
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(user.getAccount())){
            wrapper.eq("user_name",user.getAccount());
        }
        if(StringUtils.isNotBlank(user.getNickName())){
            wrapper.eq("nick_name",user.getNickName());
        }

        List<User> userList= userMapper.selectList(wrapper);

//        获取分页数据
        PageInfo<User> pageInfo=new PageInfo<>(userList);

        return Result.success(pageInfo.getList(),(int)pageInfo.getTotal());
    }

    /**
     * 添加或者修改用户信息
     *
     * @param user 实体类
     * @return
     */
    @Override
    public Result addAndUpdate(User user) {
        if(user.getId()!=null){
            //TODO 编辑
            User us=userMapper.selectById(user.getId());
            if(us==null){
                return Result.failure(ResultCode.USER_NOT_EXIST);
            }
//            判断密码是否被修改
            if(!user.getUserPwd().equals(us.getUserPwd())){
                user.setUserPwd(MD5Util.string2MD5(user.getUserPwd()));
            }
            int row=userMapper.updateById(user);
            if(row<=0){
                return Result.failure(ResultCode.USER_NOT_EXIST);
            }
        }else{
            //TODO 添加
//          查询添加的用户名是否已经被注册
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("user_name",user.getUserName());
            User us=userMapper.selectOne(wrapper);
            if(us!=null){
                return Result.failure(ResultCode.USER_HAS_EXIST);
            }
//        对密码进行加密
            user.setUserPwd(MD5Util.string2MD5(user.getUserPwd()));
            int i = userMapper.insert(user);
            if(i<=0){
                return Result.failure(ResultCode.USER_NOT_EXIST);
            }
        }

        return Result.success();
    }

    /**
     * 删除用户
     *
     * @param id 主键
     * @return
     */
    @Override
    public Result deleteUser(int id) {
        int row = userMapper.deleteById(id);
        if(row<=0){
            return Result.failure(ResultCode.USER_NOT_EXIST);
        }
        return Result.success();
    }

    /**
     * 修改登录用户密码
     *
     * @param user    登录用户信息
     * @param loginVo 密码信息
     * @return
     */
    @Override
    public Result updatePwd(User user, LoginVo loginVo) {
        if(user==null){
            return Result.failure(ResultCode.USER_NOT_EXIST);
        }
//        TODO 1判断登录用户的密码与传入的原密码是否一致，不一致提示原密码不正确 2将新密码进行加密，然后更新数据库
        String pwd = MD5Util.string2MD5(loginVo.getUserPwd());
        if(!pwd.equals(user.getUserPwd())){
            return Result.failure(ResultCode.USER_PWD_ERROR);
        }
        String newPwd = MD5Util.string2MD5(loginVo.getUserNewPwd());
        user.setUserPwd(newPwd);

        int row = userMapper.updateById(user);
        if(row<=0){
            return Result.failure(ResultCode.USER_UPD_PWD);
        }
        return Result.success();
    }
}
