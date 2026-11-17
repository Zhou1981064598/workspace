package com.example.SpringCloudStudy.microservice.rbac.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SpringCloudStudy.microservice.rbac.entity.RoleEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author star zhang
 * @since /
 */
@Mapper
public interface RoleRepository extends BaseMapper<RoleEntity> {
    /**
     * 查询所有角色
     *
     * @return /
     */
    @Select("select id,rolename " +
            "from role " +
            "where is_deleted = 0")
    List<RoleEntity> findRoles();

    /**
     * 根据用户查询角色
     *
     * @param userId /
     * @return /
     */
    @Select("select id,rolename " +
            "from role " +
            "where is_deleted = 0 and id in (select role_id from user_role where user_id=#{userId})")
    List<RoleEntity> findRolesByUserId(@Param("userId") Long userId);

    /**
     * 删除用户查询角色
     */
    @Delete("delete from user_role where user_id=#{userId}")
    void deleteRolesByUserId(Long userId);

    /**
     * 插入用户角色
     */
    @Insert("<script>" +
            "insert into user_role(user_id,role_id) values " +
            "<foreach collection='list' item='item' index='index' separator=','>" +
            "(#{userId} , #{item})" +
            "</foreach>" +
            "</script>")
    void insertRolesByUserId(@Param("userId") Long userId, @Param("list") List<Long> roleIds);
}