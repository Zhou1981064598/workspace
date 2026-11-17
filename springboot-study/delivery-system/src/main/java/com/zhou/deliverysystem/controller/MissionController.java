package com.zhou.deliverysystem.controller;

import com.zhou.deliverysystem.enenty.Mission;
import com.zhou.deliverysystem.enenty.PackagePickInf;
import com.zhou.deliverysystem.enenty.PackageSendInf;
import com.zhou.deliverysystem.pojo.Result;
import com.zhou.deliverysystem.service.MissionService;
import com.zhou.deliverysystem.service.PackagePickInfService;
import com.zhou.deliverysystem.service.PackageSendInfService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/mission")
public class MissionController {
    @Resource
    MissionService missionService;

    @Resource
    PackagePickInfService packagePickInfService;

    @Resource
    PackageSendInfService packageSendInfService;

    @GetMapping("/addMission")
    @PreAuthorize("hasAnyAuthority('mission:addMission')")
    public Result<Mission> addMission( Mission mission){
      String result= missionService.addMission(mission);
      if (Objects.equals(result, "接单成功")){
          return Result.success("添加成功",null);
      }
      else {
          return Result.failure(2010,result);
      }
    }
    @GetMapping("/getMyDoingMissions")
    @PreAuthorize("hasAnyAuthority('mission:getMyDoingMission')")
    public Result< Map<Object,Object>> getMyDoingMissions( ){
         List<Mission> missions= missionService.getMyMissionByStatus(1);
        Map<Object,Object> map=new HashMap<>();

        for (Mission mission : missions) {
            if ("快递代取".equals(mission.getType())){
                PackagePickInf packagePickInf=packagePickInfService.selectPackageInfById(mission.getInfId());
                if (packagePickInf!=null){
                map.put(mission.getId(),packagePickInf);}
            }
            if ("快递代寄".equals(mission.getType())){
                PackageSendInf packageSendInf=packageSendInfService.selectPackageInfById(mission.getInfId());
                if (packageSendInf!=null){
                    map.put(mission.getId(),packageSendInf);}
            }
        }
        return Result.success("查询成功",map);
    }
    @GetMapping("/getMySubmitMissions")
    @PreAuthorize("hasAnyAuthority('mission:getMySubmitMission')")

    public Result<Map<Object,Object>> getMySubmitMissions( ){
        List<Mission> missions= missionService.getMyMissionByStatus(2);
        Map<Object,Object> map=new HashMap<>();
        for (Mission mission : missions) {
            if ("快递代取".equals(mission.getType())){
                map.put(mission.getId(),packagePickInfService.selectPackageInfById(mission.getInfId()));
            }
            if ("快递代寄".equals(mission.getType())){
                map.put(mission.getId(),packageSendInfService.selectPackageInfById(mission.getInfId()));
            }
        }
        return Result.success("查询成功",map);
    }
    @GetMapping("/getMyFinishMissions")
    @PreAuthorize("hasAnyAuthority('mission:mission:getMyFinishMission')")

    public Result<Map<Object,Object>> getMyFinishMissions( ){
        List<Mission> missions= missionService.getMyMissionByStatus(3);
        Map<Object,Object> map=new HashMap<>();
        for (Mission mission : missions) {
            if ("快递代取".equals(mission.getType())){
                map.put(mission.getId(),packagePickInfService.selectPackageInfById(mission.getInfId()));
            }
            if ("快递代寄".equals(mission.getType())){
                map.put(mission.getId(),packageSendInfService.selectPackageInfById(mission.getInfId()));
            }
        }
        return Result.success("查询成功",map);
    }
    @GetMapping("/cancelMission")
    @PreAuthorize("hasAnyAuthority('mission:cancelMission')")

    public Result<String> cancelMission( Long missionId,Long infId,Long orderId, String type){
        if (missionId!=null&&infId!=null&&orderId!=null&&type!=null){
            missionService.cancelMission(missionId,infId,orderId,type);
            return Result.success("取消成功",null);

        }return Result.failure(2010,"取消失败");
    }

    @GetMapping("/submitMission")
    @PreAuthorize("hasAnyAuthority('mission:submitMission')")
    public Result<String> submitMission( Long missionId,Long orderId){
        if (missionId!=null&&orderId!=null){
            missionService.submitMission(missionId,orderId);
            return Result.success("提交成功",null);
        }return Result.failure(2010,"提交失败");
    }
}
