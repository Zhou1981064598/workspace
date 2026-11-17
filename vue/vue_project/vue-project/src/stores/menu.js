import { reactive } from "vue";
import { defineStore } from "pinia";

export const useMenuStore = defineStore("menu", () => {
  // 当前登录的账号的菜单权限
  const menu = reactive({});

  // 获取菜单
  const getMenu = () => {
    if (Object.keys(menu).length) return menu;

    let json = sessionStorage.getItem("menu");
    try {
      return JSON.parse(json);
    } catch (error) {
      return {};
    }
  };

  // 存储菜单
  const setMenu = (data) => {
    if (!data) return;

    Object.assign(menu, data);
    try {
      sessionStorage.setItem("menu", JSON.stringify(data));
    } catch (error) {
      sessionStorage.removeItem("menu");
    }
  };

  return {
    menu,
    getMenu,
    setMenu,
  };
});
