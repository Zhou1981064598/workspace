import request from '../utils/axios.js'

/**
 * 登录
 * @param {*} data /
 * @returns /
 */

export function getMySubmitOrder() {
  return request({
    url: '/api/order/getMySubmitOrder',
    method: 'get'
  })
}
export function getMyDoingOrder() {
  return request({
    url: '/api/order/getMyDoingOrder',
    method: 'get'
  })
}

export function getMyFinishOrder() {
  return request({
    url: '/api/order/getMyFinishOrder',
    method: 'get'
  })
}
export function getMyNoEvaluatedOrder() {
  return request({
    url: '/api/order/getMyNoEvaluatedOrder',
    method: 'get'
  })
}
export function CancelOrder(id, infId, type) {
  return request({
    url: '/api/order/deleteOrderById',
    method: 'get',
    params: { id: id, infId: infId, type: type }
  })
}
export function confirmReceipt(missionId, orderId) {
  return request({
    url: '/api/order/confirmReceipt',
    method: 'get',
    params: { missionId: missionId, orderId: orderId }
  })
}
