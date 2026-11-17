<template>
  <div class="common-layout">
    <el-container>
      <el-header style="height: 600px">
        <div class="common-layout">
          <div class="block text-center">
            <el-carousel height="600px" type="card">
              <el-carousel-item v-for="item in carouseData" :key="item" style="width: 1000px">
                <img :src="item.url" alt="" />
              </el-carousel-item>
            </el-carousel>
          </div></div
      ></el-header>
      <el-main>
        <el-divider style="border-color: black"> </el-divider>
        <el-row :gutter="20">
          <el-col :span="6">
            <el-row :gutter="20">
              <el-col :span="8" class="radius" style="round"
                ><div class="icon-body">
                  <el-icon class="icon" @click="clickIcon('showpersonal')" style="cursor: pointer"
                    ><Notification
                  /></el-icon>
                  <div><span>个人中心</span></div>
                </div>
              </el-col>

              <el-col :span="8" style="border-radius: var(--el-border-radius-round)"
                ><div class="icon-body">
                  <el-icon class="icon" @click="clickIcon('pick')" style="cursor: pointer"
                    ><Sell />
                  </el-icon>
                  <div><span>快递代取</span></div>
                </div>
              </el-col>
              <el-col :span="8"
                ><div class="icon-body">
                  <el-icon class="icon" @click="clickIcon('send')" style="cursor: pointer"
                    ><SoldOut />
                  </el-icon>
                  <div><span>快递待寄</span></div>
                </div>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8"
                ><div class="icon-body">
                  <el-icon class="icon" @click="lookMission('missions')" style="cursor: pointer"
                    ><Monitor />
                  </el-icon>
                  <div><span>任务大厅</span></div>
                </div>
              </el-col>
              <el-col :span="8"
                ><div class="icon-body">
                  <el-icon class="icon" @click="lookMission('mymissions')" style="cursor: pointer"
                    ><Notebook />
                  </el-icon>
                  <div>我的任务</div>
                </div>
              </el-col>
              <el-col :span="8"
                ><div class="icon-body">
                  <el-icon class="icon" @click="clickIcon('myorder')" style="cursor: pointer"
                    ><Memo />
                  </el-icon>
                  <div><span>我的订单</span></div>
                </div>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="6">
            <img
              class="logo"
              src="../static/img/1.png"
              style="height: 200px; cursor: pointer"
              @click="toPage('pick')"
            />
          </el-col>
          <el-col :span="6"
            ><img
              class="logo"
              src="../static/img/2.jpg"
              style="height: 200px; cursor: pointer"
              @click="toPage('pick')"
            />
          </el-col>
          <el-col :span="6"
            ><img class="logo" src="../static/img/3.png" style="height: 100%" />
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <img
              class="logo"
              src="../static/img/5.png"
              style="width: 100%; cursor: pointer; background: center center no-repeat"
              @click="applyrider"
            />
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>
<script lang="ts" setup>
import { useRouter } from 'vue-router'
import { localGet } from '@/utils'
import { ElMessage } from 'element-plus'
const router = useRouter()
const clickIcon = (url) => {
  router.push(url)
}
const lookMission = (url) => {
  if (localGet('RoleName') == '普通用户') {
    if (url == 'missions' || url == 'mymissions') {
      ElMessage({
        type: 'error',
        message: '您还不是骑手哦，请成为骑手后再来查看任务吧'
      })
    }
    return
  }
  router.push(url)
}
const carouseData = [
  { url: new URL('@/assets/index/001.jpg', import.meta.url).href },
  { url: new URL('@/assets/index/002.jpg', import.meta.url).href },
  { url: new URL('@/assets/index/003.png', import.meta.url).href }
]
const applyrider = () => {
  router.push('/applyhome')
}
const toPage = (url) => {
  router.push(url)
}
</script>
<style scoped>
.demonstration {
  color: var(--el-text-color-secondary);
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
  background-color: #f2f2f2;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.icon {
  margin-top: 0px;
  font-size: 65px;
}
.icon-body {
  margin-left: 20px;
  height: 85px;
}
.demo-radius .radius {
  height: 40px;
  width: 70%;
  border: 1px solid var(--el-border-color);
  border-radius: 0;
  margin-top: 20px;
}
/* 横线 */
.hline {
  float: right;
  width: 100%;
  height: 1px;
  background: #464444;
  position: relative;
  text-align: center;
  margin-bottom: 25px;
}
.el-icon-Menu {
  background: url('@/static/img/homebackground.png') center center no-repeat;
  background-size: 100%;
}
.el-icon-Menu:before {
  content: '11';
  visibility: hidden;
}
</style>
