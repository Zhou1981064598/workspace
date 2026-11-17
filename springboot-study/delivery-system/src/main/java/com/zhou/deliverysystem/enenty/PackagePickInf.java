package com.zhou.deliverysystem.enenty;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("package_pick_inf")
public class PackagePickInf extends BaseEntity{
    @TableField("name")
    private String name;

    @TableField("user_id")
    private Long userId;

    @TableField("phone")
    private String phone;

    @TableField(exist = false)
    private Long missionId;

    @TableField(exist = false)
    private String orderNo;

    @TableField(exist = false)
    private Long courierId;

    @TableField("size")
    private String size;

    @TableField("type")
    private String type;

    @TableField("status")
    private int status;

    @TableField("order_id")
    private Long orderId;

    @TableField("delivery_num")
    private String deliveryNum;

    @TableField("message")
    private String message;

    @TableField("id_code")
    private String idCode;

    @TableField("pickup_address")
    private String pickupAddress;

    @TableField("accept_address")
    private String acceptAddress;

    @TableField("remarks")
    private String remarks;


}
