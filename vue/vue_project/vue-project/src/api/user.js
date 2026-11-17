import request from '../utils/axios.js'

/**
 * 登录
 * @param {*} data /
 * @returns /
 */
export function login(data) {
  return request({
    url: '/api/user/login',
    method: 'post',
    headers: {
      isToken: false
    },
    data
  })
}
export function hello() {
  return request({
    url: '/api/hello',
    method: 'post'
  })
}
/**
 * 测试
 * @param {*} data /
 * @returns /
 */
export function user(token) {
  return request({
    url: '/api/user',
    method: 'get',
    headers: {
      authentication: token
    }
  })
}
export function swiper(data) {
  return request({
    url: '/api/swiper',
    method: 'post',
    headers: {
      isToken: true
    },
    data
  })
}
