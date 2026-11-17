<template>
  <el-form
    ref="ruleFormRef"
    :model="ruleForm"
    :rules="rules"
    label-width="120px"
    class="demo-ruleForm"
    :size="formSize"
    status-icon
  >
    <el-form-item label="取件姓名" prop="name" class="inputname">
      <el-input placeholder="请输入取件人姓名" v-model="ruleForm.name" style="width: auto" />
    </el-form-item>
    <el-form-item label="联系电话" prop="phone">
      <el-input placeholder="请输入联系电话" v-model="ruleForm.phone" style="width: auto" />
    </el-form-item>
    <el-form-item label="快递单号" prop="phone">
      <el-input placeholder="请输入快递单号" v-model="ruleForm.delivery_num" style="width: auto" />
    </el-form-item>
    <el-form-item label="快递大小" prop="size">
      <el-select placeholder="请选择快递大小" v-model="ruleForm.size">
        <el-option label="小件" value="1" />
        <el-option label="中件" value="2" />
        <el-option label="大件" value="3" />
      </el-select>
    </el-form-item>
    <el-form-item label="取件地址" prop="address">
      <el-select placeholder="请选择取件地址" v-model="ruleForm.address">
        <el-option label="贵大西区学府里" value="1" />
        <el-option label="贵大北区老操场" value="2" />
        <el-option label="贵大北区正门邮政" value="3" />
        <el-option label="贵大北区正门京东" value="4" />
        <el-option label="贵大北区子弟学校菜鸟驿站" value="5" />
        <el-option label="贵大北区子弟学校菜鸟驿站" value="6" />
        <el-option label="贵大北区樟园宾馆旁顺丰" value="7 " />
      </el-select>
    </el-form-item>

    <el-form-item label="取件信息" style="bord" prop="message">
      <el-input placeholder="输入取件信息(取件码)" v-model="ruleForm.message" type="textarea" />
    </el-form-item>
    <el-form-item label="身份码" style="bord">
      <el-upload
        v-model:file-list="fileList"
        class="upload-demo"
        :http-request="uploadFile"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        list-type="picture"
        :on-success="handleUpImage"
      >
        <el-button type="primary">Click to upload</el-button>
        <template #tip>
          <div class="el-upload__tip">jpg/png files with a size less than 500kb</div>
        </template>
      </el-upload>
    </el-form-item>
    <el-form-item label="备注(可选)" style="bord" prop="remark">
      <el-input placeholder="所有人可见" v-model="ruleForm.remark" type="textarea" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm(ruleFormRef)"> 提交 </el-button>
      <el-button @click="resetForm(ruleFormRef)">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import { reactive, ref } from 'vue'
import request from '../utils/axios';
const formSize = ref('default')
const ruleFormRef = ref('')
const ruleForm = reactive({
  name: '',
  phone: '',
  size: '',
  address: '',
  count: '',
  date1: '',
  date2: '',
  delivery_num: '',
  type: [],
  resource: '',
  message: '',
  remark: ''
})

const rules = reactive({
  name: [
    { required: true, message: '请输入取件人姓名', trigger: 'blur' },
    { min: 0, max: 10, message: '姓名长度不能为空，不能超过10', trigger: 'blur' }
  ],
  phone: [
    {
      required: true,
      message: '请输入联系电话',
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
  address: [
    {
      required: true,
      message: '请选择取件地址',
      trigger: 'change'
    }
  ],

  date1: [
    {
      type: 'date',
      required: true,
      message: '请选择日期',
      trigger: 'change'
    }
  ],
  date2: [
    {
      type: 'date',
      required: true,
      message: '请选择时间',
      trigger: 'change'
    }
  ],
  type: [
    {
      type: 'array',
      required: true,
      message: 'Please select at least one activity type',
      trigger: 'change'
    }
  ],
  message: [
    {
      required: true,
      message: 'Please select activity resource',
      trigger: 'change'
    }
  ],
  desc: [{ required: true, message: 'Please input activity form', trigger: 'blur' }]
})
const submitForm = async (formEl) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log('submit!')
    } else {
      console.log('error submit!', fields)
    }
  })
}

const resetForm = (formEl) => {
  if (!formEl) return
  formEl.resetFields()
}
const uploadFile=() =>{} 
const dataSet = reactive({
  addfrom: {
    image: ''
  }
})
const handleUpImage = (res) => {
  dataSet.addform.image = res.data.url
}
</script>
