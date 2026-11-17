package com.example.SpringCloudStudy.microservice.rbac.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.example.SpringCloudStudy.common.microservice.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 数据字典
 * </p>
 *
 * @author star zhang
 * @since  / 
 */
@Getter
@Setter
@TableName("dict")
@Schema(name = "DictEntity", description = "数据字典")
public class DictEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "字典ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "字典英文名称")
    @TableField("dictname")
    private String dictname;

    @Schema(description = "字典中文标签")
    @TableField("label")
    private String label;

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
}
