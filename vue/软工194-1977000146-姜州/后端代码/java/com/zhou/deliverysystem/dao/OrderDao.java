package com.zhou.deliverysystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.deliverysystem.enenty.Mission;
import com.zhou.deliverysystem.enenty.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao extends BaseMapper<Order> {
    List<Order> getMyOrderByStatus(Long userId, @Param("status") int []status);
}
