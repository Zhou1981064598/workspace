<script setup>
import TableComponent from "@/components/tables/TableComponent.vue";
const module = import.meta.globEager("@/apis/rbac/user.js");

const column = {
  id: {
    label: "ID", // 表头显示文字
    show: false, // 是否显示
    editable: false, // 是否可编辑
    fixed: false, // 表格的列是否固定：可选 true | false | left | right
    sortable: true, // 是否可排序
    type: "number", // 字段类型
  },
  username: {
    label: "用户名",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  nickname: {
    label: "昵称",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  gender: {
    label: "性别",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "select",
    option: [
      { label: "男", value: "男" },
      { label: "女", value: "女" },
    ],
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
