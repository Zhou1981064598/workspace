<script setup>
import { inject } from "vue";

const isEditView = inject("isEditView");
</script>
<template>
  <el-button-group>
    <el-button type="primary" @click="isEditView = false"> 返回 </el-button>
    <el-button type="primary" class="el-button-submit"> 提交 </el-button>
  </el-button-group>
  <el-steps :active="active" finish-status="success">
    <el-step title="Step 1" />
    <el-step title="Step 2" />
    <el-step title="Step 3" />
  </el-steps>

  <el-button style="margin-top: 12px" @click="next">Next step</el-button>
</template>
<style>
.el-button-group {
  margin-bottom: 20px;
}
.el-button-submit {
}
</style>
