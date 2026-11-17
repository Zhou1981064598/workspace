package com.zhou.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "student")
public interface StudentFeign {
    @RequestMapping(value = "/students/toList")
    public String studenttoList();
    @RequestMapping(value = "/building/toList")
    public String buildingtoList();
    @RequestMapping(value = "/score/toList")
    public String scoretoList();

}
