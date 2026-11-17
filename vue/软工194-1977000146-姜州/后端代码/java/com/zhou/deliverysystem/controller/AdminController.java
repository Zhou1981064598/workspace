package com.zhou.deliverysystem.controller;

import com.zhou.deliverysystem.enenty.Mission;
import com.zhou.deliverysystem.enenty.User;
import com.zhou.deliverysystem.pojo.Paging;
import com.zhou.deliverysystem.pojo.Result;
import com.zhou.deliverysystem.pojo.ResultCode;
import com.zhou.deliverysystem.service.MissionService;
import com.zhou.deliverysystem.service.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    UserService userService;

    @Resource
    MissionService missionService;
    @GetMapping("examine")
    @PreAuthorize("hasAnyAuthority('admin:examine')")
    public Result<String> examine(Long userId, Integer auditResult){
        if (userId!=null&&auditResult!=null){
       return Result.success(userService.examine(userId,auditResult));
        }
        return Result.failure(ResultCode.OPTION_FAILURE);
    }

    @GetMapping("/getAllRider")
    @PreAuthorize("hasAnyAuthority('admin:getAllRider')")
    public Result<Paging<User>> getAllRider(Paging<User> paging){
       return Result.success("查询成功",userService.getAllRider(paging));
    }

    @GetMapping("/arrangeMission")
    @PreAuthorize("hasAnyAuthority('admin:arrangeMission')")
    public Result<Mission> arrangeMission(Mission mission){
        String result= missionService.arrangeMission(mission);
        if (Objects.equals(result, "派单成功")){
            return Result.success("添加成功",null);
        }
        else {
            return Result.failure(2010,result);
        }
    }
    @PostMapping("/banUsers")
    @PreAuthorize("hasAnyAuthority('admin:banUsers')")
    public Result<String> banUsers(@RequestBody List<Integer> list){
        if (!list.isEmpty()){
            userService.banUser(list);
            return Result.success("禁用成功",null);
        }
        return Result.failure(2010,"封禁用户不能未空");
    }

    @PostMapping("/activateUsers")
    @PreAuthorize("hasAnyAuthority('admin:activateUsers')")
    public Result<String> activateUsers(@RequestBody List<Integer> list){
        if (!list.isEmpty()){
            userService.activate(list);
            return Result.success("解除成功",null);
        }
        return Result.failure(2010,"解禁用户不能未空");
    }
}
