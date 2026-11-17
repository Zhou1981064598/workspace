import request from '../utils/axios.js'

/**
 * 登录
 * @param {*} data /
 * @returns /
 */
export function getaddress(data) {
  return request({
    url: '/api/address/getaddress',
    method: 'get',
    data
  })
}
export function addAddress(data) {
  return request({
    url: '/api/address/addAddress',
    method: 'post',
    data
  })
}
