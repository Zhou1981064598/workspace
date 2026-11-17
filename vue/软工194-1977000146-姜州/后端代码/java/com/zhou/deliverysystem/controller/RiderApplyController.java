package com.zhou.deliverysystem.controller;



import com.zhou.deliverysystem.enenty.RiderApply;
import com.zhou.deliverysystem.pojo.Paging;
import com.zhou.deliverysystem.pojo.Result;
import com.zhou.deliverysystem.service.RiderApplyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/riderApply")
public class RiderApplyController {
    @Resource
    RiderApplyService riderApplyService;

    @GetMapping("/getAllApply")
    public Result<Paging<RiderApply>> getAllApply(Paging<RiderApply> paging){
        return Result.success("查询成功",riderApplyService.getAllApply(paging));
    }
}
