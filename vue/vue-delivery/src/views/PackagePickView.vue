<template>
  <div class="common-layout">
    <el-container>
      <el-header><p style="text-align: center; font-size: 20px">请输入您的取件信息</p></el-header>
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
                    font-weight: 700;
                    color: rgb(23, 11, 240);
                  "
                  ><Postcard /></el-icon
                >取件姓名
              </div>
            </template>
            <el-input
              placeholder="请输入取件人姓名"
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
                    font-weight: 700;
                    color: rgb(23, 11, 240);
                  "
                  ><Phone /></el-icon
                >联系电话
              </div>
            </template>
            <el-input
              placeholder="请输入联系电话"
              v-model="ruleForm.phone"
              style="width: 35%; height: 40px"
            />
          </el-form-item>
          <el-form-item prop="deliveryNum">
            <template v-slot:label>
              <div class="font">
                <span style="color: red">* </span>
                <i
                  class="el-icon-xingming"
                  style="
                    position: relative;
                    top: 2px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 550;
                    color: rgb(23, 11, 240);
                  "
                ></i
                >快递单号
              </div>
            </template>
            <el-input
              placeholder="请输入快递单号"
              v-model="ruleForm.deliveryNum"
              style="width: 35%; height: 40px"
            />
          </el-form-item>

          <el-form-item prop="size">
            <template v-slot:label>
              <div class="font">
                <span style="color: red">* </span>
                <el-icon
                  style="
                    position: relative;
                    top: 5px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 700;
                    color: rgb(23, 11, 240);
                  "
                  ><MessageBox /></el-icon
                >快递大小
              </div>
            </template>
            <el-tooltip class="box-item" effect="dark" placement="top">
              <template #content
                >快递重量小于三公斤以下的属于小件<br />
                快递重量在3-35公斤左右的为大件<br />35-100kg公斤的快递属于超大件
              </template>
              <el-select
                placeholder="请选择快递大小"
                v-model="ruleForm.size"
                size="large"
                style="width: 35%; height: 40px"
                clearable
              >
                <el-option label="小件" value="小件" />
                <el-option label="大件" value="大件" />
                <el-option label="超大件" value="超大件" />
              </el-select>
            </el-tooltip>
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
                    font-weight: 700;
                    color: rgb(23, 11, 240);
                  "
                >
                  <location /> </el-icon
                >取件地址
              </div>
            </template>
            <el-select
              placeholder="请选择取件地址"
              v-model="ruleForm.pickupAddress"
              size="large"
              style="width: 35%"
              clearable
            >
              <el-option label="贵大西区学府里" value="贵大西区学府里" />
              <el-option label="贵大北区老操场" value="贵大北区老操场" />
              <el-option label="贵大北区正门邮政" value="贵大北区正门邮政" />
              <el-option label="贵大北区正门京东" value="贵大北区正门京东" />
              <el-option label="贵大北区子弟学校菜鸟驿站" value="贵大北区子弟学校菜鸟驿站" />
              <el-option label="贵大北区子弟学校菜鸟驿站" value="贵大北区子弟学校菜鸟驿站" />
              <el-option label="贵大北区樟园宾馆旁顺丰" value="贵大北区樟园宾馆旁顺丰 " />
            </el-select>
          </el-form-item>
          <el-form-item prop="acceptAddress">
            <template v-slot:label>
              <div class="font">
                <span style="color: red">* </span>
                <el-icon
                  style="
                    position: relative;
                    top: 5px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 700;
                    color: rgb(23, 11, 240);
                  "
                >
                  <location /> </el-icon
                >收货地址
              </div>
            </template>
            <el-popover placement="right" :width="800" trigger="click" ref="popoverRef">
              <template #reference>
                <el-button
                  style="margin-right: 16px; width: 35%; height: 40px"
                  @click="getAddress()"
                  v-model="ruleForm.acceptAddress"
                  >{{ ruleForm.acceptAddress }}</el-button
                >
              </template>
              <el-table
                :data="state.userInfo"
                @current-change="handleCurrentChange"
                style="cursor: pointer"
              >
                <template v-slot:empty>
                  <el-empty description="您还未添加收货地址哦，请先在个人中心设置收货地址！" />
                </template>
                <el-table-column width="150" property="name" label="姓名" />
                <el-table-column width="300" property="phone" label="电话" />
                <el-table-column width="150" property="building" label="宿舍楼" />
                <el-table-column width="150" property="dormitoryNo" label="寝室号" />
              </el-table>
            </el-popover>
          </el-form-item>

          <el-form-item style="bord" prop="message">
            <template v-slot:label>
              <div class="font">
                <span style="color: red">* </span>
                <el-icon
                  style="
                    position: relative;
                    top: 5px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 700;
                    color: rgb(23, 11, 240);
                  "
                >
                  <tickets /> </el-icon
                >取件信息
              </div>
            </template>
            <el-input
              placeholder="输入取件信息(取件码)"
              v-model="ruleForm.message"
              type="textarea"
              style="width: 70%"
            />
          </el-form-item>
          <el-form-item prop="idCode" style="bord">
            <template v-slot:label>
              <div class="font">
                <span style="color: red">* </span>
                <el-icon
                  style="
                    position: relative;
                    top: 5px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 700;
                    color: rgb(23, 11, 240);
                  "
                  ><Picture /></el-icon
                >身份码
              </div>
            </template>
            <el-upload
              ref="upload"
              :headers="headers"
              class="upload-demo"
              action="http://localhost:9090/delivery/upload"
              :on-remove="handleRemove"
              list-type="picture-card"
              v-model="ruleForm.idCode"
              :on-success="handleSuccess"
              :before-upload="beforeUpload"
              :limit="1"
              accept="image/png,image/gif,image/jpg,image/jpeg"
              :on-exceed="handleExceed"
              :auto-upload="true"
            >
              <el-icon class="avatar-uploader-icon"><Plus /></el-icon>
              <template #tip>
                <div class="el-upload__tip">
                  只能上传jpg gif png格式的图片,单张图片大小不超过2MB
                </div>
              </template>
            </el-upload>
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
                    font-weight: 700;
                    color: rgb(23, 11, 240);
                  "
                  ><Notebook /></el-icon
                >备注(可选)
              </div>
            </template>
            <el-input
              placeholder="所有人可见"
              v-model="ruleForm.remarks"
              type="textarea"
              show-word-limit
              style="width: 70%"
              maxlength="255"
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
</template>
<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { ElMessage, ElNotification } from 'element-plus'
import { getToken } from '@/utils/auth'
import { getaddress } from '@/api/address.js'
import { pick } from '@/api/package.js'

