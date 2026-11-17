package com.zhou.springbootrbac.controller;


import com.zhou.springbootrbac.param.UserParam;
import com.zhou.springbootrbac.pojo.Result;
import com.zhou.springbootrbac.pojo.ResultCode;
import com.zhou.springbootrbac.service.UserService;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource(name = "userServiceImpl")
    UserService userService;
    // @RequestMapping(value = "/login",method = RequestMethod.POST)
    @PostMapping("/login2")
    public Result<String> login(@Validated UserParam userParam) {
        if (userParam.getUsername().isEmpty()||userParam.getPassword().isEmpty()){
            return Result.failure(ResultCode.FAILURE_USERNAME_OR_PASSWORD_EMPTY);
        }
        String token=userService.login(userParam.getUsername(),userParam.getPassword());
        if (ObjectUtils.isEmpty(token)){
            return Result.failure(ResultCode.FAILURE_USERNAME_OR_PASSWORD_ERROR);
        }
        return Result.success("登录成功",token);

    }
    @GetMapping("/user")
    public Result<String> user(){
        return Result.success("测试");
    }
}