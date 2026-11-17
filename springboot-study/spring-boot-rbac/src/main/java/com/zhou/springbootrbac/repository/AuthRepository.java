package com.zhou.springbootrbac.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.springbootrbac.eneity.AuthEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AuthRepository extends BaseMapper<AuthEntity> {
    @Select("<script>" +
            "select id,authname,permission,uri from auth where id in (select distinct auth_id from role_auth where role_id in" +
            "   <foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>\n" +
            "       #{item}" +
            "   </foreach>" +
            ")" +
            "</script>")
    List<AuthEntity>  selectListByRoleIds(@Param("list") List<Long> list);
}
