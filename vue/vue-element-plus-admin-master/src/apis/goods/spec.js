import { request } from "../../utils/request";

/**
 * 分页查询
 * @param {*} params   /
 * @returns /
 */
export function page(params) {
  return request({
    url: "/goods/spec/page",
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
    url: "/goods/spec/del",
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
    url: "/goods/spec/edit",
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
    url: "/goods/spec/plus",
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
    url: "/goods/spec/recovery",
    method: "put",
    data: id,
  });
}
