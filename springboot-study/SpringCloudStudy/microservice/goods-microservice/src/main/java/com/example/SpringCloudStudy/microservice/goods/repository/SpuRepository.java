package com.example.SpringCloudStudy.microservice.goods.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.SpringCloudStudy.microservice.goods.eneity.SpuEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SpuRepository extends BaseMapper<SpuEntity> {
}
