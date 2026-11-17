package com.zhou.springbootrbac.eneity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role")
public class RoleEntity extends BaseEntity{
    @TableField("rolename")
    private String roleName;
}
