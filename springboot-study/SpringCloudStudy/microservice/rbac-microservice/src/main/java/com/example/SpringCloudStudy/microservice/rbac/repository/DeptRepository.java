package com.example.SpringCloudStudy.microservice.rbac.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.SpringCloudStudy.microservice.rbac.entity.DeptEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 部门 Mapper 接口
 * </p>
 *
 * @author star zhang
 * @since  / 
 */
@Mapper
public interface DeptRepository extends BaseMapper<DeptEntity> {

}
