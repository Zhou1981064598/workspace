import { reactive, ref } from "vue";
import { defineStore } from "pinia";

export const useUserStore = defineStore("user", () => {
  /**
   * 当前登录的账号
   */
  const account = reactive({});

  // 获取前登录的账号
  const getAccount = () => {
    if (Object.keys(account).length) return account;

    let json = sessionStorage.getItem("account");
    try {
      return JSON.parse(json);
    } catch (error) {
      return {};
    }
  };

  // 存储前登录的账号
  const setAccount = (data) => {
    if (!data) return;

    Object.assign(account, data);
    try {
      sessionStorage.setItem("account", JSON.stringify(data));
    } catch (error) {
      sessionStorage.removeItem("account");
    }
  };

  // 获取前登录的账号
  const getUsername = () => {
    return getAccount()?.username;
  };

  // 获取前登录的账号的首字母
  const getUsernameUpper = () => {
    return getUsername().substring(0, 1).toUpperCase();
  };

  // 清除前登录的账号
  const logout = () => {
    sessionStorage.clear();
  };

  /**
   * 当前登录的账号的认证标识
   */
  const token = ref("");

  // 获取token
  const getToken = () => {
    if (token.value) return token;

    return sessionStorage.getItem("token");
  };

  // 存储token
  const setToken = (data) => {
    if (!data) return;

    token.value = data;
    sessionStorage.setItem("token", data);
  };

  return {
    account,
    getAccount,
    setAccount,
    getUsername,
    getUsernameUpper,

    logout,
    setToken,
    getToken,
  };
});
