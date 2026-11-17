<template>
  <el-config-provider :locale="zhCn">
    <div class="layout">
      <el-container v-if="state.showMenu" class="container">
        <!-- <el-aside class="aside">
          <div class="head">
            <div>
              <span>快递代取系统</span>
            </div>
          </div>
          <div class="line" />
          <el-menu
            background-color="#222832"
            text-color="#fff"
            :router="true"
            :default-openeds="state.defaultOpen"
            :default-active="state.currentPath"
          >
            <el-sub-menu index="1">
              <template #title>
                <span>Dashboard</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/"
                  ><el-icon><Odometer /></el-icon>首页</el-menu-item
                >
                <el-menu-item index="/add"
                  ><el-icon><Plus /></el-icon>添加商品</el-menu-item
                >
              </el-menu-item-group>
            </el-sub-menu>
            <el-sub-menu index="2">
              <template #title>
                <span>立即下单</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/pick"
                  ><el-icon><Sell /></el-icon>快递代取</el-menu-item
                >
                <el-menu-item index="/send"
                  ><el-icon><SoldOut /></el-icon>快递待寄</el-menu-item
                >
                <el-menu-item index="/new"
                  ><el-icon><Printer /></el-icon>打印服务</el-menu-item
                >
                <el-menu-item index="/recommend"
                  ><el-icon><List /></el-icon>其他</el-menu-item
                >
              </el-menu-item-group>
            </el-sub-menu>
            <el-sub-menu index="3">
              <template #title>
                <span>订单查询</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/myorder"
                  ><el-icon><Menu /></el-icon>我的订单</el-menu-item
                >
                <el-menu-item index="/good"
                  ><el-icon><Goods /></el-icon>商品管理</el-menu-item
                >
                <el-menu-item index="/guest"
                  ><el-icon><User /></el-icon>会员管理</el-menu-item
                >
                <el-menu-item index="/order"
                  ><el-icon><List /></el-icon>订单管理</el-menu-item
                >
              </el-menu-item-group>
            </el-sub-menu>
            <el-sub-menu index="4">
              <template #title>
                <span>任务中心</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/missions"
                  ><el-icon><Menu /></el-icon>查看任务</el-menu-item
                >
                <el-menu-item index="/mymissions"
                  ><el-icon><Goods /></el-icon>我的任务</el-menu-item
                >
                <el-menu-item index="/guest"
                  ><el-icon><User /></el-icon>会员管理</el-menu-item
                >
                <el-menu-item index="/order"
                  ><el-icon><List /></el-icon>订单管理</el-menu-item
                >
              </el-menu-item-group>
            </el-sub-menu>
            <el-sub-menu index="5">
              <template #title>
                <span>个人中心</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/account"
                  ><el-icon><Lock /></el-icon>修改密码</el-menu-item
                >
              </el-menu-item-group>
            </el-sub-menu>
          </el-menu>
        </el-aside> -->
        <el-container class="content">
          <Header />
          <div class="main">
            <router-view />
          </div>
          <Footer />
        </el-container>
      </el-container>
      <el-container v-else class="container">
        <router-view />
      </el-container>
    </div>
  </el-config-provider>
</template>

<script setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
import { pathMap } from '@/utils'
import { getToken } from '@/utils/auth'
import { ElConfigProvider } from 'element-plus'
import zhCn from 'element-plus/lib/locale/lang/zh-cn'

zhCn.el.pagination = {
  pagesize: '/page',
  total: '总共 {total}条',
  goto: '跳转到',
  pageClassifier: ''
}

const noMenu = ['/login', '/register']
const router = useRouter()
const state = reactive({
  showMenu: true,
  hasBack: true,
  defaultOpen: ['1', '2', '3', '4'],
  currentPath: '/'
})

router.beforeEach((to, from, next) => {
  if (to.path == '/login' || to.path == '/register') {
    // 如果路径是 /login 则正常执行
    next()
  } else {
    // 如果不是 /login，判断是否有 token
    if (!getToken()) {
      // 如果没有，则跳至登录页面
      next({ path: '/login' })
    } else {
      // 否则继续执行
      next()
    }
  }
  state.showMenu = !noMenu.includes(to.path)
  state.currentPath = to.path
  document.title = pathMap[to.name]
})
</script>

<style scoped>
.layout {
  min-height: 100vh;
  background: url('@/static/img/homebackground.png') top center no-repeat;
  background-size: 100%;
}
.container {
  height: 100vh;
}
.aside {
  width: 200px !important;
  background-color: #222832;
}
.head {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
}
.head > div {
  display: flex;
  align-items: center;
}

.head img {
  width: 50px;
  height: 50px;
  margin-right: 10px;
}
.head span {
  font-size: 20px;
  color: #ffffff;
}
.line {
  border-top: 1px solid hsla(0, 0%, 100%, 0.05);
  border-bottom: 1px solid rgba(0, 0, 0, 0.2);
}
.content {
  display: flex;
  flex-direction: column;
  max-height: 100vh;
  overflow: hidden;
}
.main {
  height: calc(100vh - 100px);
  overflow: auto;
  padding: 10px;
}
</style>

<style>
body {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
.el-menu {
  border-right: none !important;
}
.el-submenu {
  border-top: 1px solid hsla(0, 0%, 100%, 0.05);
  border-bottom: 1px solid rgba(0, 0, 0, 0.2);
}
.el-submenu:first-child {
  border-top: none;
}
.el-submenu [class^='el-icon-'] {
  vertical-align: -1px !important;
}
a {
  color: #409eff;
  text-decoration: none;
}
.el-pagination {
  text-align: middle;
  margin-top: 20px;
}
.el-popper__arrow {
  display: none;
}
::-webkit-scrollbar {
  width: 0 !important;
}
::-webkit-scrollbar {
  width: 0 !important;
  height: 0;
}
</style>
