package com.zhou.springbootrbac.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.springbootrbac.eneity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleRepository extends BaseMapper<RoleEntity> {
    @Select("select id,rolename FROM role where id in (select role_id from user_role where user_id=#{userId})")
    List<RoleEntity> selectListByUserId(@Param("userId") Long userId);
}
