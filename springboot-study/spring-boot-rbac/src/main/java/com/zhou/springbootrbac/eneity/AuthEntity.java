package com.zhou.springbootrbac.eneity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("auth")
public class AuthEntity extends BaseEntity{
    @TableField("authname")
    private String authName;

    @TableField("permission")
    private String permission;

    @TableField("uri")
    private String uri;
}
