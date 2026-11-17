<script setup>
import TableComponent from "@/components/tables/TableComponent.vue";
const module = import.meta.globEager("@/apis/rbac/menu.js");

const column = {
  id: {
    label: "ID", // 表头显示文字
    show: true, // 是否显示
    editable: false, // 是否可编辑
    fixed: false, // 表格的列是否固定：可选 true | false | left | right
    sortable: true, // 是否可排序
    type: "number", // 字段类型
  },
  // pid: {
  //   label: "上级菜单",
  //   show: true,
  //   editable: true,
  //   fixed: false,
  //   sortable: true,
  //   type: "number",
  // },
  sort: {
    label: "排序",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  type: {
    label: "菜单类型",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "select",
    option: [
      { label: "顶部菜单", value: "0" },
      { label: "左侧菜单", value: "1" },
      { label: "页面资源", value: "2" },
      { label: "外联", value: "3" },
    ],
  },
  name: {
    label: "菜单（路由）名称电话",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  path: {
    label: "菜单（路由）地址",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  redirect: {
    label: "菜单（路由）父级映射地址",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  component: {
    label: "菜单（路由）组件",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  meta_icon: {
    label: "菜单（路由）图标",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  meta_title: {
    label: "菜单（路由）标题",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  module: {
    label: "微服务模块名称",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  permission: {
    label: "菜单权限",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  href: {
    label: "外链链接地址",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  uri: {
    label: "后端mapping地址",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  // isDeleted: {
  //   label: "是否删除",
  //   show: true,
  //   editable: false,
  //   fixed: false,
  //   sortable: true,
  //   type: "select",
  //   option: [
  //     { label: "删除", value: true },
  //     { label: "正常", value: false },
  //   ],
  //   // 参数 ： (row, column, cellValue, index)
  //   formatter: (cellValue) => {
  //     if (cellValue == true) {
  //       return "删除";
  //     } else {
  //       return "正常";
  //     }
  //   },
  // },
  // createBy: {
  //   label: "创建者",
  //   show: true,
  //   editable: false,
  //   fixed: false,
  //   sortable: true,
  // },
  // updateBy: {
  //   label: "修改者",
  //   show: true,
  //   editable: false,
  //   fixed: false,
  //   sortable: true,
  // },
  // createTime: {
  //   label: "创建时间",
  //   show: true,
  //   editable: false,
  //   fixed: false,
  //   sortable: true,
  // },
  // updateTime: {
  //   label: "修改时间",
  //   show: true,
  //   editable: false,
  //   fixed: false,
  //   sortable: true,
  // },
};
</script>

<template>
  <TableComponent
    :module="module"
    :column="column"
    :recovery="true"
    :tree="true"
  />
</template>
