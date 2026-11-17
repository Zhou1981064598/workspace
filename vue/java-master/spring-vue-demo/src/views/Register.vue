<template>
<div style="width: 100% ;height: 100vh; background: #2e6da4;overflow: hidden">
  <div style="width: 400px;margin: 150px auto">
    <div style="color: #CCcccc; font-size: 30px;text-align: center;padding: 30px 0">注册用户</div>
    <el-form ref="form" :model="form" size="default" :rules="rules">
      <el-form-item prop="username">
        <el-input
            v-model="form.username"
            class="w-50 m-2"
            placeholder="请输入用户名"
            :prefix-icon="User"
        />
      </el-form-item>
      <el-form-item prop="password">
        <el-input
            v-model="form.password"
            class="w-50 m-2"
            placeholder="请输入密码"
            :prefix-icon="Lock"
            show-password
        />
      </el-form-item>
      <el-form-item prop="confirm">
        <el-input
            v-model="form.confirm"
            class="w-50 m-2"
            placeholder="请确认密码"
            :prefix-icon="Lock"
            show-password
        />
      </el-form-item>
      <el-form-item >
        <el-button style="width: 100%;" type="primary" @click="register">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</div>
</template>

<script>

import {User} from "@element-plus/icons-vue";
import {Lock} from "@element-plus/icons-vue";
import request from "@/utils/request";

export default {
  name: "Register",
  data(){
    return{
      form:{},
      rules:{
        username:[
          { required: true,message:'请输入用户名', trigger:'blur'},
        ],
        password:[
          { required: true,message:'请输入密码', trigger:'blur'},
        ],
        confirm:[
          { required: true,message:'请输入密码', trigger:'blur'},
        ]
      }
    }
  },
  methods:{
    register(){
      if (this.form.password !== this.form.confirm){
        this.$message({
          type:"error",
          message:'两次输入的密码不一致！！！'
        })
        return
      }
      this.$refs["form"].validate((valid)=>{
            if (valid){
              request.post("/user/register", this.form).then(res =>{
              if (res.code === '0'){
                this.$message({
                  type:"success",
                  message:"注册成功"
                })
                this.$router.push("/") //登录成功之后进行页面的跳转，跳转到主页
              }else {
                this.$message({
                  type:"error",
                  message:res.msg
                })
              }
            })
            }
      })

    }
  },
  setup(){
    return{
      User,
      Lock
    }
  }
}
</script>

<style scoped>

</style>