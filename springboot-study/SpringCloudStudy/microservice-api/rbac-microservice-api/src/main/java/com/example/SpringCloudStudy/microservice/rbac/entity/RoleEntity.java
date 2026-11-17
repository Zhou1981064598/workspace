package com.example.SpringCloudStudy.microservice.rbac.entity;

import com.baomidou.mybatisplus.annotation.*;

import com.example.SpringCloudStudy.common.microservice.base.BaseEntity;
import com.example.SpringCloudStudy.common.microservice.pojo.Paging;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author star zhang
 * @since  / 
 */
@Getter
@Setter
@TableName("role")
@Schema(name = "RoleEntity", description = "角色表")
public class RoleEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @JsonView({SimpleView.class})
    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @JsonView({SimpleView.class})
    @Schema(description = "角色名称")
    @TableField("rolename")
    private String rolename;

    @Schema(description = "角色级别")
    @TableField("`level`")
    private Byte level;

    @Schema(description = "是否逻辑删除：1-删除；0-未删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

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

    @JsonView({SimpleView.class})
    @Schema(description = "是否具有该权限")
    @TableField(exist = false)
    private Boolean isChecked;

    // 简单试图
    public interface SimpleView extends Paging.PagingView {}
}
