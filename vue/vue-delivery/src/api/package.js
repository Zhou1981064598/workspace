import request from '../utils/axios.js'

/**
 * 登录
 * @param {*} data /
 * @returns /
 */

export function pick(data) {
  return request({
    url: '/api/package/pick',
    method: 'post',
    data
  })
}
export function getPackagePickInf(currentPage, pageSize) {
  return request({
    url: '/api/package/getPackagePickInf',
    method: 'get',

    params: { currentPage: currentPage, pageSize: pageSize }
  })
}
export function getPackagePickInfById(id) {
  return request({
    url: '/api/package/selectPackageInfById',
    method: 'get',

    params: { id: id }
  })
}
export function send(data) {
  return request({
    url: '/api/package/send',
    method: 'post',
    data
  })
}

export function getPackageSendInf(currentPage, pageSize) {
  return request({
    url: '/api/package/getPackageSendInf',
    method: 'get',
    params: { currentPage: currentPage, pageSize: pageSize }
  })
}
