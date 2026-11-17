<script setup>
import { ElMessage } from "element-plus";
import { computed, onActivated, reactive } from "vue";

/**
 * 设置组件的自定义属性
 */
const props = defineProps({
  module: Object, // 对应的 API 接口文件
  column: Object, // 对于的表格中显示的数据列
  recovery: Boolean, // 是否是回收站页面
  tree: Boolean, // 是否使用树形表格
  treeProps: Object, // 树形表格的参数
});

/**
 * 定义事件
 */
const emit = defineEmits(["tableCurrentChange", "tableSelectionChange"]);

/**
 * 展开类型表格中展开的字段
 * 默认获取 column 中的第一个
 */
const rowId = computed(() => {
  if (Object.keys(props.column).length > 0) {
    return Object.keys(props.column)[0];
  } else {
    return "";
  }
});

/**
 * 加载对应的 API 接口文件中的接口函数
 */
const modules = [];
for (const path in props.module) {
  modules.push(props.module[path]);
}
const { page, del, edit, plus, recovery } = modules[0];

/**
 * ************************** 表格 **************************
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
 * 添加或者修改对话框的数据
 */
const dialog = reactive({
  plusAndEditDialogVisible: false,
  plusAndEditForm: {}, // 表单数据，当前行数据的拷贝
  title: "", // 标题：添加 | 修改
  rules: [],
});

/**
 * 分页加载数据
 */
const getPage = () => {
  page({
    currentPage: pagination.currentPage,
    pageSize: pagination.pageSize,
    isRecovery: props.recovery,
  }).then((response) => {
    const { data } = response;
    // 设置表格的数据集合
    table.list = data.list;
    // 设置分页相关的参数
    pagination.total = data.total;
  });
};

/**
 * 懒加载表格数据
 * @param {*} row /
 * @param {*} treeNode /
 * @param {*} resolve /
 */
const load = (row, treeNode, resolve) => {
  page({
    currentPage: pagination.currentPage,
    pageSize: pagination.pageSize,
    isRecovery: props.recovery,
    pid: row.id,
  }).then((response) => {
    const { data } = response;
    // 设置表格的数据集合
    resolve(data.list);
    // 设置分页相关的参数
    pagination.total = data.total;
  });
};

/**
 * 点击当前行的时候存储当前行的数据
 * @param {*} rows
 */
const currentChange = (rows) => {
  table.current = rows;
  emit("tableCurrentChange", rows);
};

/**
 * 表格多选的时候存储选择行的数据
 * @param {*} rows
 */
const selectionChange = (rows) => {
  table.selected = rows;
  emit("tableSelectionChange", rows);
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
 * 修改当前行
 * @param {*} scope
 */
const modify = (scope) => {
  dialog.plusAndEditDialogVisible = true;
  dialog.title = "修改";
  table.current = scope.row;
  // 拷贝当前行的数据到 dialog.plusAndEditForm 中
  dialog.plusAndEditForm = JSON.parse(JSON.stringify(table.current));
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
  dialog.plusAndEditDialogVisible = true;
  dialog.title = "添加";
  // 将表单数据置为空
  dialog.plusAndEditForm = {};
};

/**
 * 修改或者添加提交函数
 */
const plusAndEditSubmit = () => {
  dialog.plusAndEditDialogVisible = false; // 关闭对话框
  if (dialog.title === "添加") {
    plus(dialog.plusAndEditForm).then((response) => {
      const { message } = response;
      ElMessage({
        type: "success",
        showClose: true,
        message: message,
        duration: 3000,
      });
      getPage();
    });
  } else if (dialog.title === "修改") {
    edit(dialog.plusAndEditForm).then((response) => {
      const { message } = response;
      ElMessage({
        type: "success",
        showClose: true,
        message: message,
        duration: 3000,
      });
      getPage();
    });
  }
};

/**
 * 恢复当前行
 * @param {*} scope
 */
const recoveryOne = (scope) => {
  table.current = scope.row;
  recovery([{ id: scope.row.id }]).then((response) => {
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
 * 恢复所选行的数据
 */
const recoveryAll = () => {
  recovery(
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
 * ************************** 分页 **************************
 */

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

/**
 * 当页面被激活（被切换到显示）
 */
onActivated(() => {
  getPage();
});
</script>

<template>
  <!-- 表格上方的按钮组 -->
  <el-button-group v-if="!props.recovery">
    <el-button type="primary" @click="add"> 添加 </el-button>
    <el-button type="primary" @click="removeSelect"> 删除 </el-button>
  </el-button-group>
  <el-button-group v-else>
    <el-button type="primary" @click="recoveryAll"> 全部恢复 </el-button>
  </el-button-group>

  <!-- 表格数据显示 -->
  <el-table
    :load="load"
    :border="true"
    :stripe="true"
    :row-key="rowId"
    :lazy="props.tree"
    :data="table?.list"
    :tree-props="props.treeProps"
    :highlight-current-row="true"
    @current-change="currentChange"
    @selection-change="selectionChange"
  >
    <el-table-column fixed type="selection" width="40" />

    <template v-for="(value, key) in props.column" :key="key">
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

    <el-table-column
      fixed="right"
      label="操作"
      width="100"
      v-if="!props.recovery"
    >
      <template #default="scope">
        <el-button link type="danger" size="small" @click="remove(scope)">
          删除
        </el-button>
        <el-button link type="success" size="small" @click="modify(scope)">
          编辑
        </el-button>
      </template>
    </el-table-column>

    <el-table-column v-else fixed="right" label="操作" width="80">
      <template #default="scope">
        <el-button link type="success" size="small" @click="recoveryOne(scope)">
          恢复
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

  <!-- 添加和修改对话框 -->
  <el-dialog
    v-model="dialog.plusAndEditDialogVisible"
    :title="dialog.title"
    width="80%"
    align-center
    draggable
  >
    <el-form
      ref="plusAndEditFormRef"
      :model="dialog.plusAndEditForm"
      :rules="dialog.rules"
      label-width="80px"
      label-position="left"
      status-icon
    >
      <el-row :gutter="20">
        <template v-for="(value, key) in props.column" :key="key">
          <el-col v-if="value.editable" :span="12">
            <el-form-item :label="value.label" prop="key">
              <el-select
                v-if="value.type === 'select'"
                v-model="dialog.plusAndEditForm[key]"
              >
                <el-option
                  v-for="item in value.option"
                  :key="item.label"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
              <el-input
                v-else
                v-model="dialog.plusAndEditForm[key]"
                :type="value.type"
              />
            </el-form-item>
          </el-col>
        </template>
      </el-row>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="plusAndEditSubmit">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style>
.el-button-group {
  padding: 10px 0;
}
.el-table {
  width: 100%;
}
</style>
