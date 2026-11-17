import request from '../utils/axios.js'

export function examine(userId, auditResult) {
  return request({
    url: '/api/admin/examine',
    method: 'get',
    params: { userId: userId, auditResult: auditResult }
  })
}

export function arrangeMission(id, ordid, type, courierId) {
  return request({
    url: '/api/admin/arrangeMission',
    method: 'get',
    params: { infId: id, orderId: ordid, type: type, courierId: courierId }
  })
}

export function banUsers(data) {
  return request({
    url: '/api/admin/banUsers',
    method: 'post',
    data
  })
}

export function activateUsers(data) {
  return request({
    url: '/api/admin/activateUsers',
    method: 'post',
    data
  })
}
