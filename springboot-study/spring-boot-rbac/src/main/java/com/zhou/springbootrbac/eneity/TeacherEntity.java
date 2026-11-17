package com.zhou.springbootrbac.eneity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("teacher")
public class TeacherEntity {
    @TableId("tno")
    private Long tNo;

    @TableField("tname")
    private String tName;

    @TableField("tsex")
    private char tSex;

    @TableField("tage")
    private int tAge;

    @TableField("teb")
    private String teb;

    @TableField("tpt")
    private String tpt;

    @TableField("cno1")
    private  Long cNo1;

    @TableField("cno2")
    private  Long cNO2;

    @TableField("cno3")
    private  Long cNo3;

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