import type { FormInstance, FormRules, UploadProps } from 'element-plus'
const popoverRef = ref<any>(null)

const ruleFormRef = ref<FormInstance>()

const state = reactive({
  userInfo: [] // 用户信息变量
})
const handleCurrentChange = (row) => {
  popoverRef.value.hide()

  ruleForm.acceptAddress = row.building + '——' + row.dormitoryNo
}

const getAddress = async () => {
  if (state.userInfo.length == 0) {
    const userInfo = await getaddress()
    state.userInfo = userInfo.data
  }
}

const headers = ref({
  token: getToken()
})
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
//验证快递单号
const checkdeliverynum = (rule: any, value: any, callback: any) => {
  if (!value) {
    return callback(new Error('快递单号不能为空'))
  } else {
    //验证快递单号
    //  "name": "德邦物流",
    const DBL = /^[5789]\\d{9}$/

    //"name": "邮政快递包裹",
    const POSTB =
      /^([GA]|[KQ]|[PH]){2}[0-9]{9}([2-5][0-9]|[1][1-9]|[6][0-5])$|^[99]{2}[0-9]{11}$|^[96]{2}[0-9]{11}$|^[98]{2}[0-9]{11}$/

    //  "name": "圆通速递",
    const YTO =
      /^[A-Za-z0-9]{2}[0-9]{10}$|^[A-Za-z0-9]{2}[0-9]{8}$|^[6-9][0-9]{17}$|^[DD]{2}[8-9][0-9]{15}$|^[Y][0-9]{12}$/

    //  "name": "顺丰速运",
    const sf = /^[A-Za-z0-9-]{4,35}$/

    //    "name": "韵达快递",
    const YUNDA =
      /^(10|11|12|13|14|15|16|17|19|18|50|55|58|80|88|66|31|77|39)[0-9]{11}$|^[0-9]{13}$/

    //  "name": "中通快递",
    const ZTO =
      /^((768|765|778|828|618|680|518|528|688|010|880|660|805|988|628|205|717|718|728|761|762|763|701|757|719|751|358|100|200|118|128|689|738|359|779|852)[0-9]{9})$|^((5711|2008|7380|1180|2009|2013|2010|1000|1010)[0-9]{8})$|^((8010|8021|8831|8013)[0-9]{6})$|^((1111|90|36|11|50|53|37|39|91|93|94|95|96|98)[0-9]{10})$|^((a|b|h)[0-9]{13})$|^((90|80|60)[0-9]{7})$|^((80|81)[0-9]{6})$|^((21|23|24|25|93|94|95|96|97|110|111|112|113|114|115|116|117|118|119|121|122|123|124|125|126|127|128|129|130|131)[0-9]{8})$|^(100|101|102|103|104|105|106|107|503|504|505|506|507)[0-9]{10}$|^(4)[0-9]{11}$|^(120)[0-9]{9}$|^(780)[0-9]{9}$|^(881)[0-9]{9}$|^(882|885)[0-9]{9}$|^(91|92)[0-9]{10}$|^(54|55|56)[0-9]{10}$|^(63)[0-9]{10}$|^(7)[0-9]{9}$|^(64)[0-9]{10}$|^(72)[0-9]{10}$|^(220|221|223|224|225|226|227|228|229)[0-9]{7}$|^(21|22|23|24|25|26|27|28|29)[0-9]{10}$|^3[0-9]{9}$|^2710[0-9]{11}$|^731[0-9]{11}$|^751[0-9]{11}$|^7320[0-9]{10}$/

    //"name": "EMS",
    const EMS =
      /^[A-Z]{2}[0-9]{9}[A-Z]{2}$|^(10|11)[0-9]{11}$|^(50|51)[0-9]{11}$|^(95|97)[0-9]{11}$/

    //"name": "申通快递",
    const STO =
      /^(888|588|688|468|568|668|768|868|968)[0-9]{9}$|^(11|22)[0-9]{10}$|^(STO)[0-9]{10}$|^(37|33|11|22|44|55|66|77|88|99)[0-9]{11}$|^(4)[0-9]{11}$/

    if (
      DBL.test(value) ||
      POSTB.test(value) ||
      YTO.test(value) ||
      sf.test(value) ||
      YUNDA.test(value) ||
      ZTO.test(value) ||
      EMS.test(value) ||
      STO.test(value)
    ) {
      callback()
    } else {
      return callback(new Error('请输入正确的快递单号'))
    }
  }
}
//验证上传身份码
const checkdefile = (rule: any, value: any, callback: any) => {
  console.log(value)
  if (value.name == '') {
    return callback(new Error('请上传身份码 '))
  } else {
    callback()
  }
}
//验证接受地址
const checkaccpectaddress = (rule: any, value: any, callback: any) => {
  if (value == '请选择收货地址') {
    return callback(new Error('收货地址不能为空'))
  } else {
    callback()
  }
}

