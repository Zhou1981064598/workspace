package com.zhou.springbootrbac.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.springbootrbac.eneity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentRepository extends BaseMapper<StudentEntity> {
}
