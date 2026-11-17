<template>
  <div style="height: 50px;line-height: 50px;border-bottom: 1px solid #Ccc; display: flex">
    <div style="width: 200px;padding-left: 30px;font-weight: bold;color: #5faee3">后台管理系统</div>
    <div style="flex: 1;"></div>
    <div style="width: 100px;">
      <el-dropdown>
    <span class="el-dropdown-link" style="line-height: 50px;">
      王天扬
      <el-icon class="el-icon--right">
        <arrow-down/>
      </el-icon>
    </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>个人中心</el-dropdown-item>
            <el-dropdown-item @click="$router.push('/login')">登出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header"
}
</script>

<style scoped>

</style>