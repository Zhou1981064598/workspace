<script setup>
import TableComponent from "@/components/tables/TableComponent.vue";
import TreeComponent from "@/components/tree/TreeComponent.vue";
import { provide, reactive, ref } from "vue";

const roleModule = import.meta.globEager("@/apis/rbac/role.js");
const authModule = import.meta.globEager("@/apis/rbac/menu.js");

const column = {
  id: {
    label: "ID", // 表头显示文字
    show: false, // 是否显示
    editable: false, // 是否可编辑
    fixed: false, // 表格的列是否固定：可选 true | false | left | right
    sortable: true, // 是否可排序
    type: "number", // 字段类型
  },
  rolename: {
    label: "角色名称",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "text",
  },
  level: {
    label: "角色级别",
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

/**
 * 选中表格行的 id 值
 */
const tableId = ref(null);

/**
 * 将参数传递到子组件中
 */
provide("tableId", tableId);

/**
 * 表格行选择后执行的函数
 * @param {*} row /
 */
const tableCurrentChange = (row) => {
  tableId.value = row.id;
};

/**
 * 树的参数
 */
const tree = reactive({
  checked: [],
  props: {
    label: "metaTitle",
    children: "children",
  },
});
</script>

<template>
  <el-row :gutter="20">
    <el-col :span="16">
      <TableComponent
        :column="column"
        :module="roleModule"
        @tableCurrentChange="tableCurrentChange"
      />
    </el-col>
    <el-col :span="8">
      <TreeComponent :module="authModule" :props="tree.props" />
    </el-col>
  </el-row>
</template>
