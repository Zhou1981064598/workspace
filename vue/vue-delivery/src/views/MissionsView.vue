<template>
  <el-row :gutter="100" align="middle">
    <el-col :span="8"
      ><div class="grid-content ep-bg-purple" />
      <el-card
        :body-style="{ padding: '0px', cursor: 'pointer' }"
        @click="goto('showPackagePickInf')"
      >
        <img src="../static/img/daiqu.png" class="image" />
        <div style="padding: 14px; font-size: 20px; text-align: center">
          <span>快递代取</span>
        </div>
      </el-card></el-col
    >
    <el-col :span="8"
      ><div class="grid-content ep-bg-purple-light" />
      <el-card
        :body-style="{ padding: '0px', cursor: 'pointer' }"
        @click="goto('showPackageSendInf')"
      >
        <img src="../static/img/daiji.png" class="image" />
        <div style="padding: 14px; font-size: 20px; text-align: center">
          <span>快递代寄</span>
        </div>
      </el-card></el-col
    >
    <el-col :span="8"
      ><div class="grid-content ep-bg-purple" />
      <el-card :body-style="{ padding: '0px', cursor: 'pointer' }">
        <img src="../static/img/daying.png" class="image" />
        <div style="padding: 14px; font-size: 20px; text-align: center">
          <span>打印服务</span>
        </div>
      </el-card></el-col
    >
  </el-row>
</template>

<script lang="ts" setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const goto = (to) => {
  router.push(to)
}
</script>

<style>
.time {
  font-size: 12px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button {
  padding: 0;
  min-height: auto;
}

.image {
  width: 100%;
  display: block;
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 150px;
}
</style>
