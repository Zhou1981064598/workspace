package com.zhou.deliverysystem.controller;

import com.zhou.deliverysystem.enenty.PackagePickInf;
import com.zhou.deliverysystem.enenty.PackageSendInf;
import com.zhou.deliverysystem.pojo.Paging;
import com.zhou.deliverysystem.pojo.Result;
import com.zhou.deliverysystem.pojo.ResultCode;
import com.zhou.deliverysystem.service.PackagePickInfService;
import com.zhou.deliverysystem.service.PackageSendInfService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
public class PackageController {
    @Resource
    PackagePickInfService packagePickInfService;

    @Resource
    PackageSendInfService packageSendInfService;

    //快递代取
    @PostMapping ("/package/pick")
    public Result<String> pick(@RequestBody PackagePickInf packagePickInf){
       String result= packagePickInfService.pick(packagePickInf);
       if (Objects.equals(result, "发布成功")){
           return Result.success(result);
       }else {
           return Result.failure(2011,"发布失败");
       }
    }

    //获取快递代取信息
    @GetMapping("/package/getPackagePickInf")
    public Result<Paging<PackagePickInf>> getPackagePickInf(Paging<PackagePickInf> paging){
        return Result.success("查询成功",packagePickInfService.getPackageInf(paging));
    }

    //通过Id查询快递代取信息
    @GetMapping("/package/selectPackageInfById")
    public Result<PackagePickInf> selectPackagePickInfById(Long id){
        PackagePickInf packagePickInf=packagePickInfService.selectPackageInfById(id);
        if (packagePickInf!=null){
        return Result.success("查询成功",packagePickInf);
    }else{
            return Result.failure(ResultCode.SELECT_FAILURE);
        }
}
    //快递待寄
    @PostMapping ("/package/send")
    public Result<String> send(@RequestBody PackageSendInf packageSendInf){
        String result= packageSendInfService.send(packageSendInf);
        if (Objects.equals(result, "发布成功")){
            return Result.success(result);
        }else {
            return Result.failure(2011,"发布失败");
        }
    }

    //获取快递代寄信息
    @GetMapping("/package/getPackageSendInf")
    public Result<Paging<PackageSendInf>> getPackageSendInf(Paging<PackageSendInf> paging){
        return Result.success("查询成功",packageSendInfService.getPackageInf(paging));
    }

    //通过Id查询快递代取信息
    @GetMapping("/package/selectPackageSendInfById")
    public Result<PackageSendInf> selectPackageSendInfById(Long id){
        PackageSendInf packageSendInf=packageSendInfService.selectPackageInfById(id);
        if (packageSendInf!=null){
            return Result.success("查询成功",packageSendInf);
        }else{
            return Result.failure(ResultCode.SELECT_FAILURE);
        }
    }
}
