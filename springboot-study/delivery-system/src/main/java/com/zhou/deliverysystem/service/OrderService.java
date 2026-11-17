package com.zhou.deliverysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.deliverysystem.enenty.Order;

import java.util.List;

public interface OrderService extends IService<Order> {
    void  addOrder(Order order);
    List<Order> getMyOrderByStatus(int[] status);
    void acceptOrder(Long id,Long courierId,int Status);
    void setOrderInfId(Long id,Long infId);
    void deleteOrder(Long id,Long infId,String type);
    void cancelMission(Long id);
    void confirmReceipt(Long orderId,Long missionId);
    void updateStatusById(Long id,int status);
}
