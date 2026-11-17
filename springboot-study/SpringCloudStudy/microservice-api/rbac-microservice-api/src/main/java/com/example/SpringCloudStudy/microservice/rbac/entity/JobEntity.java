package com.example.SpringCloudStudy.microservice.rbac.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.example.SpringCloudStudy.common.microservice.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 岗位
 * </p>
 *
 * @author star zhang
 * @since  / 
 */
@Getter
@Setter
@TableName("job")
@Schema(name = "JobEntity", description = "岗位")
public class JobEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "岗位名称")
    @TableField("jobname")
    private String jobname;

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
