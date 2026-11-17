package com.example.SpringCloudStudy.microservice.rbac.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.SpringCloudStudy.microservice.rbac.entity.DictEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author star zhang
 * @since  / 
 */
@Mapper
public interface DictRepository extends BaseMapper<DictEntity> {

}
