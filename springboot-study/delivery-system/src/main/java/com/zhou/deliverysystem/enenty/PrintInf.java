package com.zhou.deliverysystem.enenty;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName print_inf
 */
@TableName(value ="print_inf")
@Data
public class PrintInf extends  BaseEntity implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 打印原件
     */
    private String originalFile;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 页数
     */
    private Integer page;

    /**
     * 总金额
     */
    private Double amount;

    /**
     * 是否彩印：1-是；0-否
     */
    private Integer colorPrinting;

    /**
     * 是否双面打印：1-是；0-否
     */
    private Integer duplexPrinting;

    /**
     * 备注
     */
    private String remarks;




    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}