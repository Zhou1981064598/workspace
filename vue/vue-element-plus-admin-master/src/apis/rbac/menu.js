import { request } from "../../utils/request";

/**
 * 获取当前用户的菜单
 * @param {*} params /
 * @returns /
 */
export function list(params) {
  return request({
    url: "/rbac/menu/list",
    method: "get",
    params: params,
  });
}

/**
 * 分页查询
 * @param {*} params   /
 * @returns /
 */
export function page(params) {
  return request({
    url: "/rbac/menu/page",
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
    url: "/rbac/menu/del",
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
    url: "/rbac/menu/edit",
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
    url: "/rbac/menu/plus",
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
    url: "/rbac/menu/recovery",
    method: "put",
    data: id,
  });
}

/**
 * 获取角色权限
 * @param {*} roleId
 * @returns
 */
export function treeGet(roleId) {
  return request({
    url: "/rbac/menu/auth",
    method: "get",
    params: roleId,
  });
}

/**
 * 修改角色权限
 * @param {*} data
 * @returns
 */
export function treeSet(data) {
  return request({
    url: "/rbac/menu/editauth",
    method: "post",
    data: data,
  });
}
