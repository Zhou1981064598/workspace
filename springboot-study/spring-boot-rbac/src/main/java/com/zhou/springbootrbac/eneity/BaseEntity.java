package com.zhou.springbootrbac.eneity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableLogic
    @TableField("is_deleted")
    private Boolean isDeleted;

    @TableField("create_by")
    private String createBy;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_by")
    private String  updateBy;

    @TableField("update_time")
    private LocalDateTime updateTime;


}
