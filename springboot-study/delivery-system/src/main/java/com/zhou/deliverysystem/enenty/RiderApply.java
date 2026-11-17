package com.zhou.deliverysystem.enenty;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("rider_apply")
public class RiderApply extends BaseEntity{
    @TableField("name")
    private String name;

    @TableField("sno")
    private String sno;

    @TableField("user_id")
    private Long userId;

    @TableField("audit_result")
    private Integer auditResult;

    @TableField("reason")
    private String reason;

    @TableField("status")
    private int status;

    @TableField("preferences")
    private String preferences;

    @TableField("profession")
    private String profession;

    @TableField("identification")
    private String identification;
}
