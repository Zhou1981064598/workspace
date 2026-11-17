package com.zjj.org.controller;

import com.alibaba.fastjson.JSONObject;
import com.zjj.org.base.BaseController;
import com.zjj.org.base.Result;
import com.zjj.org.entity.User;
import com.zjj.org.entity.vo.UserVo;
import com.zjj.org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户操作系列
 * @author zjj
 * @date 2022-04-29
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    /**
     * 跳转到用户管理页面
     * @return
     */
    @RequestMapping(value = "/toList")
    public String getList(){
        return "user/list";
    }

    /**
     * 分页查询
     * @param request
     * @param userVo
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public JSONObject list(HttpServletRequest request, UserVo userVo){
        Result result =userService.selectUserList(userVo);
        System.out.println("显示数据为："+createResponseJson(result));
        return createResponseJson(result);

    }

    /**
     * 添加用户或者修改用户
     * @param user 需要操作的用户信息
     * @return
     */
    @RequestMapping(value = "/handling")
    @ResponseBody //响应结果为json串
    public JSONObject add(@RequestBody User user){
        Result result = userService.addAndUpdate(user);
        return createResponseJson(result);
    }

    /**
     * 删除
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "del")
    @ResponseBody
    public JSONObject del(int id){
        Result result = userService.deleteUser(id);
        return createResponseJson(result);
    }




}
