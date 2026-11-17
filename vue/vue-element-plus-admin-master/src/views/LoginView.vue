<script setup>
import { reactive, ref } from "vue";
import { useRouter } from "vue-router";
import { login, info } from "../apis/rbac/user.js";
import { list } from "../apis/rbac/menu";
import { useUserStore } from "../stores/user";
import { useMenuStore } from "../stores/menu";

// 获取路由器，用于跳转页面
const router = useRouter();
// 获取 store 用于存取数据
const userStore = useUserStore();
const menuStore = useMenuStore();

// 获取表单对象
const ruleFormRef = ref();

// 表单数据
const formData = reactive({
  username: "admin",
  password: "123456",
});

// 表单校验规则
const formRule = reactive({
  username: [
    { required: true, message: "用户名不能为空", trigger: "blur" },
    { min: 2, max: 16, message: "用户名为4-16位", trigger: "blur" },
  ],
  password: [
    { required: true, message: "密码不能为空", trigger: "blur" },
    { min: 2, max: 16, message: "密码为4-16位", trigger: "blur" },
  ],
});

// 表单提交
const loginSubmit = (formEl) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (!valid) return false;

    // 格式验证通过后登录
    login(formData).then((response) => {
      const { data } = response;
      // 存储登录后的 token 认证标识
      userStore.setToken(data);

      // 加载菜单
      list().then((response) => {
        const { data } = response;
        // 存储动态路由
        menuStore.setMenu(data);

        // 获取用户信息
        info().then((response) => {
          const { data } = response;
          // 存储用户信息
          userStore.setAccount(data);
          // 跳转到主页
          router.push("/");
        });
      });
    });
  });
};
</script>

<template>
  <el-form
    label-position="top"
    ref="ruleFormRef"
    :model="formData"
    status-icon
    :rules="formRule"
    label-width="120px"
  >
    <el-form-item label="用户名" prop="username">
      <el-input v-model="formData.username" autocomplete="off" />
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input
        v-model="formData.password"
        type="password"
        autocomplete="off"
      />
    </el-form-item>

    <el-form-item>
      <el-button
        type="primary"
        @click="loginSubmit(ruleFormRef)"
        @keyup.exact="loginSubmit(ruleFormRef)"
        >登录</el-button
      >
    </el-form-item>
  </el-form>
</template>

<style scoped>
.el-form {
  position: fixed;
  width: 500px;
  height: 320px;
  left: 50%;
  top: 50%;
  padding: 40px 60px;
  transform: translate(-50%, -50%);
  box-shadow: 0 0 2px 2px rgba(0, 0, 0, 0.2);
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  border-radius: 10px;
}

@media (max-width: 960px) {
  .el-form {
    width: 90vw;
    max-width: 460px;
    height: auto;
    padding: 3vh 8vw;
  }
}
</style>
