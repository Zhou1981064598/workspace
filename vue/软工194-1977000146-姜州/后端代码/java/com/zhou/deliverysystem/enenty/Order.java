package com.zhou.deliverysystem.enenty;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@TableName("`order`")
@EqualsAndHashCode(exclude = "createdBy")
public class Order {

    @TableField("order_no")
    private String orderNo;

    @TableField("type")
    private String type;

    @TableField("status")
    private int status;

    @TableField("user_id")
    private Long userId;

    @TableField("courier_id")
    private Long courierId;

    @TableField("inf_id")
    private Long infId;

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
