package com.zhou.deliverysystem.enenty;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("evaluate")
public class Evaluate extends BaseEntity{
    @TableField(exist = false)
    private Long orderId;

    @TableField(exist = false)
    private Double evaluationScore;

    @TableField("user_id")
    private  Long userId;

    @TableField("courier_id")
    private Long courierId;

    @TableField("score")
    private Double score;

    @TableField("comment")
    private String comment;

    @TableField("order_no")
    private String orderNo;
}
