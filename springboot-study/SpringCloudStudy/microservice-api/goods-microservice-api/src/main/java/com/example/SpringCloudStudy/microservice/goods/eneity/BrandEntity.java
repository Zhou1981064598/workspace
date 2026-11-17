package com.example.SpringCloudStudy.microservice.goods.eneity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.SpringCloudStudy.common.microservice.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("brand")
@Schema(name = "BrandEntity", description = "品牌表")
public class BrandEntity extends BaseEntity {
    @Schema(description = "品牌名称")
    @TableField("name")
    private String name;

    @Schema(description = "品牌图片地址")
    @TableField("image")
    private String image;

    @Schema(description = "品牌的首字母")
    @TableField("letter")
    private String letter;

    @Schema(description = "排序")
    @TableField("sort")
    private int sort;
}
