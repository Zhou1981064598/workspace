package com.zhou.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhou.base.BaseController;
import com.zhou.base.Result;
import com.zhou.entity.Building;
import com.zhou.entity.vo.BuildingVo;
import com.zhou.service.BuildingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.zhou.base.BaseController.createResponseJson;


@Controller
@CrossOrigin(origins = "*") //允许所有域名访问
@RequestMapping(value = "/building")
public class BuildingController extends BaseController {
    @Autowired
    private BuildingService buildingService;
    /**
     * 跳转到宿舍管理页面
     * @return
     */
    @RequestMapping(value = "/toList")
    public String toList(){
        return "building/list";
    }

    /**
     * 分页带条件查询
     * @param buildingVo
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public JSONObject list(BuildingVo buildingVo){
        Result result =buildingService.selectBulList(buildingVo);
        return  createResponseJson(result);
    }
    /**
     * 添加或者修改
     * @param building
     * @return
     */
    @RequestMapping(value = "/handling")
    @ResponseBody
    public JSONObject handling(@RequestBody Building building){
        Result result =buildingService.addAndEditBld(building);
        return createResponseJson(result);
    }

    /**
     * 删除宿舍
     * @param id
     * @return
     */
    @RequestMapping(value = "/del")
    @ResponseBody
    public JSONObject del(int id){
        Result result =buildingService.deleteById(id);
        return createResponseJson(result);
    }
}
