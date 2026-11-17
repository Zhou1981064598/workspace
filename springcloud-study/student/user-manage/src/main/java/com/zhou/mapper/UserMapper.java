package com.zhou.mapper;

import com.zhou.base.BaseMapper;
import com.zhou.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * User对象持久层
 * @author zjj
 * @date 2022-04-27
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
