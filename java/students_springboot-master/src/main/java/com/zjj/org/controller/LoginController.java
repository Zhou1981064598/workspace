package com.zjj.org.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjj.org.base.BaseController;
import com.zjj.org.constant.ConstantConfig;
import com.zjj.org.base.Result;
import com.zjj.org.base.ResultCode;
import com.zjj.org.entity.User;
import com.zjj.org.entity.vo.LoginVo;
import com.zjj.org.redis.RedisTemplateUtil;
import com.zjj.org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录
 * @author zjj
 * @date 2022-04-23
 */
@Controller
//@RequestMapping(value = "")
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

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
//            将登录用户信息存入redis中
            redisTemplateUtil.set(ConstantConfig.USER_INFO, JSONArray.toJSONString(user));
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
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(){
//        清除登录时存入的用户信息redis
        redisTemplateUtil.delete(ConstantConfig.USER_INFO);
        return "login";
    }

    /**
     * 跳转到修改密码页面
     * @return
     */
    @RequestMapping(value = "/editPwd")
    public String editPwd(){
        return "editPwd";
    }

    /**
     * 修改登录密码
     * @param loginVo
     * @return
     */
    @RequestMapping(value = "/updatePwd")
    @ResponseBody
    public JSONObject updatePwd(@RequestBody LoginVo loginVo){
//        从redis中读取的登录用户的信息
        User user=JSON.parseObject(redisTemplateUtil.get(ConstantConfig.USER_INFO).toString(),User.class);
        Result result=userService.updatePwd(user,loginVo);
        return createResponseJson(result);
    }
}
