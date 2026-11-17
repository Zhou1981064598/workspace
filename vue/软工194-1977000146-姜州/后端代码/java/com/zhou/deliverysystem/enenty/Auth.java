package com.zhou.deliverysystem.enenty;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("auth")
public class Auth extends BaseEntity{
    @TableField("authname")
    private String authname;

    @TableField("permission")
    private String permission;

    @TableField("uri")
    private String uri;
}
