<script setup>
import { inject } from "vue";
import { useRoute } from "vue-router";

// 当前路由对象
const route = useRoute();

// 当前访问的路由及其子路由，这里取第一级路由
// const currentRoute = route.matched[0];

// 获取父组件中传递来的数据
const isCollapse = inject("collapse");
</script>

<template>
  <el-scrollbar class="side" :class="{ collapse: isCollapse }">
    <el-menu :router="true" :unique-opened="true">
      <template v-for="item in route.matched[0].children" :key="item?.name">
        <el-sub-menu v-if="item?.meta?.title" :index="item?.path">
          <template #title>
            <el-icon v-if="item?.meta?.icon">
              <font-awesome-icon :icon="item?.meta?.icon" />
            </el-icon>
            <span>{{ item?.meta?.title }}</span>
          </template>

          <template v-for="child in item?.children" :key="child?.name">
            <el-menu-item-group>
              <el-menu-item :index="child.path">
                {{ child?.meta?.title }}
              </el-menu-item>
            </el-menu-item-group>
          </template>
        </el-sub-menu>
      </template>
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
