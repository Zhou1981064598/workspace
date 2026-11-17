package com.zhou.deliverysystem.controller;

import com.zhou.deliverysystem.enenty.Address;
import com.zhou.deliverysystem.pojo.Result;
import com.zhou.deliverysystem.service.AddressService;
import com.zhou.deliverysystem.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/address")
public class AddressController {
    @Resource
    AddressService addressService;

    @Resource
    UserService userService;
    @GetMapping("/getaddress")
    public Result<Object> getAddress(){
      return Result.success("查询成功",addressService.getAddress()) ;
    }

    @PostMapping("/addAddress")
    public Result<String> addAddress(@RequestBody Address address){
        address.setUserId( userService.getuser().getId());
       String result= addressService.addAddress(address);
       if ("添加成功".equals(result)){
           return Result.success(result,null);
       }else {
           return Result.failure(2010,"添加失败");
       }
    }
}
