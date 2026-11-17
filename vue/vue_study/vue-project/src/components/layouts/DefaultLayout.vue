<template>
  <el-scrollbar class="pannel">
    <router-view v-slot="{ Component }">
      <template v-if="Component">
        <keep-alive>
          <component :is="Component" />
        </keep-alive>
      </template>
    </router-view>
  </el-scrollbar>
</template>

<style scoped>
.pannel {
  height: calc(100vh - var(--layout-header-height));
  padding: var(--layout-container-padding);
}
</style>
