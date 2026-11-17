<template>
  <div class="common-layout">
    <el-container>
      <el-header><p style="text-align: center; font-size: 20px">请输入您的寄件信息</p></el-header>
      <el-main>
        <el-form
          ref="ruleFormRef"
          :model="ruleForm"
          :rules="rules"
          label-width="120px"
          class="demo-ruleForm"
          :size="formSize"
          status-icon
          style="margin-left: 35%"
          label-position="top"
          hide-required-asterisk
        >
          <el-form-item prop="name" class="inputname">
            <template v-slot:label>
              <div class="font">
                <span style="color: red">* </span>
                <el-icon
                  style="
                    position: relative;
                    top: 5px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 550;
                    color: rgb(23, 11, 240);
                  "
                  ><Postcard /></el-icon
                >姓名
              </div>
            </template>
            <el-input
              placeholder="请输入寄件人姓名"
              v-model="ruleForm.name"
              style="width: 35%; height: 40px"
            />
          </el-form-item>
          <el-form-item prop="phone">
            <template v-slot:label>
              <div class="font">
                <span style="color: red">* </span>
                <el-icon
                  style="
                    position: relative;
                    top: 5px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 550;
                    color: rgb(23, 11, 240);
                  "
                  ><Phone /></el-icon
                >联系电话
              </div>
            </template>
            <el-input
              placeholder="请输入寄件联系电话"
              v-model="ruleForm.phone"
              style="width: 35%; height: 40px"
            />
          </el-form-item>
          <el-form-item prop="pickupAddress">
            <template v-slot:label>
              <div class="font">
                <span style="color: red">* </span>
                <el-icon
                  style="
                    position: relative;
                    top: 5px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 550;
                    color: rgb(23, 11, 240);
                  "
                >
                  <location /> </el-icon
                >取货地址
              </div>
            </template>
            <el-popover placement="right" :width="800" trigger="click" ref="popoverRef">
              <template #reference>
                <el-button
                  style="margin-right: 16px; width: 35%; height: 40px"
                  @click="getAddress()"
                  v-model="ruleForm.pickupAddress"
                  >{{ ruleForm.pickupAddress }}</el-button
                >
              </template>
              <el-table
                :data="state.userInfo"
                @current-change="handleCurrentChange"
                style="cursor: pointer"
              >
                <template v-slot:empty>
                  <el-empty description="您还未添加地址哦，请先在个人中心设置收货地址！" />
                </template>
                <el-table-column width="150" property="name" label="姓名" />
                <el-table-column width="300" property="phone" label="电话" />
                <el-table-column width="150" property="building" label="宿舍楼" />
                <el-table-column width="150" property="dormitoryNo" label="寝室号" />
              </el-table>
            </el-popover>
          </el-form-item>

          <el-form-item prop="weight" @click="inputNumberVisible = true">
            <template v-slot:label>
              <div class="font">
                <span style="color: red">* </span>
                <el-icon
                  style="
                    position: relative;
                    top: 5px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 550;
                    color: rgb(23, 11, 240);
                  "
                  ><MessageBox /></el-icon
                >重量
              </div>
            </template>
            <el-input
              v-model="ruleForm.weight"
              class="w-50 m-2"
              placeholder="请输入重量"
              :formatter="(value) => `$ ${value}`.replace(/^\.+|[^\d.]/g, '')"
              style="width: 35%; height: 40px; cursor: pointer"
            >
              <template #suffix>
                <el-icon><ArrowRight /></el-icon>
              </template>
              <template #append>kg</template>
            </el-input>
          </el-form-item>

          <el-form-item prop="sendAddress">
            <template v-slot:label>
              <div class="font">
                <span style="color: red">* </span>
                <el-icon
                  style="
                    position: relative;
                    top: 5px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 550;
                    color: rgb(23, 11, 240);
                  "
                >
                  <location /> </el-icon
                >寄送商家
              </div>
            </template>
            <el-select
              placeholder="请选择寄送商家"
              v-model="ruleForm.sendAddress"
              size="large"
              style="width: 35%"
              clearable
            >
              <el-option label="邮政" value="邮政" />
              <el-option label="顺丰" value="顺丰" />
              <el-option label="中通" value="中通" />
              <el-option label="圆通" value="圆通" />
              <el-option label="韵达" value="韵达" />
              <el-option label="申通" value="申通" />
            </el-select>
          </el-form-item>
          <el-form-item prop="sendType">
            <template v-slot:label>
              <div class="font">
                <span style="color: red">* </span>
                <el-icon
                  style="
                    position: relative;
                    top: 5px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 550;
                    color: rgb(23, 11, 240);
                  "
                >
                  <Notification
                /></el-icon>
                寄送类型
              </div>
            </template>
            <el-select
              placeholder="请选择寄送类型"
              v-model="ruleForm.sendType"
              size="large"
              style="width: 35%"
              clearable
            >
              <el-alert
                type="info"
                show-icon
                style="margin-top: -1%"
                :closable="false"
                title="寄件分类"
                ><template #default>
                  <span
                    >1.文件类邮寄物品：包括信件、明信片、账单、合同、保单等，通常采用平信、挂号信、特快专递等邮寄方式<br />
                    2.商品类邮寄物品：包括服装、食品、电子产品、化妆品、玩具、书籍等各类商品，采用快递、EMS、空运等邮寄方式，可以根据重量、体积大小决定运输方式<br />
                    3.礼品类邮寄物品：包括生日礼物、结婚礼物、纪念品等，通常采用快递、EMS等邮
                    寄方式，一般需要在物品外包装上标明礼物及其价值<br />
                    4.生活用品类邮寄物品：包括毛巾、被子、袜子等各种生活用品一般采用快递、国内普通包裹邮寄<br />
                    5.花卉类邮寄物品：包括各种鲜花、绿植等可以用国内普通包裹邮寄</span
                  >
                </template></el-alert
              >
              <el-option label="国内特快专送" value="国内特快专送" />
              <el-option label="快递包裹" value="快递包裹" />
              <el-option label="国内普通包裹" value="国内普通包裹" />
              <el-option label="挂号信" value="挂号信" />
              <el-option label="平信" value="平信" />
              <el-option label="印刷品" value="印刷品" />
              <el-option label="国内挂号印刷品" value="国内挂号印刷品 " />
            </el-select>
          </el-form-item>

          <el-form-item prop="acceptName" class="inputname">
            <template v-slot:label>
              <div class="font">
                <span style="color: red">* </span>
                <el-icon
                  style="
                    position: relative;
                    top: 5px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 550;
                    color: rgb(23, 11, 240);
                  "
                  ><Postcard /></el-icon
                >收件人姓名
              </div>
            </template>
            <el-input
              placeholder="请输入收件人姓名"
              v-model="ruleForm.acceptName"
              style="width: 35%; height: 40px"
            />
          </el-form-item>
          <el-form-item prop="acceptPhone">
            <template v-slot:label>
              <div class="font">
                <span style="color: red">* </span>
                <el-icon
                  style="
                    position: relative;
                    top: 5px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 550;
                    color: rgb(23, 11, 240);
                  "
                  ><Phone /></el-icon
                >收件人联系电话
              </div>
            </template>
            <el-input
              placeholder="请输入收件人联系电话"
              v-model="ruleForm.acceptPhone"
              style="width: 35%; height: 40px"
            />
          </el-form-item>
          <el-form-item prop="acceptAddress"
            ><template v-slot:label>
              <div class="font">
                <span style="color: red">* </span>
                <el-icon
                  style="
                    position: relative;
                    top: 5px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 550;
                    color: rgb(23, 11, 240);
                  "
                >
                  <location /> </el-icon
                >收件人地址
              </div>
            </template>
            <div style="width: 100%">
              <el-cascader
                size="large"
                :options="options"
                v-model="ruleForm.selectedOptions"
                @change="handleChange"
                style="width: 35%"
              >
              </el-cascader
              ><br />
              <el-input
                v-model="ruleForm.acceptAddress"
                style="height: 40px; margin-top: 10px; width: 35%"
                ><template #prepend>详细地址</template></el-input
              >
            </div>
          </el-form-item>
          <el-form-item prop="uuencode">
            <template v-slot:label>
              <div class="font">
                <el-icon
                  style="
                    position: relative;
                    top: 5px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 550;
                    color: rgb(23, 11, 240);
                  "
                  ><Memo /></el-icon
                >邮政编码
              </div>
            </template>
            <el-input
              placeholder="请输入邮政编码(选填)"
              v-model="ruleForm.uuencode"
              style="width: 35%; height: 40px"
            />
          </el-form-item>
          <el-form-item style="bord" prop="remark">
            <template v-slot:label>
              <div class="font">
                <el-icon
                  style="
                    position: relative;
                    top: 5px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 550;
                    color: rgb(23, 11, 240);
                  "
                  ><Notebook /></el-icon
                >备注(可选)
              </div>
            </template>
            <el-input
              placeholder="您可以对骑手进行备注(选填)"
              v-model="ruleForm.remarks"
              type="textarea"
              show-word-limit
              maxlength="64"
              style="width: 70%"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="showDialog()"> 提交 </el-button>
            <el-button @click="resetForm(ruleFormRef)">重置</el-button>
          </el-form-item>
        </el-form></el-main
      >
    </el-container>
  </div>

  <el-dialog v-model="dialogVisible" width="30%">
    <span>确定要发布该任务吗？</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm(ruleFormRef)"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
  <el-dialog v-model="inputNumberVisible" width="30%" title="重量预估" class="inputnumber">
    <el-input-number v-model="ruleForm.weight" :min="1" :max="50" style="margin-left: 38%">
    </el-input-number>
    <span>kg</span>
    <el-alert
      type="info"
      description="注：实际重量以交寄时核定为准，物品体积较大时可能会按体积重量收费，录入长宽高，预估费用更准确，寄件重量不超过50kg"
      show-icon
      style="margin-top: 20px"
      :closable="false"
    />
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="inputNumberVisible = false" style="margin-right: 45%">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { getaddress } from '@/api/address.js'
import { send } from '@/api/package.js'
import { ArrowRight } from '@element-plus/icons-vue'
import { regionData, codeToText } from 'element-china-area-data'
import type { FormInstance, FormRules } from 'element-plus'
import { ElNotification } from 'element-plus'

