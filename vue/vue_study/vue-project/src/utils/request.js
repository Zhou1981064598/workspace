import axios from "axios";
import { ElMessage } from "element-plus";
import router from "../router";

export const CONTENT_TYPE = {
  formData: "application/x-www-form-urlencoded",
  jsonData: "application/json",
};

/**
 * 1.创建 axios
 */
export const request = axios.create({
  // api的 base_url
  baseURL: import.meta.env.VITE_API_BASEPATH,
  // 发生 cookies 当是跨域请求时 cross-domain requests
  withCredentials: true,
  // 请求超时时间
  timeout: 60000,
});

/**
 * 2.request 请求拦截器
 */
request.interceptors.request.use(
  // 配置请求参数
  (config) => {
    // 本地会话读取 token
    // const token = sessionStorage.getItem(AUTHORIZATION_KEY);
    // if (config.url !== "/login" && token) {
    //   config.headers[AUTHORIZATION_KEY] = token;
    // }
    // 表单提交
    if (
      config.method === "post" &&
      config.headers["Content-Type"] === CONTENT_TYPE.formData
    ) {
      let formdata = new FormData();
      Object.keys(config.data).forEach((key) => {
        formdata.append(key, config.data[key]);
      });
      config.data = formdata;
    }
    // get提交对参数进行编码
    if (config.method === "get" && config.params) {
      let url = config.url + "?";
      Object.keys(config.params).forEach((key) => {
        if (config.params[key] !== void 0 && config.params[key] !== null) {
          url += `${key}=${encodeURIComponent(config.params[key])}&`;
        }
      });
      url = url.substring(0, url.length - 1);
      config.params = {};
      config.url = url;
    }
    return config;
  },
  // 请求失败
  (error) => {
    return Promise.reject(error).catch((error) => console.log(error));
  }
);

/**
 * 3.response 响应拦截器
 */
request.interceptors.response.use(
  // 正常返回
  (response) => {
    const result = response.data;
    // 后端返回的状态码为 2000 表示处理成功
    if (result.code == import.meta.env.VITE_RESPONSE_SUCCESS) {
      ElMessage({
        type: "success",
        message: result.message || "操作成功",
      });
      return Promise.resolve(result);
    }
    // 如果未登录或者登录过期
    if (result.code === 2015) {
      router.push("/login");
    }
    ElMessage({
      type: "error",
      message: result.message || "操作失败",
    });
    // 失败时回调
    return Promise.reject(result).catch((error) => console.log(error));
  },
  // 客户端或者网络错误
  (error) => Promise.reject(error).catch((error) => console.log(error))
);

export default request;
