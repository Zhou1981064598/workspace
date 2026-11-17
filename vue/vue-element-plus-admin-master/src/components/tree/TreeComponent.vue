<script setup>
import { inject, reactive, ref, watch } from "vue";

/**
 * 设置组件的自定义属性
 */
const props = defineProps({
  module: Object, // 对应的 API 接口文件
  props: Object,
});

/**
 * 加载对应的 API 接口文件中的接口函数
 */
const modules = [];
for (const path in props.module) {
  modules.push(props.module[path]);
}
const { treeGet, treeSet } = modules[0];

/**
 * 树形结构组件
 */
const treeRef = ref();

/**
 * 树形结构参数
 */
const treeData = reactive({
  data: [],
  defaultChecked: [],
});

/**
 * 获取父组件的值
 */
const tableId = inject("tableId");

/**
 * 监听用户id，获取对应的角色
 */
watch(tableId, (newId) => {
  // 先清空所有数据
  treeData.data = [];
  treeData.defaultChecked = [];
  // 再获取新数据
  treeGet({ id: newId }).then((response) => {
    const { data } = response;
    treeData.data = data;
    getDefaultChecked(treeData.data);
  });
});

const getDefaultChecked = (datas) => {
  for (const data of datas) {
    if (data.isChecked) {
      treeData.defaultChecked.push(data.id);
    }
    if (data.children) {
      getDefaultChecked(data.children);
    }
  }
};

/**
 * 修改角色
 */
const treeEditRole = () => {
  const ids = treeRef.value?.getCheckedKeys(false);
  treeSet({ id: tableId.value, ids: ids }).then(() => {
    treeData.defaultChecked = [];
    for (const role of treeData.data) {
      if (role.isChecked) {
        treeData.defaultChecked.push(role.id);
      }
    }
  });
};
</script>

<template>
  <el-button-group>
    <el-button type="primary" @click="treeEditRole"> 保存角色 </el-button>
  </el-button-group>
  <el-tree
    ref="treeRef"
    node-key="id"
    :data="treeData.data"
    :props="props.props"
    :show-checkbox="true"
    :highlight-current="true"
    :check-on-click-node="true"
    :default-checked-keys="treeData.defaultChecked"
  />
</template>

<style scoped>
.el-button-group {
  padding: 10px 0;
}
</style>
