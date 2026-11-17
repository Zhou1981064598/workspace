package com.zhou.mapper;

import com.zhou.base.BaseMapper;
import com.zhou.entity.Building;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface BuildingMapper extends BaseMapper<Building> {
}
