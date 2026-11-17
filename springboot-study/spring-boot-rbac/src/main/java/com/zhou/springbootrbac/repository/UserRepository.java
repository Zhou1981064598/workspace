package com.zhou.springbootrbac.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.springbootrbac.eneity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository extends BaseMapper<UserEntity> {
}
