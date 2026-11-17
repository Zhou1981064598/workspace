<script setup>
import { Fold, Expand } from "@element-plus/icons-vue";
import { inject } from "vue";

// 获取父组件中传递来的数据
const isCollapse = inject("collapse");

// 菜单点击事件
const onSelect = (index) => {
  switch (index) {
    case "menu":
      isCollapse.value = !isCollapse.value;
      break;

    default:
      break;
  }
};
</script>

<template>
  <el-scrollbar class="toolbar">
    <el-menu :collapse="true" @select="onSelect">
      <el-menu-item index="menu">
        <el-icon>
          <Expand v-if="isCollapse" />
          <Fold v-else />
        </el-icon>
        <template #title>菜单</template>
      </el-menu-item>
    </el-menu>
  </el-scrollbar>
</template>

<style scoped>
.toolbar {
  height: 100vw;
  border-right: solid 1px var(--el-menu-border-color);
}
.el-menu {
  border: none;
}
</style>
