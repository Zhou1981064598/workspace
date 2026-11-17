package com.example.SpringCloudStudy.microservice.rbac.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SpringCloudStudy.microservice.rbac.entity.JobEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 岗位 Mapper 接口
 * </p>
 *
 * @author star zhang
 * @since  / 
 */
@Mapper
public interface JobRepository extends BaseMapper<JobEntity> {

}
