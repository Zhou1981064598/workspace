package com.zhou.deliverysystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.deliverysystem.enenty.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleDao extends BaseMapper<UserRole> {
    List<Integer> selectAllByRoleIdIntegers(Long roleId);
}
