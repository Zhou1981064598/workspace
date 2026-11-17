package com.zhou.springbootrbac.eneity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class UserEntity extends BaseEntity{
    @TableField("username")
    private String username;

    @TableField("password")
    private String password;
}
