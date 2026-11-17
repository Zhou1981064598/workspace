package com.example.SpringCloudStudy.microservice.rbac.entity;

import com.baomidou.mybatisplus.annotation.*;

import com.example.SpringCloudStudy.common.microservice.base.BaseEntity;
import com.example.SpringCloudStudy.common.microservice.pojo.Paging;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 系统菜单
 * </p>
 *
 * @author star zhang
 * @since /
 */
@Getter
@Setter
@TableName("menu")
@Schema(name = "MenuEntity", description = "系统菜单")
public class MenuEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @JsonView({SimpleView.class})
    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "上级菜单")
    @TableField("pid")
    private Long pid;

    @Schema(description = "排序")
    @TableField("sort")
    private Integer sort;

    @Schema(description = "菜单类型:0-顶部菜单；1-左侧菜单；2-页面资源；3-外联")
    @TableField("`type`")
    private String type;

    @Schema(description = "菜单（路由）名称")
    @TableField("`name`")
    private String name;

    @Schema(description = "菜单（路由）地址：user/page")
    @TableField("`path`")
    private String path;

    @Schema(description = "菜单（路由）父级映射地址：user/page")
    @TableField("redirect")
    private String redirect;

    @Schema(description = "菜单（路由）组件：components/icons/index")
    @TableField("`component`")
    private String component;

    @Schema(description = "菜单（路由）图标")
    @TableField("meta_icon")
    private String metaIcon;

    @JsonView({SimpleView.class})
    @Schema(description = "菜单（路由）标题")
    @TableField("meta_title")
    private String metaTitle;

    @Schema(description = "微服务模块名称")
    @TableField("module")
    private String module;

    @Schema(description = "菜单权限：user:page")
    @TableField("permission")
    private String permission;

    @Schema(description = "外链链接地址")
    @TableField("href")
    private String href;

    @Schema(description = "后端mapping地址")
    @TableField("uri")
    private String uri;

    @Schema(description = "是否隐藏：1-删除；0-未删除")
    @TableField("is_hidden")
    private Boolean isHidden;

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

    @Schema(description = "是否具有子菜单")
    @TableField(exist = false)
    private Boolean hasChildren = true;

    @JsonView({SimpleView.class})
    @Schema(description = "子菜单")
    @TableField(exist = false)
    private List<MenuEntity> children = Collections.emptyList();

    @JsonView({SimpleView.class})
    @Schema(description = "是否具有该权限")
    @TableField(exist = false)
    private Boolean isChecked;

    // 简单试图
    public interface SimpleView extends Paging.PagingView {}
}
