package com.zhou.controller;


import com.zhou.base.BaseController;
import com.zhou.base.Result;
import com.zhou.base.ResultCode;
import com.zhou.entity.User;
import com.zhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping(value = "")
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 跳转到登录页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request){
        return "login";
    }

    /**
     * 登录判断
     * @param request
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
//        接收参数
        String name = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
//        判断登录用户信息
        Result result = userService.checkLogin(name,pwd);
        if(result.getResultCode().equals(ResultCode.SUCCESS)){
            User user=(User)result.getData();
            mav.addObject("nickName",user.getNickName());
            mav.setViewName("index");//返回页面
        }else{
            mav.addObject("msg",result.getResultCode().showMsg());
            mav.setViewName("login");
        }
        return mav;
    }

    /**
     * 跳转到首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/index_x")
    public String shotYe(HttpServletRequest request){
        return "index_x";
    }}
