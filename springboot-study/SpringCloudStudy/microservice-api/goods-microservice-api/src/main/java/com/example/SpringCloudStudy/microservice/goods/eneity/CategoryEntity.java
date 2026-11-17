package com.example.SpringCloudStudy.microservice.goods.eneity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.SpringCloudStudy.common.microservice.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("category")
@Schema(name = "CategoryEntity",description = "商品类别")
public class CategoryEntity extends BaseEntity {

    @Schema(description = "上级ID")
    @TableField("template_id")
    private Long templateId;

    @Schema(description = "模板ID")
    @TableField("pid")
    private Long pid;

    @Schema(description = "分类名称")
    @TableField("name")
    private String name;

    @Schema(description = "商品数量")
    @TableField("count")
    private Integer count;


    @Schema(description = "排序")
    @TableField("sort")
    private Integer sort;

    @Schema(description = "是否隐藏")
    @TableField("is_hidden")
    private Integer isHidden;


    @Schema(description = "是否导航")
    @TableField("is_menu")
    private Integer isMenu;
}
