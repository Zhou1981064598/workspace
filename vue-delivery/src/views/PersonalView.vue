<template>
  <div align="center">
    <el-card style="height: 100%">
      <el-descriptions
        class="margin-top"
        title="个人信息"
        :column="1"
        :size="size"
        border
        style="width: 1200px"
      >
        <template #title>
          <span style="font-size: 20px; font-weight: 700">个人信息</span>
        </template>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              <el-icon :style="iconStyle">
                <user />
              </el-icon>
              用户名
            </div>
          </template>
          <div class="userInf" style="cursor: pointer" @click="updateMyName">
            {{ state.userInfo[0].userName }}
          </div>
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              <el-icon :style="iconStyle">
                <iphone />
              </el-icon>
              电话
            </div>
          </template>
          <div class="userInf" style="cursor: pointer" @click="updateMyPhone">
            {{ state.userInfo[0].phone }}
          </div>
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              <el-icon :style="iconStyle">
                <location />
              </el-icon>
              收货地址
            </div>
          </template>
          <div @click="getMyAdderss" class="userInf" style="cursor: pointer">查看我的收货地址</div>
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              <el-icon :style="iconStyle">
                <tickets />
              </el-icon>
              账号密码
            </div>
          </template>
          <el-tag @click="updatePassWord" style="width: 100px; font-size: 18px; cursor: pointer"
            >修改</el-tag
          >
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              <el-icon :style="iconStyle">
                <office-building />
              </el-icon>
              账号角色
            </div>
          </template>
          <div class="userInf">
            {{ state.roleName }}
          </div>
        </el-descriptions-item>
      </el-descriptions></el-card
    >
  </div>

  <el-dialog v-model="dialogTableVisible" title="我的地址">
    <el-dialog v-model="innerVisible" width="30%" title="请输入新地址" append-to-body>
      <el-form :model="form.address" :rules="rules" ref="ruleFormRef">
        <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="form.address.name" autocomplete="off" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="电话" :label-width="formLabelWidth" prop="phone">
          <el-input v-model="form.address.phone" autocomplete="off" placeholder="请输入电话号码" />
        </el-form-item>
        <el-form-item label="宿舍楼" :label-width="formLabelWidth" prop="building">
          <el-select v-model="form.address.building" placeholder="请选择宿舍楼">
            <el-option-group v-for="group in options" :key="group.label" :label="group.label">
              <el-option
                v-for="item in group.options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-option-group>
          </el-select>
        </el-form-item>
        <el-form-item label="寝室号" :label-width="formLabelWidth" prop="dormitoryNo">
          <el-input v-model="form.address.dormitoryNo" placeholder="请输入寝室号"> </el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="innerVisible = false">取消</el-button>
          <el-button type="primary" @click="submitForm(ruleFormRef)"> 确定 </el-button>
        </span>
      </template>
    </el-dialog>
    <el-table :data="state.address">
      <el-table-column property="name" label="姓名" width="150" />
      <el-table-column property="phone" label="电话" width="200" />
      <el-table-column property="building" label="宿舍楼" width="200" />
      <el-table-column property="dormitoryNo" label="寝室号" width="200" />
    </el-table>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="addressAdd"> 添加新地址 </el-button>
      </span>
    </template>
  </el-dialog>
  <el-dialog v-model="dialogNameFormVisible" title="修改用户名" width="500px">
    <el-form :model="form.name" :rules="rules" ref="ruleFormRef">
      <el-form-item label="新的用户名" :label-width="100" prop="name">
        <el-input v-model="form.name.name" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogNameFormVisible = false">取消</el-button>
        <el-button type="primary" @click="submitName(ruleFormRef)"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
  <el-dialog v-model="dialogPhoneFormVisible" title="修改手机号" width="500px">
    <el-form :model="form.phone" :rules="rules1" ref="ruleFormRef1">
      <el-form-item label="新的手机号" :label-width="100" prop="phone">
        <el-input v-model="form.phone.phone" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogPhoneFormVisible = false">取消</el-button>
        <el-button type="primary" @click="submitPhone(ruleFormRef1)"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
  <el-dialog v-model="dialogPasswordFormVisible" title="修改密码" width="500px">
    <el-form :model="form.password" :rules="rules" ref="ruleFormRef">
      <el-form-item label="新的密码" :label-width="100" prop="password">
        <el-input v-model="form.password.password" autocomplete="off" type="password" />
      </el-form-item>
      <el-form-item label="确认新密码" :label-width="100" prop="confirmPassWord">
        <el-input v-model="form.password.confirmPassWord" autocomplete="off" type="password" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogPasswordFormVisible = false">取消</el-button>
        <el-button type="primary" @click="submitPassword(ruleFormRef)"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { onMounted, computed, ref, reactive } from 'vue'
