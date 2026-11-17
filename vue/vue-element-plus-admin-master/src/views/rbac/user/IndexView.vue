<script setup>
import TableComponent from "@/components/tables/TableComponent.vue";
import TreeComponent from "@/components/tree/TreeComponent.vue";
import { provide, reactive, ref } from "vue";

const userModule = import.meta.globEager("@/apis/rbac/user.js");
const roleModule = import.meta.globEager("@/apis/rbac/role.js");

/**
 * 表格参数
 */
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
  phone: {
    label: "电话",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "tel",
  },
  email: {
    label: "邮箱",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "email",
  },
  status: {
    label: "状态",
    show: true,
    editable: true,
    fixed: false,
    sortable: true,
    type: "select",
    option: [
      { label: "启用", value: 1 },
      { label: "禁用", value: 0 },
    ],
    // 格式化文本：参数 ： (row, column, cellValue, index)
    formatter: (cellValue) => {
      if (cellValue == "1") {
        return "启用";
      } else {
        return "禁用";
      }
    },
    // 自定义显示内容：参数：row
    slotter: (row) => {
      if (row.status == 1) {
        return '<span class="el-tag el-tag--success el-tag--light">启用</el-tag>';
      } else {
        return '<span class="el-tag el-tag--danger el-tag--light">禁用</el-tag>';
      }
    },
  },
  pwdResetTime: {
    label: "修改密码时间",
    show: true,
    editable: false,
    fixed: false,
    sortable: true,
    type: "email",
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
    label: "rolename",
    children: "children",
  },
});
</script>

<template>
  <el-row :gutter="20">
    <el-col :span="16">
      <TableComponent
        :column="column"
        :module="userModule"
        @tableCurrentChange="tableCurrentChange"
      />
    </el-col>
    <el-col :span="8">
      <TreeComponent :module="roleModule" :props="tree.props" />
    </el-col>
  </el-row>
</template>
