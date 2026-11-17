package com.zhou.deliverysystem.service.impl;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.zhou.deliverysystem.dao.MissionDao;
import com.zhou.deliverysystem.dao.OrderDao;
import com.zhou.deliverysystem.dao.PackagePickInfDao;
import com.zhou.deliverysystem.dao.PackageSendInfDao;
import com.zhou.deliverysystem.enenty.Order;
import com.zhou.deliverysystem.service.MissionService;
import com.zhou.deliverysystem.service.OrderService;
import com.zhou.deliverysystem.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao,Order> implements OrderService {
    @Resource
    OrderDao orderDao;

    @Resource
    UserService userService;

    @Resource
    PackagePickInfDao packagePickInfDao;

    @Resource
    PackageSendInfDao packageSendInfDao;

    @Resource
    @Lazy
    MissionService missionService;
    @Override
    public void addOrder(Order order) {
        order.setUserId(userService.getuser().getId());
        order.setOrderNo(UUID.randomUUID().toString().replace("-",""));
        orderDao.insert(order);
    }

    @Override
    public List<Order> getMyOrderByStatus(int[] status) {
        Long userId = userService.getuser().getId();
        List<Order> list =orderDao.getMyOrderByStatus(userId,status) ;
        if (!list.isEmpty()) {
            return list;
        } else {
            return null;
        }
    }



    @Override
    public void acceptOrder(Long id,Long courierId,int status) {
        UpdateWrapper<Order> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        Order order=new Order();
        order.setStatus(status);
        order.setCourierId(courierId);
        orderDao.update(order,updateWrapper);
    }
    @Override
    public void confirmReceipt(Long orderId,Long missionId) {
        updateStatusById(orderId,4);
        missionService.updateMissionStatus(3,missionId);
    }
    @Override
    public void setOrderInfId(Long id,Long infId) {
        UpdateWrapper<Order> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        Order order=new Order();
        order.setStatus(1);
        order.setInfId(infId);
        orderDao.update(order,updateWrapper);
    }

    @Override
    public void deleteOrder(Long id,Long infId,String type) {
        if ("快递代取".equals(type)){
            packagePickInfDao.deleteById(infId);
        }
        if ("快递代寄".equals(type)){
            packageSendInfDao.deleteById(infId);
        }
        orderDao.deleteById(id);
    }

    @Override
    public void cancelMission(Long id) {
        UpdateWrapper<Order> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        updateWrapper.set("courier_id",null);
        Order order=new Order();
        order.setStatus(1);
        orderDao.update(order,updateWrapper);
    }

    @Override
    public void updateStatusById(Long id, int status) {
        UpdateWrapper<Order> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        Order order=new Order();
        order.setStatus(status);
        orderDao.update(order,updateWrapper);
    }


}
