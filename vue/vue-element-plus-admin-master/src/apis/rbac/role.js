import { request } from "../../utils/request";

/**
 * 分页查询
 * @param {*} params   /
 * @returns /
 */
export function page(params) {
  return request({
    url: "/rbac/role/page",
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
    url: "/rbac/role/del",
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
    url: "/rbac/role/edit",
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
    url: "/rbac/role/plus",
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
    url: "/rbac/role/recovery",
    method: "put",
    data: id,
  });
}

/**
 * 获取用户角色
 * @param {*} userId
 * @returns
 */
export function treeGet(userId) {
  return request({
    url: "/rbac/role/role",
    method: "get",
    params: userId,
  });
}

/**
 * 修改用户角色
 * @param {*} data
 * @returns
 */
export function treeSet(data) {
  return request({
    url: "/rbac/role/editrole",
    method: "post",
    data: data,
  });
}
