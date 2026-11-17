import request from '../utils/axios.js'

/**
 * 登录
 * @param {*} data /
 * @returns /
 */

export function acceptMission(id, ordid, type) {
  return request({
    url: '/api/mission/addMission',
    method: 'get',
    params: { infId: id, orderId: ordid, type: type }
  })
}

export function getMyDoingMissions() {
  return request({
    url: '/api/mission/getMyDoingMissions',
    method: 'get'
  })
}
export function getMySubmitMissions() {
  return request({
    url: '/api/mission/getMySubmitMissions',
    method: 'get'
  })
}
export function getMyFinishMissions() {
  return request({
    url: '/api/mission/getMyFinishMissions',
    method: 'get'
  })
}
export function cancelMission(id, infId, ordid, type) {
  return request({
    url: '/api/mission/cancelMission',
    method: 'get',
    params: { missionId: id, infId: infId, orderId: ordid, type: type }
  })
}

export function submitMission(id, ordid) {
  return request({
    url: '/api/mission/submitMission',
    method: 'get',
    params: { missionId: id, orderId: ordid }
  })
}
