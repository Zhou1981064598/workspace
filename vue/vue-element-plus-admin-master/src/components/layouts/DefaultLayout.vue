<script setup>
import SideLayout from "./SideLayout.vue";
</script>
<template>
  <div class="content">
    <SideLayout />
    <el-scrollbar class="pannel">
      <router-view v-slot="{ Component }">
        <template v-if="Component">
          <keep-alive>
            <component :is="Component" />
          </keep-alive>
        </template>
      </router-view>
    </el-scrollbar>
  </div>
</template>

<style scoped>
.content {
  display: flex;
}
.pannel {
  width: 100%;
  height: calc(100vh - var(--layout-header-height));
  padding: var(--layout-container-padding);
}
</style>
