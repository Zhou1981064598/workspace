package com.example.SpringCloudStudy.microservice.rbac.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SpringCloudStudy.microservice.rbac.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author star zhang
 * @since /
 */
@Mapper
public interface UserRepository extends BaseMapper<UserEntity> {
    /**
     * 根据用户名获取用户名和密码
     *
     * @param username /
     * @return /
     */
    @Select("select id,username,password " +
            "from user " +
            "where username=#{username};")
    UserEntity getUserByUsername(String username);

    /**
     * 根据用户名获取用户详细信息
     *
     * @param username /
     * @return /
     */
    @Select("select username,nickname,gender,phone,email,is_deleted,status,avatar_name,avatar_path,pwd_reset_time,create_by,update_by,create_time,update_time  " +
            "from user " +
            "where username=#{username};")
    UserEntity getInfoByUsername(String username);

    /**
     * 查询密码
     *
     * @param username /
     * @return /
     */
    @Select("select password " +
            "from user " +
            "where username=#{username}")
    String getPassword(String username);

    /**
     * 修改密码
     *
     * @param username /
     * @param password /
     * @return /
     */
    @Update("update user " +
            "set password=#{password}, pwd_reset_time=now() " +
            "where username=#{username}")
    int changePassword(@Param("username") String username, @Param("password") String password);

    /**
     * 修改头像
     *
     * @param username   /
     * @param avatarPath /
     * @return /
     */
    @Update("update user " +
            "set avatar_path=#{avatarPath} " +
            "where username=#{username}")
    int changeAvatar(@Param("username") String username, @Param("avatarPath") String avatarPath);
}