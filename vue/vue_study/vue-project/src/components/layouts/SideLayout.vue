<script setup>
import { Location } from "@element-plus/icons-vue";
import { inject } from "vue";

// 获取父组件中传递来的数据
const isCollapse = inject("collapse");
</script>

<template>
  <el-scrollbar class="side" :class="{ collapse: isCollapse }">
    <el-menu :router="true">
      <el-sub-menu index="1">
        <template #title>
          <el-icon><location /></el-icon>
          <span>Navigator One{{ isCollapse }}</span>
        </template>
        <el-menu-item-group>
          <template #title><span>Group One</span></template>
          <el-menu-item index="1-1">item one</el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>
      <el-menu-item index="2">
        <el-icon><location /></el-icon>
        <template #title>Navigator Two</template>
      </el-menu-item>
    </el-menu>
  </el-scrollbar>
</template>

<style scoped>
.side {
  width: var(--layout-side-menu-expand-width);
  min-width: var(--layout-side-menu-expand-width);
  height: calc(100vh - var(--layout-header-height));
  border-right: solid 1px var(--el-menu-border-color);
}

.side.collapse {
  display: none;
}
.el-menu {
  border: none;
  width: var(--layout-side-menu-expand-width);
  min-width: var(--layout-side-menu-expand-width);
}

@media (max-width: 960px) {
  .side {
    position: fixed;
    left: var(--layout-side-menu-collapse-width);
    top: var(--layout-header-height);
    z-index: 999;
    display: none;
  }
  .side.collapse {
    display: block !important;
  }
}
</style>
