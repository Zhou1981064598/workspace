<script setup>
import TableComponent from "@/components/tables/TableComponent.vue";
const module = import.meta.globEager("@/apis/rbac/dept.js");

const column = {
  id: {
    label: "ID", // 表头显示文字
    show: false, // 是否显示
    editable: false, // 是否可编辑
    fixed: false, // 表格的列是否固定：可选 true | false | left | right
    sortable: true, // 是否可排序
    type: "number", // 字段类型
  },
  pid: {
    label: "上级ID",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "number",
  },
  deptname: {
    label: "部门名称",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  countSub: {
    label: "子部门数目",
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
  <TableComponent :module="module" :column="column" :recovery="true" />
</template>
