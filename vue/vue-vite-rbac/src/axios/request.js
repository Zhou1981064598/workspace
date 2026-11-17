import axios from "axios";
import { ElMessage } from "element-plus";

/**
 * 1.创建 axios
 */
export const request = axios.create({
  // api的 base_url
  baseURL: "",
  // 发生 cookies 当是跨域请求时 cross-domain requests
  withCredentials: true,
  // 请求超时时间
  timeout: 60000,
});

/**
 * 2.request 请求拦截器
 */
request.interceptors.request.use(
  (config) => {
    // 针对请求类型为 application/x-www-form-urlencoded 的表单，将数据转换成 FormData
    if (
      config.headers["Content-Type"] === "application/x-www-form-urlencoded"
    ) {
      let formdata = new FormData();
      Object.keys(config.data).forEach((key) => {
        formdata.append(key, config.data[key]);
      });
      config.data = formdata;
    }
    return config;
  },
  (error) => {
    // 请求失败
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
    if (result.code === 2000) {
      ElMessage({
        type: "success",
        message: result.message || "操作成功",
      });
      return Promise.resolve(result);
    } else {
      ElMessage({
        type: "error",
        message: result.message || "操作失败",
      });
      return Promise.reject(result).catch((error) => console.log(error));
    }
  },
  // 客户端或者网络错误
  (error) => Promise.reject(error).catch((error) => console.log(error))
);

export default request;
