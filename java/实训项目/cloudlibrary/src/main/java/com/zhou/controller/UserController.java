package com.zhou.controller;

import com.zhou.domain.User;
import com.zhou.enetity.Result;
import com.zhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    /**注入UserService对象
     */
    @Autowired
    private UserService userService;
    /**用户登录
     */
    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request){
        try {
            User u=userService.login(user);
            /**用户账号和密码是否查询到用户信息
             * 是：将用户信息存入到Session中，并跳转到后台首页
             * 否：Request域中添加提示信息，并转发到登录页
             */
            if (u!=null){
                request.getSession().setAttribute("USER_SESSION",u);
                return "redirect:/admin/main.jsp";
            }
            request.setAttribute("msg","用户名或密码错误");
            return "forward:/admin/login.jsp";
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("msg","系统错误");
            return "forward:/admin/login.jsp";
        }
    }
 
    @RequestMapping("/toMainPage")
    public String toMainPage(){
        return "main";
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        try{
        //销毁session
        HttpSession session=request.getSession();
        session.invalidate();
        return "forward:/admin/login.jsp";
    }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("msg","系统错误");
            return ("forward:/admin/login.jsp");
        }
    }

}
