package com.zhou.deliverysystem.enenty;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableLogic
    @TableField("is_deleted")
    private int isDeleted;

    @TableField("created_by")
    private String createdBy;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("updated_by")
    private String  updatedBy;

    @TableField("update_time")
    private LocalDateTime updateTime;


}
