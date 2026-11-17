<script setup>
import { reactive, ref } from "vue";
import { useRouter } from "vue-router";
import { login, user } from "../apis/user";

const router = useRouter();

// 引用 el-form 组件
const loginFormRef = ref();

// 登录表单相关的数据
const loginData = reactive({
  form: {
    username: "Zs.123456",
    password: "123456",
  },
  rules: {
    username: [
      {
        required: true, // 是否必填
        message: "必须输入用户名",
        trigger: "blur",
      },
      { min: 3, max: 20, message: "用户名长度需为3~20", trigger: "blur" },
    ],
    password: [
      {
        required: true,
        message: "必须输入密码",
        trigger: "change",
      },
      { min: 6, max: 20, message: "密码长度需为6~20", trigger: "blur" },
    ],
  },
});

// 登录功能
const loginSubmit = async (loginFormRef) => {
  await loginFormRef.validate((valid) => {
    if (valid) {
      login(loginData.form).then((response) => {
        if (response.data) {
          user(response.data);
          //跳转到主页
          router.push("/home");
        }
      });
    }
  });
};
</script>

<template>
  <el-form
    ref="loginFormRef"
    label-position="top"
    :model="loginData.form"
    :rules="loginData.rules"
  >
    <span>登录</span>
    <el-form-item label="用户名" prop="username">
      <el-input v-model="loginData.form.username" />
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="loginData.form.password" type="password" />
    </el-form-item>
    <el-button type="primary" @click="loginSubmit(loginFormRef)"
      >登录</el-button
    >
  </el-form>
</template>

<style scoped>
.el-form {
  position: fixed;
  width: 480px;
  height: 300px;
  padding: 20px 40px;
  left: 50%;
  top: 50%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  text-align: center;
  border-radius: 10px;
  transform: translate(-50%, -50%);
  box-shadow: 0px 0px 2px 2px rgba(255, 0, 0, 0.2);
}
</style>
