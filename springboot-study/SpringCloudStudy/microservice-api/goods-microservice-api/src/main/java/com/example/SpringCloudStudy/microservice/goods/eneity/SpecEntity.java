package com.example.SpringCloudStudy.microservice.goods.eneity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.example.SpringCloudStudy.common.microservice.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("spec")
@Schema(name = "SpecEntity",description = "商品spec")
public class SpecEntity extends BaseEntity {
    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "规格选项")
    @TableField("options")
    private String options;

    @Schema(description = "排序")
    @TableField("sort")
    private Integer sort;

    @Schema(description = "模板id")
    @TableField("template_id")
    private Long templateId;
}
