import request from '../utils/axios.js'

export function getAllApply(currentPage, pageSize) {
  return request({
    url: '/api/riderApply/getAllApply',
    method: 'get',

    params: { currentPage: currentPage, pageSize: pageSize }
  })
}
