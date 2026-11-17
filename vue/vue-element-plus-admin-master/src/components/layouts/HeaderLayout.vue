<script setup>
import { useDark, useToggle } from "@vueuse/core";
import { useRouter } from "vue-router";
import { logout } from "../../apis/rbac/user";
import { useUserStore } from "../../stores/user";
import { useMenuStore } from "../../stores/menu";
import {
  Sunny,
  Sunrise,
  SwitchButton,
  UserFilled,
} from "@element-plus/icons-vue";

// 获取路由器，用于跳转页面
const router = useRouter();
// 获取 store 用于存取数据
const userStore = useUserStore();
const menuStore = useMenuStore();

// 获取用户名的首字母大写
const username = userStore.getUsername();
// 获取用户名的首字母大写
const usernameUp = userStore.getUsernameUpper();

// 获取动态路由
const menus = menuStore.getMenu();

// 引入环境变量中的标题
const title = import.meta.env.VITE_APP_TITLE;

// 是否是暗黑模式
const isDark = useDark();

// 切换暗黑模式
const toggleDark = useToggle(isDark);

// 退出登录
const logoutSubmit = () => {
  logout().then(() => {
    // 清空缓存
    userStore.logout();
    // 跳转到登录页面
    router.push("/login");
  });
};
</script>

<template>
  <el-menu
    mode="horizontal"
    :router="true"
    :ellipsis="false"
    :unique-opened="true"
  >
    <el-menu-item class="title" index="/">
      <template #title>{{ title }}</template>
    </el-menu-item>

    <template v-for="menu in menus" :key="menu.name">
      <el-menu-item :index="menu.path">
        {{ menu.metaTitle }}
      </el-menu-item>
    </template>
    <!-- 前面是左侧菜单 -->
    <div style="flex-grow: 1" />
    <!-- 后面是右侧菜单 -->
    <div class="el-menu-item mine-menu" @click="toggleDark()">
      <el-icon>
        <Sunny v-if="isDark" />
        <Sunrise v-else />
      </el-icon>
    </div>
    <div class="el-menu-item mine-menu">
      <el-dropdown>
        <el-avatar :size="30"> {{ usernameUp }}</el-avatar>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="router.push('/info')">
              <el-icon><UserFilled /></el-icon>
              <span>{{ username }}</span>
            </el-dropdown-item>
            <el-dropdown-item @click="logoutSubmit()">
              <el-icon><SwitchButton /></el-icon>
              <span>退出登录</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </el-menu>
</template>

<style scoped>
.title.collapse {
  min-width: var(--layout-side-menu-collapse-width);
}
.mine-menu {
  height: var(--el-menu-item-height);
}
</style>
