<template>
  <el-menu class="el-menu-demo" mode="horizontal" :ellipsis="false">
    <el-menu-item index="0">
      <div aria-label="A complete example of page header">
        <el-page-header @click="back">
          <template #content>
            <div class="flex items-center">
              <!-- <el-avatar
                class="mr-3"
                :size="32"
                src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
              /> -->
              <span
                class="text-sm mr-2"
                style="color: var(--el-text-color-regular); font-size: 15px"
              >
                {{ state.name }}
              </span>
            </div>
          </template>
        </el-page-header>
      </div>
    </el-menu-item>
    <el-menu-item index="1" @click="toWhere('/home')">
      <i class="el-icon-zhuye2" style="font-size: 23px; color: rgb(28, 26, 23)"></i
      ><span style="font-size: 15px">首页</span>
    </el-menu-item>
    <div class="flex-grow" />
    <el-menu-item index="2" v-if="state.roleName == '管理员'" @click="toWhere('/admin')">
      <i
        class="el-icon-guanliyuan_jiaoseguanli"
        style="font-size: 20px; color: rgb(28, 26, 23)"
      ></i>
      <span>管理员界面</span>
    </el-menu-item>
    <el-sub-menu index="3">
      <template #title>
        <el-icon><User /></el-icon>

        <span style="tecolor: black">
          {{ (state.userInfo && state.userInfo.username) || '' }}:{{ state.roleName }}</span
        ></template
      >
      <el-menu-item index="2-1" @click="toWhere('/showpersonal')">个人中心</el-menu-item>
      <el-menu-item index="2-2">
        <div @click="logoutuser" style="color: red">退出登录</div>
      </el-menu-item>
    </el-sub-menu>
  </el-menu>
  <!-- 
  <div class="header">
    <div class="left">
      <el-icon @click="back" style="cursor: pointer"><ArrowLeftBold /></el-icon>
      <span style="font-size: 20px">{{ state.name }}</span>
    </div>
    <div class="right" @click="showPersonal">
      <el-popover placement="bottom" :width="320" popper-class="popper-user-box">
        <template #reference>
          <div class="author">
            <el-icon><User /></el-icon>
            <span style="tecolor: black">
              {{ (state.userInfo && state.userInfo.username) || '' }}</span
            >
            <el-icon><ArrowDownBold /></el-icon>
          </div>
        </template>
        <div class="nickname">
          <p>用户名：{{ (state.userInfo && state.userInfo.username) || '' }}</p>
          <p>角色：{{ state.roleName }}</p>
          <el-tag size="small" effect="dark" class="logout" @click="logoutuser">退出</el-tag>
        </div>
      </el-popover>
    </div>
  </div> -->
</template>

<script setup>
import { onMounted, reactive } from 'vue'
import { useRouter } from 'vue-router'
import axios from '@/utils/axios'
import { logout } from '@/api/user.js'
import { ElMessage } from 'element-plus'
import { getRole } from '@/api/user.js'

import { localRemove, pathMap, localSet } from '@/utils'

const router = useRouter()
const state = reactive({
  name: '',
  userInfo: { id: '', username: '', password: '' }, // 用户信息变量
  hasBack: true, // 是否展示返回icon
  role: [{ id: '', roleName: '' }],
  roleNameList: [''],
  roleName: ''
})
// 初始化执行方法
onMounted(() => {
  const pathname = window.location.hash.split('/')[1] || ''
  if (!['login'].includes(pathname)) {
    getUserInfo()
  }
})
// 获取用户信息
const getUserInfo = async () => {
  const userInfo = await axios.get('/api/user/getuser')
  state.userInfo.id = userInfo.data.id
  state.userInfo.username = userInfo.data.userName
  state.userInfo.password = userInfo.data.password
  getRole().then((res) => {
    state.role = res.data
    for (let i = 0; i < state.role.length; i++) {
      state.roleNameList.push(state.role[i].roleName)
    }
    if (state.roleNameList.includes('管理员')) {
      state.roleName = '管理员'
    } else if (state.roleNameList.includes('骑手')) {
      state.roleName = '骑手'
    } else {
      state.roleName = '普通用户'
    }
    localSet('RoleName', state.roleName)
  })
}
const toWhere = (val) => {
  router.push(val)
}
// 退出登录
const logoutuser = () => {
  logout().then((res) => {
    // 回到登录页
    router.push({ path: '/login' })
    ElMessage({
      type: 'success',
      message: res.message
    })
  })

  // 退出之后，将本地保存的 token  清理掉
}

router.afterEach((to) => {
  // const { id } = to.query
  if (to.path == '/login') {
    localRemove('token')
  }
  state.name = pathMap[to.name]
  // level2 和 level3 需要展示返回icon
  state.hasBack = ['level2', 'level3'].includes(to.name)
})

// 返回方法
const back = () => {
  router.back()
}
</script>

<style scoped>
.header {
  height: 50px;
  border-bottom: 1px solid #e9e9e9;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}
.header .left .back {
  border: 1px solid #e9e9e9;
  padding: 5px;
  border-radius: 50%;
  margin-right: 5px;
  cursor: pointer;
}
.right > div > .icon {
  font-size: 18px;
  margin-right: 6px;
}
.author {
  margin-left: 10px;
  cursor: pointer;
}
</style>

<style>
.popper-user-box {
  background: url('@/static/img/userbackground.png') 50% 50% no-repeat !important;
  background-size: cover !important;
  border-radius: 0 !important;
}
.popper-user-box .nickname {
  position: relative;
  color: #131212;
}
.popper-user-box .nickname .logout {
  position: absolute;
  right: 0;
  top: 0;
  cursor: pointer;
}
.flex-grow {
  flex-grow: 1;
}
</style>
