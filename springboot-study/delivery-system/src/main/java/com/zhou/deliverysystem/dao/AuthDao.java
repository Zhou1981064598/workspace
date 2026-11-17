package com.zhou.deliverysystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.deliverysystem.enenty.Auth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AuthDao extends BaseMapper<Auth> {

    List<String> selectPermsByUserId(Long userid);
}
