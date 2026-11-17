package com.zjj.org.mapper;

import com.zjj.org.base.BaseMapper;
import com.zjj.org.entity.User;
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
