package com.zhou.deliverysystem.controller;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.zhou.deliverysystem.enenty.Evaluate;
import com.zhou.deliverysystem.enenty.RiderApply;
import com.zhou.deliverysystem.enenty.Role;
import com.zhou.deliverysystem.enenty.User;
import com.zhou.deliverysystem.pojo.Result;
import com.zhou.deliverysystem.pojo.ResultCode;
import com.zhou.deliverysystem.service.EvaluateService;
import com.zhou.deliverysystem.service.OrderService;
import com.zhou.deliverysystem.service.RiderApplyService;
import com.zhou.deliverysystem.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userServiceImpl")
    UserService userService;

    @Resource
    RiderApplyService riderApplyService;

    @Resource
    OrderService orderService;

    @Resource
    EvaluateService evaluateService;

    //用户登录接口
    @PostMapping("/login")
    public Result<HashMap<String, String>> login(@RequestBody User user) {
        return Result.success("登录成功", userService.login(user));
    }

    //测试接口
    @PostMapping("/hello")
    @PreAuthorize("hasAnyAuthority('user:hello')")
    public String user() {
        return "hello";
    }

    //退出登录接口
    @RequestMapping("/logout")
    public Result<Object> logout() {
        userService.logout();
        return Result.success("退出成功", null);
    }


    @GetMapping("/getuser")
    public Result<User> getuser() {
        return Result.success("查询成功", userService.getuser());
    }

    @GetMapping("/getuserById")
    public Result<User> getuserById(Long id) {
        return Result.success("查询成功", userService.getUserById(id));
    }

    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        if (userService.register(user).getCode() == 2000) {
            return Result.success(ResultCode.SUCCESS);
        } else {
            if (userService.register(user) == ResultCode.FAiLURE_REGISTER_EXIST_USERNAME) {
                return Result.failure(ResultCode.FAiLURE_REGISTER_EXIST_USERNAME);
            } else {
                return Result.failure(ResultCode.FAiLURE_REGISTER);
            }
        }
    }

    @GetMapping("/updateName")
    public Result<User> updateName(String name) {
        String result = userService.updateNameById(name);
        if ("修改成功".equals(result)) {
            return Result.success("修改成功");
        } else {
            return Result.failure(2010, result);
        }
    }
    @GetMapping("/updatePhone")
    public Result<User> updatePhone(String phone) {
        String result = userService.updatePhoneById(phone);
        if ("修改成功".equals(result)) {
            return Result.success("修改成功");
        } else{
            return Result.failure(2010,null);
        }
    }

    @PostMapping("/updatePassword")
    public Result<User> updatePassword(String password) {
        String result = userService.updatePasswordById(password);
        if ("修改成功".equals(result)) {
            return Result.success("修改成功");
        } else{
            return Result.failure(2010,null);
        }
    }
        @GetMapping("/selectRoleByUserId")
    public Result<List<Role>> selectRoleByUserId() {
       return Result.success("查询成功",userService.selectListByUserId());
    }

    @PostMapping("/submitRiderApply")
    public Result<String> submitRiderApply(@RequestBody RiderApply riderApply) {
        if (!ObjectUtils.isEmpty(riderApply)){
        riderApply.setUserId(userService.getuser().getId());
        riderApply.setStatus(1);
        ResultCode result=riderApplyService.submitApply(riderApply);
        if (result.getCode()==2000){
        return Result.success(ResultCode.SUCCESS);
        }else {
            if (result==ResultCode.FAiLURE_APPLY_EXIST){
                return Result.failure(ResultCode.FAiLURE_APPLY_EXIST);
            }else if(result==ResultCode.FAILURE_APPLY_RIDER_EXIST){
                return Result.failure(ResultCode.FAILURE_APPLY_RIDER_EXIST);
            }
            return Result.failure(ResultCode.APPLY_FAILURE);

        }
    }
        return Result.failure(ResultCode.APPLY_FAILURE);

    }
    @GetMapping("getMyApply")
    public Result<RiderApply> getMyApply(){
       return Result.success( "获取成功",riderApplyService.getMyApply());
    }

    @PostMapping("/evaluate")
    public Result<String> evaluate(@RequestBody Evaluate evaluate) {
        if (!ObjectUtils.isEmpty(evaluate)){
            evaluateService.evaluate(evaluate);
            orderService.updateStatusById(evaluate.getOrderId(),5);
            return Result.success("评价成功");
        }
        return Result.failure(ResultCode.OPTION_FAILURE);
    }
}