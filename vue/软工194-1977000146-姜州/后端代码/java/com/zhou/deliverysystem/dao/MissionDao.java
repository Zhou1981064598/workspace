package com.zhou.deliverysystem.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.deliverysystem.enenty.Mission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MissionDao extends BaseMapper<Mission> {
}
