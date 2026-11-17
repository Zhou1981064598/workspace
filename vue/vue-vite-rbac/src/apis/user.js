import request from "../axios/request";

/**
 * 登录
 * @param {*} data /
 * @returns /
 */
export function login(data) {
  return request({
    url: "/api/login",
    method: "post",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    data,
  });
}
/**
 * 测试
 * @param {*} data /
 * @returns /
 */
export function user(token) {
  return request({
    url: "/api/user",
    method: "get",
    headers: {
      authentication: token,
    },
  });
}
