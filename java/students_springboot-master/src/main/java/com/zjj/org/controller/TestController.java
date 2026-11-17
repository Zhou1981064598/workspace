package com.zjj.org.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试项目能否访问到html页面
 * @author zjj
 * @date 2022-04-23
 */

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/view")
    public ModelAndView view(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
//        接收参数
        String name = request.getParameter("userName");
        String pwd = request.getParameter("pwd");

        mav.setViewName("test");//返回页面
//        传参数
        mav.addObject("yhm",name);
        mav.addObject("mm",pwd);
        return mav;
    }

}
