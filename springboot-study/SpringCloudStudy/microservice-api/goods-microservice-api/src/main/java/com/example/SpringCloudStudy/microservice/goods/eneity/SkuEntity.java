package com.example.SpringCloudStudy.microservice.goods.eneity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.example.SpringCloudStudy.common.microservice.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("sku")
@Schema(name = "SkuEntity",description = "商品sku")
public class SkuEntity extends BaseEntity {

    @Schema(description = "SPUID")
    @TableField("spu_id")
    private  Long spuId;

    @Schema(description = "商品条码")
    @TableField("sn")
    private String sn;

    @Schema(description = "SKU名称")
    @TableField("name")
    private String name;

    @Schema(description = "价格（分）")
    @TableField("price")
    private Double price;

    @Schema(description = "库存数量")
    @TableField("count")
    private Integer count;

    @Schema(description = "库存预警数量")
    @TableField("alert_count")
    private Integer alertCount;

    @Schema(description = "商品图片")
    @TableField("image")
    private String image;

    @Schema(description = "商品图片列表")
    @TableField("gallery")
    private String gallery;

    @Schema(description = "类目名称")
    @TableField("category_name")
    private String categoryName;

    @Schema(description = "品牌名称")
    @TableField("brand_name")
    private String brandName;

    @Schema(description = "规格")
    @TableField("spec_items")
    private String specItems;

    @Schema(description = "销量")
    @TableField("sale_count")
        private Integer saleCount;

    @Schema(description = "商品状态 1-正常，2-下架，3-删除")
    @TableField("status")
    private Integer status;
}
