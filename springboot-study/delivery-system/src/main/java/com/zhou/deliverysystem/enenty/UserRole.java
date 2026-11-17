package com.zhou.deliverysystem.enenty;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_role")
public class UserRole extends BaseEntity{

    @TableField("user_id")
    private Long userId;

    @TableField("role_id")
    private Long roleId;
}
