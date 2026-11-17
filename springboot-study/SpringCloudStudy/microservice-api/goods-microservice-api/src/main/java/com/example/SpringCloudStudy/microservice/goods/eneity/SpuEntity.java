package com.example.SpringCloudStudy.microservice.goods.eneity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.example.SpringCloudStudy.common.microservice.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("spu")
@Schema(name = "SpuEntity",description = "商品介绍")
public class SpuEntity extends BaseEntity {

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "副标题")
    @TableField("caption")
    private String caption;

    @Schema(description = "产品简介")
    @TableField("description")
    private String description;

    @Schema(description = "产品详情")
    @TableField("detail")
    private String detail;

    @Schema(description = "销售量")
    @TableField("sale_count")
    private Integer saleCount;

    @Schema(description = "主图")
    @TableField("image")
    private String image;

    @Schema(description = "相册列表,以逗号分隔")
    @TableField("gallery")
    private String gallery;

    @Schema(description = "标准价格")
    @TableField("price")
    private Double price;

    @Schema(description = "总库存数量")
    @TableField("stock")
    private Integer stock;

    @Schema(description = "公共规格json数据")
    @TableField("spec_items")
    private String specItems;

    @Schema(description = "公共属性json数据")
    @TableField("param_items")
    private String paramItems;

    @Schema(description = "品牌id")
    @TableField("brand_id")
    private Long brandId;

    @Schema(description = "模板id")
    @TableField("template_id")
    private Long templateId;

    @Schema(description = "一级分类id")
    @TableField("category1_id")
    private Long category1Id;

    @Schema(description = "二级分类id")
    @TableField("category2_id")
    private Long category2Id;

    @Schema(description = "三级分类id")
    @TableField("category3_id")
    private Long category3Id;

    @Schema(description = "是否人气商品")
    @TableField("is_hot")
    private Integer isHot;

    @Schema(description = "是否新品推荐")
    @TableField("is_fresh")
    private Integer isFresh;

    @Schema(description = "是否上架")
    @TableField("is_on_sale")
    private Integer isOnSale;
}
