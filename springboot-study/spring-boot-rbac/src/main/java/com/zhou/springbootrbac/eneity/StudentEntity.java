package com.zhou.springbootrbac.eneity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("student")
public class StudentEntity extends BaseEntity  {
    @TableField(value = "sno")
    private Long sNo;

    @TableField("spassword")
    private String sPassword;

    @TableField("sname")
    private  String sName;

    @TableField("ssex")
    private  String sSex;

    @TableField("sage")
    private int sAge;

    @TableField("sdept")
    private String sDept;

    @TableField("power")
    private String power;
}
