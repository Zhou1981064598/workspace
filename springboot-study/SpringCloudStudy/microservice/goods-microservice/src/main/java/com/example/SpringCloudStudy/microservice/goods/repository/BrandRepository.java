package com.example.SpringCloudStudy.microservice.goods.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SpringCloudStudy.microservice.goods.eneity.BrandEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BrandRepository extends BaseMapper<BrandEntity> {
}
