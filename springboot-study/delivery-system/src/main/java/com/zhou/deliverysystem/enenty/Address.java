package com.zhou.deliverysystem.enenty;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName address
 */
@TableName(value ="address")
@Data
public class Address extends BaseEntity implements Serializable  {

    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;

    @TableField(value = "user_id")
    private Long userId;
    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    @TableField(value = "building")
    private String building;

    /**
     * 宿舍号
     */
    @TableField(value = "dormitory_no")
    private Long dormitoryNo;


    /**
     *默认地址：1-是；0-否
     */
    @TableField(value = "default_address")

    private Integer defaultAddress;

    /**
     * 是否逻辑删除：-1删除；0未删除
     */

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}