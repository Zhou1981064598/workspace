package com.example.demo.controller;

import com.example.demo.model.Userinfo;
import com.example.demo.service.UserService;
import com.example.demo.util.ReturnData;
import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Date:2022-11-07
 * Time:19:49
 *
 * @Author: 东莞呵呵
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/user/login")
    public ReturnData login(Userinfo userinfo, HttpServletRequest request) {
        if (userinfo == null
                || !StringUtils.hasLength(userinfo.getUser_name())
                || !StringUtils.hasLength(userinfo.getPassword())) {
            return ReturnData.fail("参数非法", null);
        }
        Userinfo retUserinfo = userService.login(userinfo);
        if (retUserinfo!=null) {
            HttpSession session = request.getSession(true);
            StringBuffer key= new StringBuffer("userinfo").append(retUserinfo.getUser_name());
            session.setAttribute(key.toString(),retUserinfo);
            return ReturnData.ok("成功",null);
        }else {
            return ReturnData.fail("用户名或密码错误",null);
        }
    }

    @RequestMapping("/user/logout")
    public ReturnData logOut(HttpServletRequest request){

    }

    @RequestMapping("/admin/dashboard/index")
    public ReturnData homePage(){

    }


    @RequestMapping("/admin/user/page/list")
    public ReturnData paging(String userName,int role,int pageIndex,int pageSize){

    }
}
