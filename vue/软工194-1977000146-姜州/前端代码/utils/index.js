export function localGet(key) {
  const value = window.localStorage.getItem(key)
  try {
    return JSON.parse(window.localStorage.getItem(key))
  } catch (error) {
    return value
  }
}

export function localSet(key, value) {
  window.localStorage.setItem(key, JSON.stringify(value))
}

export function localRemove(key) {
  window.localStorage.removeItem(key)
}

// 判断内容是否含有表情字符，现有数据库不支持。
export function hasEmoji(str = '') {
  const reg =
    /[^\u0020-\u007E\u00A0-\u00BE\u2E80-\uA4CF\uF900-\uFAFF\uFE30-\uFE4F\uFF00-\uFFEF\u0080-\u009F\u2000-\u201f\u2026\u2022\u20ac\r\n]/g
  return str.match(reg) && str.match(reg).length
}

// 单张图片上传
export const uploadImgServer = 'http://backend-api-02.newbee.ltd/manage-api/v1/upload/file'
// 多张图片上传
export const uploadImgsServer = 'http://backend-api-02.newbee.ltd/manage-api/v1/upload/files'

export const pathMap = {
  login: '登录',
  home: '首页',
  admin: '管理员后台',
  dashboard: '大盘数据',
  add: '添加商品',
  pick: '快递代取',
  send: '快递待寄',
  new: '新品上线配置',
  recommend: '为你推荐配置',
  category: '分类管理',
  level2: '分类二级管理',
  level3: '分类三级管理',
  good: '商品管理',
  guest: '骑手审核',
  order: '订单管理',
  order_detail: '订单详情',
  account: '修改账户',
  register: '注册',
  missions: '查看任务',
  myorder: '我的订单',
  mymissions: '我的任务',
  applyhome: '申请骑手',
  showpersonal: '个人中心',
  showPackagePickInf: '快递代取任务',
  showPackageSendInf: '快递代寄任务',
  ridermanage: '骑手管理'
}
