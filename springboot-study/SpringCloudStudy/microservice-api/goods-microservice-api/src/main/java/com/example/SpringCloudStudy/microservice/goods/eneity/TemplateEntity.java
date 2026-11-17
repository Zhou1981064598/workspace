package com.example.SpringCloudStudy.microservice.goods.eneity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.SpringCloudStudy.common.microservice.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("template")
@Schema(name = "TemplateEntity", description = "商品模板表")
public class TemplateEntity extends BaseEntity {
    @Schema(description = "模板名称")
    @TableField("name")
    private String name;

    @Schema(description = "规格数量")
    @TableField("spec_num")
    private Long specNum;

    @Schema(description = "参数数量")
    @TableField("param_num")
    private Long paramNum;
}
