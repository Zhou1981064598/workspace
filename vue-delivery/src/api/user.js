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
export function logout(data) {
  return request({
    url: '/api/user/logout',
    method: 'get',
    data
  })
}
export function register(data) {
  return request({
    url: '/api/user/register',
    method: 'post',
    headers: {
      isToken: false
    },
    data
  })
}

/**
 * 测试
 * @param {*} data /
 * @returns /
 */
export function getuser() {
  return request({
    url: '/api/user/getuser',
    method: 'get'
  })
}
export function getuserById(id) {
  return request({
    url: '/api/user/getuserById',
    method: 'get',
    params: { id: id }
  })
}
export function getRole() {
  return request({
    url: '/api/user/selectRoleByUserId',
    method: 'get'
  })
}
export function updateName(name) {
  return request({
    url: '/api/user/updateName',
    method: 'get',
    params: name
  })
}
export function updatePhone(phone) {
  return request({
    url: '/api/user/updatePhone',
    method: 'get',
    params: phone
  })
}
export function updatepassword(password) {
  return request({
    url: '/api/user/updatePassword',
    method: 'post',
    params: password
  })
}
export function submitRiderApply(data) {
  return request({
    url: '/api/user/submitRiderApply',
    method: 'post',
    data
  })
}
export function getMyApply() {
  return request({
    url: '/api/user/getMyApply',
    method: 'get'
  })
}
export function getAllRider(currentPage, pageSize) {
  return request({
    url: '/api/admin/getAllRider',
    method: 'get',
    params: { currentPage: currentPage, pageSize: pageSize }
  })
}
export function evaluate(data) {
  return request({
    url: '/api/user/evaluate',
    method: 'post',
    data
  })
}
