package com.zhou.deliverysystem.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zhou.deliverysystem.enenty.BaseEntity;
import com.zhou.deliverysystem.enenty.PackagePickInf;
import lombok.Data;

@Data
public class PackagePickOrderDetil  extends PackagePickInf {
    private String name;

    private Long userId;

    private String phone;

    private String size;

    private String type;

    private int status;

    private Long infId;

    private String deliveryNum;

    private String message;

    private String idCode;

    private String pickupAddress;

    private String acceptAddress;

    private String remarks;

    private String orderNo;

    private Long courierId;
}
