package com.example.SpringCloudStudy.microservice.rbac.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SpringCloudStudy.microservice.rbac.entity.MenuEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 系统菜单 Mapper 接口
 * </p>
 *
 * @author star zhang
 * @since /
 */
@Mapper
public interface MenuRepository extends BaseMapper<MenuEntity> {
    @Select("<script>" +
            "select id,uri from menu where id in (select menu_id from role_menu where role_id in " +
            "<foreach item='id' index='index' collection='list' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            ")" +
            "</script>")
    List<MenuEntity> findMenusByRoleIds(List<Long> ids);

    @Select("<script>" +
            "select id,pid,type,name,path,uri,redirect,component,meta_icon,meta_title " +
            "from menu " +
            "where type=1 and is_deleted=0 " +
            "<if test='module != null'>" +
            "and module=#{module}" +
            "</if>" +
            "</script>")
    List<MenuEntity> findMenusByModule(String module);

    @Select("<script>" +
            "select id,pid,type,name,path,uri,redirect,component,meta_icon,meta_title " +
            "from menu " +
            "where pid=#{pid} and is_deleted=#{isRecovery}" +
            "</script>")
    List<MenuEntity> findMenusByPid(@Param("pid") long pid, @Param("isRecovery") boolean isRecovery);

    /**
     * 查询所有菜单
     *
     * @return /
     */
    @Select("select id,pid,meta_title " +
            "from menu " +
            "where is_deleted = 0 and module = #{module}")
    List<MenuEntity> findSimpleMenusByModule(String module);

    /**
     * 根据角色查询菜单
     *
     * @param roleId /
     * @return /
     */
    @Select("select id,pid,meta_title " +
            "from menu " +
            "where is_deleted = 0 and id in (select menu_id from role_menu where role_id=#{roleId})")
    List<MenuEntity> findMenusByRoleIdAndModule(Long roleId);

    /**
     * 删除用户查询权限
     */
    @Delete("delete from role_menu where role_id=#{roleId}")
    void deleteMenusByRoleId(Long roleId);

    /**
     * 插入用户权限
     */
    @Insert("<script>" +
            "insert into role_menu(role_id,menu_id) values " +
            "<foreach collection='list' item='item' index='index' separator=','>" +
            "(#{role_id} , #{item})" +
            "</foreach>" +
            "</script>")
    void insertMenusByRoleId(@Param("role_id") Long role_id, @Param("list") List<Long> menuIds);
}
