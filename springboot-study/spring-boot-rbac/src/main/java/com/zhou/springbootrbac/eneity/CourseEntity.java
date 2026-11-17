package com.zhou.springbootrbac.eneity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@TableName("course")
public class CourseEntity {
    @TableId(value = "cno")
    private Long cNo;

    @TableField("cname")
    private String cName;

    @TableField("cpno")
    private Long cPNO;

    @TableField("ccredit")
    private Float cCredit;

    @TableLogic
    @TableField("is_deleted")
    private boolean isDeleted;

    @TableField("create_by")
    private String createBy;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_by")
    private String  updateBy;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
