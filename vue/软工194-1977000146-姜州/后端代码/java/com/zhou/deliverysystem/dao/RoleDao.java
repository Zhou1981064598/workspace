package com.zhou.deliverysystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.deliverysystem.enenty.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleDao  extends BaseMapper<Role> {
    @Select("select id,rolename FROM role where id in (select role_id from user_role where user_id=#{userId})")
    List<Role> selectListByUserId(@Param("userId") Long userId);
    void addUserRole(@Param("userId") Long userId);
}
