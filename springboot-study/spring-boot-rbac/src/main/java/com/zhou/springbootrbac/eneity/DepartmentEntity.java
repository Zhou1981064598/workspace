package com.zhou.springbootrbac.eneity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DepartmentEntity {
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
