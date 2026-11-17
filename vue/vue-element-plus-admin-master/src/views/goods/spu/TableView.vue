<script setup>
import { inject, onActivated, reactive } from "vue";
import { page, del } from "../../../apis/goods/spu";
import { column } from "./column";
import { ElMessage } from "element-plus";
//获取父组件传递过来的数据
const isEditView = inject("isEditView");
/**
 * 分页加载数据
 */
const getPage = () => {
  page({
    currentPage: pagination.currentPage,
    pageSize: pagination.pageSize,
    isRecovery: false,
  }).then((response) => {
    const { data } = response;
    // 设置表格的数据集合
    table.list = data.list;
    // 设置分页相关的参数
    pagination.total = data.total;
  });
};
onActivated(() => {
  getPage();
});

/**
 * 定义事件
 */
/**
 * 表格相关数据
 */
const table = reactive({
  list: [], // 表格数据
  current: {}, // 当前选中行的数据
  selected: [], // 多选行的数据
});

/**
 * 点击当前行的时候存储当前行的数据
 * @param {*} rows
 */
const currentChange = (rows) => {
  table.current = rows;
};

/**
 * 表格多选的时候存储选择行的数据
 * @param {*} rows
 */
const selectionChange = (rows) => {
  table.selected = rows;
};
/**
 * 删除当前行
 * @param {*} scope
 */
const remove = (scope) => {
  del([{ id: scope.row.id }]).then((response) => {
    const { message } = response;
    ElMessage({
      type: "success",
      showClose: true,
      message: message,
      duration: 3000,
    });
    // 获取分页数据
    getPage();
  });
};

/**
 * 删除所选行的数据
 */
const removeSelect = () => {
  del(
    table.selected.map((item) => {
      return { id: item.id };
    })
  ).then((response) => {
    const { message } = response;
    ElMessage({
      type: "success",
      showClose: true,
      message: message,
      duration: 3000,
    });
    // 获取分页数据
    getPage();
  });
};

/**
 * 添加数据函数
 */
const add = () => {
  isEditView.value = true;
};

/**
 * 修改当前行
 * @param {*} scope
 */
const modify = (scope) => {
  console.log(scope);
  isEditView.value = true;
};
/**
 * 分页插件相关数据
 */
const pagination = reactive({
  currentPage: 1, // 当前页
  pageSize: 5, // 每页条数
  total: 0, // 总条数
  pageSizes: [5, 10, 15, 50], // 可选每页条数
  layout: "total, sizes, prev, pager, next, jumper", // 分页按钮选项
});

/**
 * 改变分页每页显示条数
 */
const paginationSizeChange = () => {
  getPage();
};

/**
 * 切换分页
 */
const paginationCurrentChange = () => {
  getPage();
};
</script>
<template>
  <!-- 表格上方的按钮组 -->
  <el-button-group>
    <el-button type="primary" @click="add"> 添加 </el-button>
    <el-button type="primary" @click="removeSelect"> 删除 </el-button>
  </el-button-group>

  <!-- 表格数据显示 -->
  <el-table
    :border="true"
    :stripe="true"
    :data="table?.list"
    :highlight-current-row="true"
    @current-change="currentChange"
    @selection-change="selectionChange"
  >
    <el-table-column fixed type="selection" width="40" />

    <template v-for="(value, key) in column" :key="key">
      <template v-if="value.show">
        <el-table-column
          :fixed="value.fixed"
          :prop="key"
          :label="value.label"
          :sortable="value.sortable"
          :formatter="value.formatter"
        >
          <template #default="scope">
            <div v-if="value.slotter" v-html="value.slotter(scope.row)" />
          </template>
        </el-table-column>
      </template>
    </template>

    <el-table-column fixed="right" label="操作" width="100">
      <template #default="scope">
        <el-button link type="danger" size="small" @click="remove(scope)">
          删除
        </el-button>
        <el-button link type="success" size="small" @click="modify(scope)">
          编辑
        </el-button>
      </template>
    </el-table-column>
  </el-table>

  <!-- 分页 -->
  <el-pagination
    v-model:currentPage="pagination.currentPage"
    v-model:page-size="pagination.pageSize"
    :page-sizes="pagination.pageSizes"
    :layout="pagination.layout"
    :total="pagination.total"
    @size-change="paginationSizeChange"
    @current-change="paginationCurrentChange"
  />
</template>
