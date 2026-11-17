package com.example.springbootstudy.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录的控制器
 */
@Controller
public class LoginController {
    /*/
    //不使用模板引擎
    @RequestMapping("/test1")
    @ResponseBody
    public String login(){
        return "登录成功";
    }
    //模板引擎：方法1
    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    //@ResponseBody返回json格式数据，就不找页面
    public ModelAndView test2(ModelAndView modelAndView){
        Map<String,Object> attr=new HashMap<>();
        attr.put("title","测试页面标题");
        modelAndView.addAllObjects(attr);
        modelAndView.setViewName("test");
        return modelAndView;
    }
    //模板引擎：方法2
    @RequestMapping(value = "/test3",method = RequestMethod.GET)
    //@ResponseBody
    public String test3(Model model){
        Map<String,Object> attr=new HashMap<>();
        attr.put("title","测试页面标题");
        model.addAllAttributes(attr);
        return "test";
    }

     */

    /**
     * 只为展示一个页面：注册
     * @return /
     */
    @RequestMapping("/logup")
    public String logup(){
        return "logup";
    }
    /**
     * 只为展示一个页面：登录
     * @return /
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 处理注册
     * @param username /
     * @param password /
     * @param repassword /
     * @param session /
     * @return /
     */
    @RequestMapping(value = "/dologup",method = RequestMethod.POST)
    public String dologup(String username, String password, String repassword, HttpSession session){
        if (!ObjectUtils.isEmpty(username)&&username.equals(repassword)){
            //存储到数据库(插入数据)，使用session代替
            User user=new User();
            user.setUsername(username);
            user.setPassword(password);
            session.setAttribute("user",user);
            return "login";
        }else {
            return "logup";
        }
    }
}
