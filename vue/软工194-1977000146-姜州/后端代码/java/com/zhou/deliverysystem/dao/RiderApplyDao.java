package com.zhou.deliverysystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.deliverysystem.enenty.RiderApply;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RiderApplyDao extends BaseMapper<RiderApply> {
   RiderApply selectMyLastApply(Long userId);
}
