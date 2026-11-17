<template>
  <div class="login-body">
    <div class="login-container">
      <div class="head">
        <img class="logo" src="../img/kuaidi.png" />
        <div class="name">
          <div class="title">校园代取系统</div>
          <div class="tips">Vue3.0 系统</div>
        </div>
      </div>
      <el-form
        label-position="top"
        :rules="state.rules"
        :model="state.ruleForm"
        ref="loginForm"
        class="login-form"
      >
        <el-form-item label="账号" prop="userName">
          <input
            class="el-input__inner1"
            v-model.trim="state.ruleForm.userName"
            type="text"
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="密码" prop="passWord">
          <input
            class="el-input__inner1"
            v-model.trim="state.ruleForm.passWord"
            type="password"
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item>
          <div style="color: #333">登录表示您已同意<a>《服务条款》</a></div>
          <el-button style="width: 100%" type="primary" @click="submitForm">立即登录</el-button>
          <el-checkbox v-model="state.checked" @change="!state.checked">下次自动登录</el-checkbox>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { login } from '@/api/user.js'
import { useRouter } from 'vue-router'
import { setToken } from '@/utils/auth'

const router = useRouter()

const loginForm = ref(null)
const state = reactive({
  ruleForm: {
    userName: 'admin',
    passWord: '123456'
  },
  checked: true,
  rules: {
    userName: [{ required: 'true', message: '账户不能为空', trigger: 'blur' }],
    passWord: [{ required: 'true', message: '密码不能为空', trigger: 'blur' }]
  }
})
const submitForm = async () => {
  loginForm.value.validate((valid) => {
    if (valid) {
      login(state.ruleForm).then((res) => {
        if (res.code === 200) {
          ElMessage({
            type: 'success',
            message: '登录成功'
          })
          setToken(res.data.token)

          router.push('/home')
        } else {
          state.ruleForm.userName = ''
          state.ruleForm.passWord = ''
        }
      })
    } else {
      console.log('error submit!!')
      return false
    }
  })
}
// const resetForm = () => {
//   loginForm.value.resetFields()
// }
</script>

<style scoped>
.login-body {
  display: flex;
  width: 100%;
  background-color: #fff;
}
.login-container {
  width: 420px;
  height: 500px;
  background-color: #fff;
  border-radius: 4px;
  box-shadow: 0px 21px 41px 0px rgba(0, 0, 0, 0.2);
  margin: auto;
}
.el-button--primary {
  color: #fff;
  background-color: #1baeae;
  border-color: #1baeae;
}
.head {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px 0 20px 0;
}
.head img {
  width: 100px;
  height: 100px;
  margin-right: 20px;
}
.head .title {
  font-size: 28px;
  color: #1baeae;
  font-weight: bold;
}
.head .tips {
  font-size: 12px;

  color: #999;
}
.login-form {
  width: 70%;
  margin: 0 auto;
}

.el-button {
  display: inline-block;
  line-height: 1;
  white-space: nowrap;
  cursor: pointer;
  border: 1px solid #dcdfe6;
  border-color: #dcdfe6;
  color: #fff;
  background-color: #1baeae;
  text-align: center;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  outline: 0;
  margin: 0;
  -webkit-transition: 0.1s;
  transition: 0.1s;
  font-weight: 500;

  padding: 12px 20px;
  font-size: 14px;
  border-radius: 4px;
}
.el-form-item.is-error .el-input__inner,
.el-form-item.is-error .el-input__inner:focus,
.el-form-item.is-error .el-textarea__inner,
.el-form-item.is-error .el-textarea__inner:focus {
  border-color: #f56c6c;
}

.el-input__inner1 {
  background-color: #fff;
  background-image: none;
  border-radius: 4px;
  border: 1px solid #dcdfe6;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  color: #606266;
  display: inline-block;
  font-size: inherit;
  height: 40px;
  line-height: 40px;
  outline: 0;
  padding: 0 15px;
  -webkit-transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
  transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
  width: 100%;
}
.el-form-item__error {
  color: #f56c6c;
  font-size: 12px;
  line-height: 1;
  padding-top: 4px;
  position: absolute;
  top: 100%;
  left: 0;
}
</style>
