import { request } from "../../utils/request";

/**
 * 分页查询
 * @param {*} params   /
 * @returns /
 */
export function page(params) {
  return request({
    url: "/rbac/dept/page",
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
    url: "/rbac/dept/del",
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
    url: "/rbac/dept/edit",
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
    url: "/rbac/dept/recovery",
    method: "put",
    data: id,
  });
}