import { getaddress, addAddress } from '@/api/address.js'
import { getuser, updateName, updatePhone, updatepassword, getRole } from '@/api/user.js'
import type { FormInstance, FormRules } from 'element-plus'
import { ElMessage } from 'element-plus'

const state = reactive({
  name: '',
  userInfo: [{ userName: '', phone: '' }], // 用户信息变量
  role: [{ id: '', roleName: '' }],
  roleNameList: [''],
  roleName: '',
  hasBack: true, // 是否展示返回icon
  address: []
})
const checkPhone = (rule: any, value: any, callback: any) => {
  if (!value) {
    return callback(new Error('手机号不能为空'))
  } else {
    //验证手机号
    const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
    //验证区号
    const phoneReg = /^\d{3}-\d{8}|\d{4}-\d{7}$/
    if (reg.test(value) || phoneReg.test(value)) {
      callback()
    } else {
      return callback(new Error('请输入正确的联系电话'))
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
const validateConfirmPass = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== form.password.password) {
    callback(new Error('两次输入的密码不相同!'))
  } else {
    callback()
  }
}
const size = ref('')
const dialogTableVisible = ref(false)
const innerVisible = ref(false)
const dialogNameFormVisible = ref(false)
const dialogPhoneFormVisible = ref(false)
const dialogPasswordFormVisible = ref(false)
const formLabelWidth = '140px'

const ruleFormRef = ref<FormInstance>()
const ruleFormRef1 = ref<FormInstance>()

const rules = reactive<FormRules>({
  name: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 0, max: 10, message: '姓名长度不能为空,不能超过10', trigger: 'blur' }
  ],
  phone: [
    {
      required: true,
      validator: checkPhone,
      trigger: 'change'
    }
  ],

  building: [
    {
      required: true,
      message: '请选择寝室号',
      trigger: 'change'
    }
  ],
  dormitoryNo: [
    {
      required: true,
      message: '请选择寝室号',
      trigger: 'change'
    }
  ],
  password: [{ required: true, validator: validatePass, trigger: 'blur' }],
  confirmPassWord: [{ required: true, validator: validateConfirmPass, trigger: 'blur' }]
})
const rules1 = reactive<FormRules>({
  phone: [
    {
      required: true,
      validator: checkPhone,
      trigger: 'change'
    }
  ]
})
const form = reactive({
  name: { name: '' },
  phone: { phone: '' },
  address: {
    name: '',
    phone: '',
    building: '',
    dormitoryNo: ''
  },
  password: { password: '', confirmPassWord: '' }
})
// 初始化执行方法
onMounted(() => {
  getUserInfo()
})
// 获取用户信息
const getUserInfo = async () => {
  getuser().then((res) => {
    state.userInfo[0] = res.data
  })
  getRole().then((res) => {
    state.role = res.data
    for (let i = 0; i < state.role.length; i++) {
      state.roleNameList.push(state.role[i].roleName)
    }
    console.log(state.roleNameList)
    if (state.roleNameList.includes('管理员')) {
      state.roleName = '管理员'
    } else if (state.roleNameList.includes('骑手')) {
      state.roleName = '骑手'
    } else {
      state.roleName = '普通用户'
    }
  })
}
const options = [
  {
    label: '贵大北区',
    options: [
      {
        value: '北区一栋',
        label: '北区一栋'
      },
      {
        value: '北区二栋',
        label: '北区二栋'
      },
      {
        value: '北区三栋',
        label: '北区三栋'
      },
      {
        value: '北区四栋',
        label: '北区四栋'
      },
      {
        value: '北区五栋',
        label: '北区五栋'
      },
      {
        value: '北区六栋',
        label: '北区六栋'
      },
      {
        value: '北区七栋',
        label: '北区七栋'
      },
      {
        value: '北区八栋',
        label: '北区八栋'
      },
      {
        value: '北区九栋',
        label: '北区九栋'
      },
      {
        value: '北区十栋',
        label: '北区十栋'
      }
    ]
  },
  {
    label: '贵大东区',
    options: [
      {
        value: '东区一栋',
        label: '东区一栋'
      },
      {
        value: '东区二栋',
        label: '东区二栋'
      },
      {
        value: '东区三栋',
        label: '东区三栋'
      },
      {
        value: '东区四栋',
        label: '东区四栋'
      },
      {
        value: '东区五栋',
        label: '东区五栋'
      },
      {
        value: '东区六栋',
        label: '东区六栋'
      },
      {
        value: '东区七栋',
        label: '东区七栋'
      },
      {
        value: '东区八栋',
        label: '东区八栋'
      },
      {
        value: '东区九栋',
        label: '东区九栋'
      },
      {
        value: '东区十栋',
        label: '东区十栋'
      }
    ]
  },
  {
    label: '贵大西区',
    options: [
      {
        value: '西区一栋',
        label: '西区一栋'
      },
      {
        value: '西区二栋',
        label: '西区二栋'
      },
      {
        value: '西区三栋',
        label: '西区三栋'
      },
      {
        value: '西区四栋',
        label: '西区四栋'
      },
      {
        value: '西区五栋',
        label: '西区五栋'
      },
      {
        value: '西区六栋',
        label: '西区六栋'
      },
      {
        value: '西区七栋',
        label: '西区七栋'
      },
      {
        value: '西区八栋',
        label: '西区八栋'
      }
    ]
  }
]
const getMyAdderss = () => {
  dialogTableVisible.value = true
  if (state.address.length == 0) {
    getaddress().then((res) => {
      state.address = res.data
    })
  }
}
const addressAdd = () => {
  innerVisible.value = true
}
const iconStyle = computed(() => {
  const marginMap = {
    large: '8px',
    default: '6px',
    small: '4px'
  }
  return {
    marginRight: marginMap[size.value] || marginMap.default
  }
})
const updateMyName = () => {
  dialogNameFormVisible.value = true
}
const updateMyPhone = () => {
  dialogPhoneFormVisible.value = true
}
const submitName = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid) => {
    if (valid) {
      updateName(form.name).then((res) => {
        if (res.code == 2000) {
          ElMessage({
            type: 'success',
            message: '修改成功'
          })
          dialogNameFormVisible.value = false
          getUserInfo()
        }
      })
    }
  })
}
const submitPhone = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid) => {
    if (valid) {
      updatePhone(form.phone).then((res) => {
        if (res.code === 2000) {
          ElMessage({
            type: 'success',
            message: '修改成功'
          })
          getUserInfo()
          dialogPhoneFormVisible.value = false
        } else {
          ElMessage({
            type: 'error',
            message: '修改失败'
          })
          dialogPhoneFormVisible.value = false
        }
      })
    }
  })
}
const submitPassword = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid) => {
    if (valid) {
      updatepassword(form.password).then((res) => {
        if (res.code == 2000) {
          ElMessage({
            type: 'success',
            message: '修改成功'
          })
          dialogPasswordFormVisible.value = false
          getUserInfo()
        }
      })
    }
  })
}
const submitForm = async (formEl: FormInstance | undefined) => {
  console.log(formEl)
  console.log(form.address)
  if (!formEl) return
  await formEl.validate((valid) => {
    if (valid) {
      addAddress(form.address).then((res) => {
        if (res.code === 2000) {
          ElMessage({
            type: 'success',
            message: '添加成功'
          })
        }
      })
    }
  })
}
const updatePassWord = () => {
  dialogPasswordFormVisible.value = true
}
</script>

<style scoped>
.el-descriptions {
  margin-top: 20px;
}
.cell-item {
  font-weight: 600;
  font-size: 20px;
  display: flex;
  align-items: center;
  width: 150px;
}
.margin-top {
  margin-top: 20px;
  width: 70%;
}
.userInf {
  font-size: 20px;
}
.el-input {
  width: 300px;
}
.el-button--text {
  margin-right: 15px;
}
.el-select {
  width: 300px;
}
.el-input {
  width: 300px;
}
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