const ruleForm = reactive({
  name: '',
  phone: '',
  deliveryNum: '',
  size: '',
  pickupAddress: '',
  acceptAddress: '',
  message: '',
  remarks: '',
  idCode: { name: '' }
})
const dialogVisible = ref(false)

const rules = reactive<FormRules>({
  name: [
    { required: true, message: '请输入取件人姓名', trigger: 'blur' },
    { min: 0, max: 10, message: '姓名长度不能为空，不能超过10', trigger: 'blur' }
  ],
  phone: [
    {
      required: true,
      validator: checkPhone,
      trigger: 'change'
    }
  ],
  size: [
    {
      required: true,
      message: '请选择快递大小',
      trigger: 'change'
    }
  ],
  pickupAddress: [
    {
      required: true,
      message: '请选择取件地址',
      trigger: 'change'
    }
  ],
  acceptAddress: [
    {
      required: true,
      validator: checkaccpectaddress,
      trigger: 'change'
    }
  ],

  deliveryNum: [
    {
      required: true,
      validator: checkdeliverynum,

      trigger: 'change'
    }
  ],
  idCode: [
    {
      required: true,
      validator: checkdefile,
      trigger: 'change'
    }
  ],
  message: [
    {
      required: true,
      message: '请输入取件信息',
      trigger: 'change'
    }
  ]
})

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}

//上传成功将图片存入表单
const handleSuccess: UploadProps['onSuccess'] = (response, uploadFile) => {
  ruleForm.idCode = uploadFile
  ruleForm.idCode.name = response.data
}

//上传前对文件进行限制
const beforeUpload: UploadProps['beforeUpload'] = (rawFile) => {
  //图片格式数组
  let types = ['image/jpeg', 'image/gif', 'image/png']

  if (!types.includes(rawFile.type)) {
    ElMessage.error('只能上传jpg gif png格式的图片!')
    return false
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('图片大小不能超过2MB!')
    return false
  }
  return true
}
//删除上传图片
const handleRemove: UploadProps['onRemove'] = () => {}

//限制传文件个数
const handleExceed = () => {
  ElMessage.error(`只能上传一张图片`)
}

const submitForm = async (formEl: FormInstance | undefined) => {
  dialogVisible.value = false
  if (!formEl) return
  await formEl.validate((valid) => {
    const ruleForm1 = {
      name: ruleForm.name,
      phone: ruleForm.phone,
      deliveryNum: ruleForm.deliveryNum,
      size: ruleForm.size,
      pickupAddress: ruleForm.pickupAddress,
      acceptAddress: ruleForm.acceptAddress,
      message: ruleForm.message,
      remarks: ruleForm.remarks,
      idCode: ruleForm.idCode.name
    }
    if (valid) {
      pick(ruleForm1).then((res) => {
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
</style>
