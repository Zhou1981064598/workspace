package com.zhou.deliverysystem.enenty;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@TableName("mission")
@Data
public class Mission extends BaseEntity{
    @TableField("courier_id")
    private Long courierId;

    @TableField("status")
    private int status;

    @TableField("type")
    private String type;

    @TableField("order_id")
    private Long orderId;

    @TableField("inf_id")
    private Long infId;


}
