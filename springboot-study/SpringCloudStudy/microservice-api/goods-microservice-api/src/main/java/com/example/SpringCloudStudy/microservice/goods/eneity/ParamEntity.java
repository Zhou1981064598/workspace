package com.example.SpringCloudStudy.microservice.goods.eneity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.example.SpringCloudStudy.common.microservice.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("param")
@Schema(name = "ParamEntity",description = "商品参数")
public class ParamEntity extends BaseEntity {

    @Schema(description = "参数名称")
    @TableField("name")
    private String name;

    @Schema(description = "参数选项")
    @TableField("`option`")
    private String option;

    @Schema(description = "参数类型")
    @TableField("category")
    private String category;

    @Schema(description = "模板ID")
    @TableField("template_id")
    private Long templateId;

}