const options = ref(regionData)
const popoverRef = ref<any>(null)
const ruleFormRef = ref<FormInstance>()

const state = reactive({
  userInfo: [] // 用户信息变量
})
const handleCurrentChange = (row) => {
  popoverRef.value.hide()
  ruleForm.pickupAddress = row.building + '——' + row.dormitoryNo
}

const getAddress = async () => {
  if (state.userInfo.length == 0) {
    const userInfo = await getaddress()
    state.userInfo = userInfo.data
  }
}

const showDialog = () => {
  dialogVisible.value = true
}
//验证电话号码
const formSize = ref('default')
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

//验证接受地址
const checkaccpectaddress = (rule: any, value: any, callback: any) => {
  if (value == '请选择取货地址') {
    return callback(new Error('取货地址不能为空'))
  } else {
    callback()
  }
}
//验证邮政编码
const checkuuencode = (rule: any, value: any, callback: any) => {
  if (!value) {
    return callback()
  }
  const reg = /^[0-9]\d{5}$/
  if (reg.test(value)) {
    callback()
  } else {
    return callback(new Error('请输入正确邮政编码'))
  }
}
const ruleForm = reactive({
  name: '',
  acceptName: '',
  acceptPhone: '',
  acceptAddress: '',
  uuencode: '',
  phone: '',
  weight: 1,
  sendType: '',

  // 存储省市区选中的ID值
  selectedOptions: [],
  pickupAddress: '请选择取货地址',
  sendAddress: '',
  remarks: '',
  idCode: { name: '' }
})
const dialogVisible = ref(false)
const inputNumberVisible = ref(false)
const rules = reactive<FormRules>({
  name: [
    { required: true, message: '请输入寄件人姓名', trigger: 'blur' },
    { min: 0, max: 10, message: '姓名长度不能为空,不能超过10', trigger: 'blur' }
  ],
  acceptName: [
    { required: true, message: '请输入收件人姓名', trigger: 'blur' },
    { min: 0, max: 10, message: '姓名长度不能为空,不能超过10', trigger: 'blur' }
  ],
  phone: [
    {
      required: true,
      validator: checkPhone,
      trigger: 'change'
    }
  ],
  acceptPhone: [
    {
      required: true,
      validator: checkPhone,
      trigger: 'change'
    }
  ],
  weight: [
    {
      required: true,
      message: '请输入重量',
      trigger: 'change'
    }
  ],
  acceptAddress: [
    {
      required: true,
      message: '请输入收件人地址',
      trigger: 'change'
    }
  ],
  sendAddress: [
    {
      required: true,
      message: '请选择寄送商家',
      trigger: 'change'
    }
  ],
  sendType: [
    {
      required: true,
      message: '请选择寄送类型',
      trigger: 'change'
    }
  ],
  pickupAddress: [
    {
      required: true,
      validator: checkaccpectaddress,
      trigger: 'change'
    }
  ],

  message: [
    {
      required: true,
      message: '请输入取件信息',
      trigger: 'change'
    }
  ],
  uuencode: [
    {
      required: false,
      validator: checkuuencode
    }
  ]
})
const handleChange = (value) => {
  ruleForm.selectedOptions = value
  var name = ''
  ruleForm.selectedOptions.map((item) => (name += codeToText[item] + '')) //将省市区三个拼接一起
  ruleForm.acceptAddress = name
}

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}

const submitForm = async (formEl: FormInstance | undefined) => {
  dialogVisible.value = false
  if (!formEl) return
  await formEl.validate((valid) => {
    if (valid) {
      send(ruleForm).then((res) => {
        if (res.code === 2000) {
          ElMessage({
            type: 'success',
            message: '发布成功'
          })
        }
      })
    } else {
      open4()
      return false
    }
  })
}
const open4 = () => {
  ElNotification({
    title: '输入错误',
    message: '请输入正确的信息',
    type: 'error'
  })
}
</script>
<style>
.tooltip-base-box .box-item {
  width: 300px;
  margin-top: 10px;
}
.el-form-item__error {
  font-size: 13px;
  color: red;
  font-weight: 600;
}
.font {
  font-weight: 600;
}
.inputnumber {
  width: 700px;
}
</style>
