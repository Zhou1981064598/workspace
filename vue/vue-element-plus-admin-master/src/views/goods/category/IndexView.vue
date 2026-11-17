<script setup>
import TableComponent from "@/components/tables/TableComponent.vue";
const module = import.meta.globEager("@/apis/goods/category.js");

const column = {
  id: {
    label: "分类ID", // 表头显示文字
    show: true, // 是否显示
    editable: false, // 是否可编辑
    fixed: false, // 表格的列是否固定：可选 true | false | left | right
    sortable: true, // 是否可排序
    type: "number", // 字段类型
  },
  templateId: {
    label: "上级ID", // 表头显示文字
    show: true, // 是否显示
    editable: false, // 是否可编辑
    fixed: false, // 表格的列是否固定：可选 true | false | left | right
    sortable: true, // 是否可排序
    type: "number", // 字段类型
  },
  pid: {
    label: "模板ID", // 表头显示文字
    show: true, // 是否显示
    editable: false, // 是否可编辑
    fixed: false, // 表格的列是否固定：可选 true | false | left | right
    sortable: true, // 是否可排序
    type: "number", // 字段类型
  },
  name: {
    label: "分类名称",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  count: {
    label: "商品数量",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "number",
  },
  sort: {
    label: "排序",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "number",
  },
  isHidden: {
    label: "是否隐藏",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "number",
  },
  isMenu: {
    label: "是否导航",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "number",
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
};
</script>

<template>
  <TableComponent :module="module" :column="column" />
</template>
