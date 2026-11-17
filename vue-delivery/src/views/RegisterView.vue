<template>
  <div class="login-body">
    <div class="login-container">
      <div class="head">
        <img class="logo" src="../static/img/kuaidi.png" />
        <div class="name">
          <div class="title">校园代取系统</div>
          <div class="tips">Vue3.0 系统</div>
        </div>
      </div>
      <el-form
        label-position="top"
        :rules="state.rules"
        :model="state.ruleForm"
        ref="registerForm"
        class="login-form"
      >
        <el-form-item label="账号" prop="username">
          <input
            class="el-input__inner1"
            v-model.trim="state.ruleForm.username"
            type="text"
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <input
            class="el-input__inner1"
            v-model.trim="state.ruleForm.email"
            type="text"
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="手机号码" prop="phone">
          <input
            class="el-input__inner1"
            v-model.trim="state.ruleForm.phone"
            type="text"
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <input
            class="el-input__inner1"
            v-model.trim="state.ruleForm.password"
            type="password"
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassWord">
          <input
            class="el-input__inner1"
            v-model.trim="state.ruleForm.confirmPassWord"
            type="password"
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="submitForm">提交</el-button>
          <el-button style="width: 100%" type="primary" @click="backlogin">返回登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { register } from '@/api/user.js'

const router = useRouter()

const registerForm = ref()

const validateUsername = (rule: any, value: any, callback: any) => {
  if (!value) {
    return callback(new Error('账号不能为空！'))
  } else {
    const reg = /^[a-zA-Z0-9_-]{4,16}$/
    if (reg.test(value)) {
      callback()
    } else {
      return callback(new Error('用户名需4到16位（字母，数字，下划线，减号）'))
    }
  }
}
const validatePass = (rule: any, value: any, callback: any) => {
  if (!value) {
    return callback(new Error('密码不能为空！'))
  } else {
    const reg = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/
    if (reg.test(value)) {
      callback()
    } else {
      return callback(new Error('最少6位，必须包含大写字母，小写字母，数字及特殊字符'))
    }
  }
}
const validateEmail = (rule: any, value: any, callback: any) => {
  if (!value) {
    return callback(new Error('邮箱不能为空！'))
  } else {
    const reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$/
    if (reg.test(value)) {
      callback()
    } else {
      return callback(new Error('邮箱格式不正确！'))
    }
  }
}
const checkPhone = (rule: any, value: any, callback: any) => {
  if (!value) {
    return callback(new Error('手机号不能为空'))
  } else {
    //验证手机号
    const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
    //验证区号
    const phoneReg = /^\d{3}-\d{8}|\d{4}-\d{7}$/
    console.log(reg.test(value))
    if (reg.test(value) || phoneReg.test(value)) {
      callback()
    } else {
      return callback(new Error('请输入正确的手机号码'))
    }
  }
}
const validateConfirmPass = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== state.ruleForm.password) {
    callback(new Error('两次输入的密码不相同!'))
  } else {
    callback()
  }
}
const state = reactive({
  ruleForm: {
    username: '',
    email: '',
    phone: '',
    password: '',
    confirmPassWord: ''
  },
  checked: true,
  rules: {
    username: [{ required: 'true', validator: validateUsername, trigger: 'blur' }],
    email: [{ required: 'true', validator: validateEmail, trigger: 'blur' }],
    phone: [{ required: 'true', validator: checkPhone, trigger: 'blur' }],
    password: [{ required: 'true', validator: validatePass, trigger: 'blur' }],
    confirmPassWord: [{ required: 'true', validator: validateConfirmPass, trigger: 'blur' }]
  }
})

const submitForm = async () => {
  registerForm.value.validate((valid) => {
    if (valid) {
      register(state.ruleForm).then((res) => {
        if (res.code === 2000) {
          ElMessage({
            type: 'success',
            message: '注册成功'
          })
          router.push('/login')
        }
      })
    } else {
      console.log('error submit!!')
      return false
    }
  })
}
const backlogin = () => {
  router.push('/login')
}
</script>

<style scoped>
.login-body {
  display: flex;
  width: 100%;
  background: url('../assets/img/background.png');
}
.login-container {
  width: 500px;
  height: 750px;
  background-color: #fff;
  border-radius: 4px;
  box-shadow: 0px 21px 41px 0px rgba(0, 0, 0, 0.2);
  margin: auto;
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
  line-height: 0.75;
  white-space: nowrap;
  cursor: pointer;
  border: 1px solid #dcdfe6;
  border-color: #dcdfe6;
  color: #fff;
  background-color: #1baeae;
  box-sizing: border-box;
  outline: 0;
  margin: 0;
  -webkit-transition: 0.1s;
  transition: 0.1s;
  font-weight: 600;
  margin-top: 10px;
  padding: 12px 20px;
  font-size: 15px;
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
