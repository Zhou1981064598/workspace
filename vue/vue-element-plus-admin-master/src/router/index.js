import { createRouter, createWebHistory } from "vue-router";
import NProgress from "nprogress"; // 引入进度条
import "nprogress/nprogress.css"; // 引入进度条样式
import { useUserStore } from "../stores/user";
import { useMenuStore } from "../stores/menu";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "root",
      redirect: "/home",
      component: () => import("../components/layouts/IndexLayout.vue"),
      children: [
        {
          path: "/default",
          name: "default",
          redirect: "/home",
          component: () => import("../components/layouts/DefaultLayout.vue"),
          children: [],
        },
        {
          path: "/home",
          name: "home",
          component: () => import("../views/HomeView.vue"),
        },
        {
          path: "/info",
          name: "info",
          component: () => import("../views/rbac/user/UserInfo.vue"),
        },
      ],
    },
    // 添加登录路由
    {
      path: "/login",
      name: "login",
      component: () => import("../views/LoginView.vue"),
    },
  ],
});

export default router;

/**
 * 1.路由前置守卫
 */
router.beforeEach((to, from, next) => {
  // 开启进度条
  NProgress.start();

  // 如果是登录页面，则直接跳转，什么都不做
  if (to.path === "/login") {
    next();
    return false;
  }

  const userStore = useUserStore();
  // 判断 token 是否过期，如果过期则回到登录页面
  const token = userStore.getToken();

  if (!token) {
    next({ path: "/login", replace: true });
    return false;
  }

  // 生成动态路由
  generateAsyncRouters();
  next();
});

/**
 * 2.路由后置守卫
 */
router.afterEach(() => {
  // 关闭进度条
  NProgress.done();
});

/**
 * 3.生成动态路由
 */
export function generateAsyncRouters() {
  // 获取动态路由
  const menuStore = useMenuStore();
  const menus = menuStore.getMenu();
  if (menus) {
    generateAsyncRouter(menus);
  }
}

/**
 * 递归生成动态路由
 * @param {*} menus sessionStorage 中存储的数据
 * @returns /
 */
export function generateAsyncRouter(menus) {
  const routes = [];
  for (const i in menus) {
    const menu = menus[i];
    const route = {
      path: menu.path,
      name: menu.name,
      meta: {
        title: menu?.metaTitle,
        icon: menu?.metaIcon,
      },
    };
    if (menu.redirect) {
      route.redirect = menu.redirect;
    }
    if (menu.component) {
      route.component = () => import(/* @vite-ignore */ menu.component);
    }
    if (menu.children) {
      route.children = generateAsyncRouter(menu.children);
    }
    router.addRoute(route);
    routes.push(route);
  }
  return routes;
}
