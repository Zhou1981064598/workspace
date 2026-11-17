package com.zhou.deliverysystem.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.deliverysystem.dao.MissionDao;
import com.zhou.deliverysystem.enenty.Mission;
import com.zhou.deliverysystem.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MissionServiceImpl extends ServiceImpl<MissionDao, Mission> implements MissionService {
    @Resource
    MissionDao missionDao;

    @Resource
    UserService userService;

    @Resource
    PackagePickInfService packagePickInfService;

    @Resource
    PackageSendInfService packageSendInfService;

    @Resource
    OrderService orderService;
    @Override
    public String addMission(Mission mission) {
        Long courierId=userService.getuser().getId();
        mission.setCourierId(courierId);
        mission.setStatus(1);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("inf_id",mission.getInfId());
        map.put("courier_id",courierId);
        map.put("order_id",mission.getOrderId());
        List<Mission> missionResult=missionDao.selectByMap(map);
        if (!missionResult.isEmpty()){
            return "您已接受该单";
        }
        int result=missionDao.insert(mission);
        if (result!=1){
            return "操作失败";
        }else {
            if ("快递代取".equals(mission.getType())){
            packagePickInfService.updatePackageStatusById(mission.getInfId());
            orderService.acceptOrder(mission.getOrderId(),courierId,2);

        }if ("快递代寄".equals(mission.getType())){
                packageSendInfService.updatePackageStatusById(mission.getInfId());
                orderService.acceptOrder(mission.getOrderId(),courierId,2);
            }
            return "接单成功";
        }
    }

    @Override
    public String arrangeMission(Mission mission) {
        mission.setStatus(1);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("inf_id",mission.getInfId());
        map.put("courier_id",mission.getCourierId());
        map.put("order_id",mission.getOrderId());
        List<Mission> missionResult=missionDao.selectByMap(map);
        if (!missionResult.isEmpty()){
            return "该骑手已接受该单";
        }
        int result=missionDao.insert(mission);
        if (result!=1){
            return "操作失败";
        }else {
            if ("快递代取".equals(mission.getType())){
                packagePickInfService.updatePackageStatusById(mission.getInfId());
                orderService.acceptOrder(mission.getOrderId(),mission.getCourierId(),2);

            }if ("快递代寄".equals(mission.getType())){
                packageSendInfService.updatePackageStatusById(mission.getInfId());
                orderService.acceptOrder(mission.getOrderId(),mission.getCourierId(),2);
            }
            return "派单成功";
        }    }

    @Override
    public List<Mission> getMyMissionByStatus( int status) {
        Long courierId=userService.getuser().getId();
        Map<String,Object> map=new HashMap<>();
        map.put("courier_id",courierId);
        map.put("status",status);
        return missionDao.selectByMap(map);
    }

    @Override
    public void cancelMission(Long id,Long infId,Long orderId,String type) {
        if ("快递代取".equals(type)){
            packagePickInfService.cancelMission(infId);
        }
        if ("快递代寄".equals(type)){
            packageSendInfService.cancelMission(infId);
        }
        orderService.cancelMission(orderId);
        missionDao.deleteById(id);
    }
    @Override
    public void updateMissionStatus(int status,Long id) {
        Mission mission=new Mission();
        mission.setStatus(status);
        UpdateWrapper<Mission> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        missionDao.update(mission,updateWrapper);
    }

    @Override
    public Mission getMissionByOrderId(Long orderId) {
        QueryWrapper<Mission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("order_id",orderId);
        return missionDao.selectOne(queryWrapper);
    }

    @Override
    public void submitMission(Long id, Long orderId) {
        updateMissionStatus(2,id);
        orderService.updateStatusById(orderId,3);
    }


}
