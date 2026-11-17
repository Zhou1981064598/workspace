package com.example.SpringCloudStudy.microservice.rbac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.SpringCloudStudy.common.microservice.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author star zhang
 * @since  / 
 */
@Getter
@Setter
@TableName("user")
@Schema(name = "UserEntity", description = "系统用户")
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户名")
    @TableField("username")
    private String username;

    @JsonIgnore
    @Schema(description = "密码")
    @TableField("`password`")
    private String password;

    @Schema(description = "昵称")
    @TableField("nickname")
    private String nickname;

    @Schema(description = "性别：男；女")
    @TableField("gender")
    private String gender;

    @Schema(description = "手机号码")
    @TableField("phone")
    private String phone;

    @Schema(description = "邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "头像地址")
    @TableField("avatar_name")
    private String avatarName;

    @Schema(description = "头像真实路径")
    @TableField("avatar_path")
    private String avatarPath;

    @Schema(description = "是否逻辑删除：1-删除；0-未删除")
    @TableField("is_deleted")
    private Boolean isDeleted;

    @Schema(description = "状态：1-启用；0-禁用")
    @TableField("`status`")
    private Byte status;

    @Schema(description = "修改密码的时间")
    @TableField("pwd_reset_time")
    private LocalDateTime pwdResetTime;

    @Schema(description = "创建者")
    @TableField("create_by")
    private String createBy;

    @Schema(description = "更新者")
    @TableField("update_by")
    private String updateBy;

    @Schema(description = "创建日期")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
