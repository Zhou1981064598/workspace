import { request, CONTENT_TYPE } from "../../utils/request";

/**
 * 登录
 * @param {*} data /
 * @returns /
 */
export function login(data) {
  return request({
    url: "user/login",
    method: "post",
    headers: {
      "Content-Type": CONTENT_TYPE.formData,
    },
    data,
  });
}

/**
 * 退出
 * @returns /
 */
export function logout() {
  return request({
    url: "user/logout",
    method: "post",
  });
}

/**
 * 获取用户信息
 * @returns
 */
export function info() {
  return request({
    url: "/rbac/user/info",
    method: "get",
  });
}

/**
 * 分页查询
 * @param {*} params   /
 * @returns /
 */
export function page(params) {
  return request({
    url: "/rbac/user/page",
    method: "get",
    params: params,
  });
}

/**
 * 根据 id 删除
 * @param {*} id
 * @returns
 */
export function del(id) {
  return request({
    url: "/rbac/user/del",
    method: "delete",
    data: id,
  });
}

/**
 * 修改
 * @param {*} user
 * @returns
 */
export function edit(user) {
  return request({
    url: "/rbac/user/edit",
    method: "put",
    data: user,
  });
}

/**
 * 添加
 * @param {*} user
 * @returns
 */
export function plus(user) {
  return request({
    url: "/rbac/user/plus",
    method: "post",
    data: user,
  });
}

/**
 * 恢复删除
 * @param {*} id
 * @returns
 */
export function recovery(id) {
  return request({
    url: "/rbac/user/recovery",
    method: "put",
    data: id,
  });
}

/**
 * 修改密码
 * @param {*} data
 * @returns
 */
export function changePassword(data) {
  return request({
    url: "/rbac/user/chpswd",
    method: "post",
    data: data,
  });
}
