package com.zhou.deliverysystem.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhou.deliverysystem.dao.RoleDao;
import com.zhou.deliverysystem.dao.UserDao;
import com.zhou.deliverysystem.dao.UserRoleDao;
import com.zhou.deliverysystem.enenty.RiderApply;
import com.zhou.deliverysystem.enenty.Role;
import com.zhou.deliverysystem.enenty.User;
import com.zhou.deliverysystem.pojo.LoginUser;
import com.zhou.deliverysystem.pojo.Paging;
import com.zhou.deliverysystem.pojo.ResultCode;
import com.zhou.deliverysystem.service.*;
import com.zhou.deliverysystem.utils.JwtUtil;
import com.zhou.deliverysystem.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource(name = "userDao")
    private UserDao userDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    @Resource
    RoleService roleService;

    @Autowired
    RoleDao roleDao;

    @Resource
    UserRoleDao userRoleDao;

    @Resource
    @Lazy
    EvaluateService evaluateService;

    @Resource
    @Lazy
    RiderApplyService riderApplyService;

    @Resource
    private UserRoleService userRoleService;

    public Long getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return loginUser.getUser().getId();
    }
    @Override
    public HashMap<String,String> login(User user) {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassWord());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示（如果authenticate为null，则认证不通过）
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //如果认证通过了，使用userId生成一个jwt，jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        //把token响应给前端
        HashMap<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return map;
    }

    @Override
    public ResultCode register(User user) {
        //1、查询用户名是否存在
        User userEntity=userDao.selectOne(new QueryWrapper<User>()
                .select("username")
                .eq("username",user.getUserName()));
        if(!ObjectUtils.isEmpty(userEntity)&&!userEntity.getUserName().isEmpty()){
            {
                return ResultCode.FAiLURE_REGISTER_EXIST_USERNAME;
            }
        }else {
            user.setPassword(new BCryptPasswordEncoder(10).encode(user.getPassWord()));
            user.setStatus(1);
            int insert = userDao.insert(user);
            System.out.println(user.getId());
            if (insert==1){
                roleService.addUserRole(user.getId());
                return ResultCode.SUCCESS_REGISTER;
            }else {  return ResultCode.FAiLURE_REGISTER;
                  }
    }}

    @Override
    public User getuser() {
        return  userDao.selectById(getUserId());
    }

    @Override
    public Paging<User> getAllRider(Paging<User> paging) {
        List<Integer> userIdList=userRoleDao.selectAllByRoleIdIntegers(3L);
        Page<User> page=userDao.selectPage(new Page<>(paging.getCurrentPage(),paging.getPageSize()),
                new QueryWrapper<User>().select().in("id",userIdList));
        return Paging.build(page.getTotal(),paging.getCurrentPage(),paging.getPageSize(),page.getRecords());
    }

    @Override
    public User getUserById(Long id) {
        return  userDao.selectById(id);
    }

    @Override
    public void logout() {
        redisCache.deleteObject("login:"+getUserId());
    }

    @Override
    public String updateNameById(String name) {
        if ("".equals(name)){
            return "修改失败,用户名不能为空";
        }
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",getUserId());
        User user=new User();
        user.setUsername(name);
        try {
            userDao.update(user,updateWrapper);
            return "修改成功";
        }catch (Exception e){
            return "用户名已存在,请重新输入";
        }
    }

    @Override
    public String updatePhoneById(String phone) {
        if (phone.isEmpty()){
            return "修改失败";
        }
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",getUserId());
        User user=new User();
        user.setPhone(phone);
        userDao.update(user,updateWrapper);
        return "修改成功";
    }

    @Override
    public String updatePasswordById(@RequestBody String password) {
        if("".equals(password)){
        return "修改失败,密码为空";
    }
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",getUserId());
        User user=new User();
        user.setPassword(new BCryptPasswordEncoder(10).encode(password));
        userDao.update(user,updateWrapper);
        return "修改成功";
}

    @Override
    public List<Role> selectListByUserId(){

        return roleDao.selectListByUserId(getUserId());
    }

    @Override
    public String examine(Long userId,Integer auditResult) {
        if (userId!=null&&auditResult==1){
            userRoleService.addRider(userId);
            riderApplyService.examine(userId,auditResult);
            addRider(userId);
            return "审核通过";
    }
        riderApplyService.examine(userId,auditResult);
        return "审核不通过";
    }

    @Override
    public void addRider(Long userId) {
        User user=new User();
        RiderApply riderApply= riderApplyService.getApplyById(userId);
        user.setPreferences(riderApply.getPreferences());
        user.setRealName(riderApply.getName());
        user.setProfession(riderApply.getProfession());
        userDao.update(user,new UpdateWrapper<User>().eq("id",userId));
    }

    @Override
    public void setScore(Long courierId) {
        User user=new User();
        user.setScore(evaluateService.getScore(courierId));
        userDao.update(user,new UpdateWrapper<User>().eq("id",courierId));
    }

    @Override
    public void banUser(List<Integer> list) {
        if (!list.isEmpty()){
            for (Integer id:list){
                User user=new User();
                user.setStatus(0);
                UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
                updateWrapper.eq("id",id);
                userDao.update(user,updateWrapper);
            }
        }
    }

    @Override
    public void activate(List<Integer> list) {
        if (!list.isEmpty()){
            for (Integer id:list){
                User user=new User();
                user.setStatus(1);
                UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
                updateWrapper.eq("id",id);
                userDao.update(user,updateWrapper);
            }
        }
    }
}

