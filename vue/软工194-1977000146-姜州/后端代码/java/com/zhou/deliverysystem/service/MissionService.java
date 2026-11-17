package com.zhou.deliverysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.deliverysystem.enenty.Mission;

import java.util.List;
import java.util.Map;

public interface MissionService extends IService<Mission> {
    String addMission(Mission mission);
    String arrangeMission(Mission mission);
    List<Mission> getMyMissionByStatus(int status);
    void cancelMission(Long id,Long infId,Long orderId,String type);
    void updateMissionStatus(int status,Long id);
    Mission getMissionByOrderId(Long orderId);
    void submitMission(Long id,Long orderId);
}
