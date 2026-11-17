<script setup>
import TableComponent from "@/components/tables/TableComponent.vue";
const module = import.meta.globEager("@/apis/goods/sku.js");

const column = {
  id: {
    label: "商品ID", // 表头显示文字
    show: true, // 是否显示
    editable: false, // 是否可编辑
    fixed: false, // 表格的列是否固定：可选 true | false | left | right
    sortable: true, // 是否可排序
    type: "number", // 字段类型
  },
  spuId: {
    label: "SPUID", // 表头显示文字
    show: true, // 是否显示
    editable: false, // 是否可编辑
    fixed: false, // 表格的列是否固定：可选 true | false | left | right
    sortable: true, // 是否可排序
    type: "number", // 字段类型
  },
  sn: {
    label: "商品条码",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  name: {
    label: "SKU名称",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  price: {
    label: "价格（分）",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "number",
  },
  count: {
    label: "库存数量",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "number",
  },
  alertCount: {
    label: "库存预警数量",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "number",
  },
  image: {
    label: "商品图片",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  gallery: {
    label: "商品图片列表",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  categoryName: {
    label: "类目名称",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  brandName: {
    label: "品牌名称",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  specItems: {
    label: "规格",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  saleCount: {
    label: "销量",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "number",
  },
  status: {
    label: "商品状态",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  isDeleted: {
    label: "是否删除",
    show: true,
    editable: false,
    fixed: false,
    sortable: true,
    type: "select",
    option: [
      { label: "删除", value: true },
      { label: "正常", value: false },
    ],
    // 参数 ： (row, column, cellValue, index)
    formatter: (cellValue) => {
      if (cellValue == true) {
        return "删除";
      } else {
        return "正常";
      }
    },
  },
  createBy: {
    label: "创建者",
    show: true,
    editable: false,
    fixed: false,
    sortable: true,
  },
  updateBy: {
    label: "修改者",
    show: true,
    editable: false,
    fixed: false,
    sortable: true,
  },
  createTime: {
    label: "创建时间",
    show: true,
    editable: false,
    fixed: false,
    sortable: true,
  },
  updateTime: {
    label: "修改时间",
    show: true,
    editable: false,
    fixed: false,
    sortable: true,
  },
};
</script>

<template>
  <TableComponent :module="module" :column="column" />
</template>
