<script setup>
import HeaderLayout from "./HeaderLayout.vue";
import SideLayout from "./SideLayout.vue";
import ToolbarLayout from "./ToolbarLayout.vue";
import { provide, ref } from "vue";

// 菜单是否打开
const collapse = ref(false);

// 传递数据到子组件
provide("collapse", collapse);
</script>

<template>
  <el-config-provider namespace="el">
    <div class="frame">
      <ToolbarLayout class="toolbar" />
      <div class="layout">
        <HeaderLayout class="header" />
        <div class="content">
          <SideLayout />
          <RouterView />
        </div>
      </div>
    </div>
  </el-config-provider>
</template>

<style scoped>
.frame {
  position: fixed;
  width: 100vw;
  height: 100vh;
}
.toolbar {
  position: absolute;
  left: 0;
  top: 0;
  width: var(--layout-side-menu-collapse-width);
  height: 100%;
  border-right: solid 1px var(--el-menu-border-color);
}
.layout {
  position: absolute;
  width: calc(100% - var(--layout-side-menu-collapse-width));
  height: 100%;
  left: var(--layout-side-menu-collapse-width);
}
.header {
  height: var(--layout-header-height);
}
.content {
  display: flex;
}
</style>
