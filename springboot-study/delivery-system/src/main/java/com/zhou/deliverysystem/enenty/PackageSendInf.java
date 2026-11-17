package com.zhou.deliverysystem.enenty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * @TableName package_send_inf
 */
@TableName(value ="package_send_inf")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageSendInf extends BaseEntity implements Serializable {


    @TableField("user_id")
    private Long userId;

    @TableField(exist = false)
    private Long courierId;

    @TableField(exist = false)
    private Long missionId;

    @TableField(exist = false)
    private String orderNo;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;


    @TableField("pickup_address")
    private String pickupAddress;

    @TableField("weight")
    private String weight;


    @TableField("send_address")
    private String sendAddress;

    @TableField("send_type")
    private String sendType;

    @TableField("order_id")
    private Long orderId;

   @TableField("type")
    private String type;

    @TableField("status")
    private Integer status;


    @TableField("remarks")
    private String remarks;


    @TableField("accept_name")
    private String acceptName;

    @TableField("accept_address")
    private String acceptAddress;

    @TableField("accept_phone")
    private String acceptPhone;

    @TableField("total_amount")
    private Double totalAmount;

    @TableField("uuencode")
    private String uuencode;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}