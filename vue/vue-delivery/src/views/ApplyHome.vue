<template>
  <el-tabs type="border-card">
    <el-tab-pane label="申请骑手">
      <template #label>
        <i
          class="el-icon-basepermissionapproveApply"
          style="
            position: relative;
            top: 2px;
            margin: 0 5px 0 0px;
            font-size: 20px;
            font-weight: 550;
            color: rgb(18, 18, 19);
          "
        ></i>
        <span>申请骑手</span></template
      >
      <el-steps :active="active" finish-status="success" simple style="margin-top: 20px">
        <el-step title="第一步"> </el-step>
        <el-step title="第二步" />
        <el-step title="第三步" />
      </el-steps>
      <div class="form-body" v-if="active == 1">
        <el-button
          style="
            margin-bottom: 5px;
            margin-left: 40%;
            font-size: 15px;
            font-weight: 600;
            size: large;
            width: 275px;
            height: 40px;
          "
          @click="centerDialogVisible = true"
          ><el-icon class="el-icon--left" style="font-size: 20px"><ZoomIn /></el-icon
          >申请接单常见问题说明</el-button
        >
        <el-form
          ref="ruleFormRef"
          hide-required-asterisk
          :model="ruleForm"
          :rules="rules"
          label-width="120px"
          class="demo-ruleForm"
          :size="formSize"
          style="margin-left: 35%"
          status-icon
          label-position="top"
          ><el-form-item prop="profession">
            <template v-slot:label>
              <div class="font">
                <span style="color: red">*</span>

                <i
                  class="el-icon-renshizhiye"
                  style="
                    position: relative;
                    top: 2px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 550;
                    color: rgb(23, 11, 240);
                  "
                ></i
                >职业
              </div>
            </template>
            <el-select
              v-model="ruleForm.profession"
              placeholder="请选择您的职业"
              :options="options"
              size="large"
              style="width: 40%; height: 40px"
            >
              <el-option label="学生" value="学生" />
              <el-option label="教师" value="教师" />
              <el-option label="其他" value="其他"
            /></el-select>
          </el-form-item>
          <el-form-item prop="name">
            <template v-slot:label>
              <div class="font">
                <span style="color: red">*</span>

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
                >姓名
              </div>
            </template>
            <el-input
              v-model="ruleForm.name"
              style="width: 40%; height: 40px"
              placeholder="请输入姓名"
            />
          </el-form-item>
          <el-form-item prop="sno" :rule="[{ type: 'number', message: '学号必须是数字' }]">
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
                  ><Postcard /></el-icon
                >学号
              </div>
            </template>
            <el-input
              v-model.number="ruleForm.sno"
              style="width: 40%; height: 40px"
              placeholder="职业为学生时填写"
            />
          </el-form-item>
          <el-form-item prop="preferences">
            <template v-slot:label>
              <div class="font">
                <el-icon
                  style="
                    position: relative;
                    top: 5px;
                    margin: 0 5px 0 -3px;
                    font-size: 20px;
                    font-weight: 700;
                    color: rgb(23, 11, 240);
                  "
                  ><Star /></el-icon
                >接单偏好
              </div>
            </template>

            <el-checkbox-group v-model="ruleForm.preferences">
              <el-checkbox label="快递代取" name="快递代取">
                <template v-slot:label>
                  <div class="font">快递代取</div>
                  <div>快递代取</div>
                </template>
              </el-checkbox>
              <el-checkbox label="快递待寄" name="快递待寄" />
              <el-checkbox label="打印服务" name="打印服务" />
              <el-checkbox label="其他" name="其他" />
            </el-checkbox-group>
          </el-form-item>

          <el-form-item prop="identification"
            ><template v-slot:label>
              <div class="font">
                <span style="color: red">*</span>

                <i
                  class="el-icon-shenfen"
                  style="
                    position: relative;
                    top: 2px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 550;
                    color: rgb(23, 11, 240);
                  "
                ></i
                >身份证明
              </div>
            </template>
            <el-upload
              ref="upload"
              :headers="headers"
              class="upload-demo"
              action="http://localhost:9090/delivery/upload"
              :on-remove="handleRemove"
              list-type="picture-card"
              v-model="ruleForm.identification"
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
          <el-form-item>
            <template v-slot:label>
              <div class="font">
                <i
                  class="el-icon-weixin"
                  style="
                    position: relative;
                    top: 2px;
                    margin: 0 5px 0 0px;
                    font-size: 20px;
                    font-weight: 500;
                    color: rgb(23, 11, 240);
                  "
                ></i
                >加急审核
              </div>
            </template>
            <el-button color="rgb(23, 11, 240)" @click="centerDialogVisible2 = true"
              >联系校区管理员</el-button
            >
          </el-form-item>
          <el-form-item>
            <el-button @click="nextstep(0)"> 上一步 </el-button>
            <el-button type="primary" @click="submitForm(ruleFormRef, 3)">下一步</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div v-if="active == 0">
        <div class="demo-collapse" style="margin-top: 15px">
          <el-collapse v-model="activeNames" @change="handleChange">
            <el-collapse-item name="1" style="font-size: 20px">
              <template v-slot:title
                ><div style="font-size: 23px; margin-left: 45%">申请骑手协议</div></template
              >
              <el-collapse-item name="2" style="font-size: 20px"
                ><template v-slot:title> <div style="font-size: 20px">一、定义</div></template>
                <div class="text">
                  1.跑跑乐校园平台:指平台的名称。
                  <br />2.跑跑乐校园平台:用来给大学生提供各种便利服务的平台。<br />
                  3.平台用户:指在跑跑乐校园寻求便利服务的人群。<br />4.接单用户:指在跑跑乐校园为他人提供便利服务的人群。
                </div>
              </el-collapse-item>
              <el-collapse-item name="3"
                ><template v-slot:title>
                  <div style="font-size: 20px">二、接单用户的权力和义务</div></template
                >
                <div class="text">
                  1.平台为确保平台用户和平台的安全和利益，接单用户申请人需提供以下信息:<br />
                  用户真实姓名、学生证。待平台验证信息属实后方可进行接单任务。<br />
                  2.接单用户申请成功后，即可在本平台接受接单任务。接单用户承诺遵守本协议以及所有公示于跑跑乐校园平台的已发布的或将来可能发布的各类规则。<br />
                  所有规则为本协议不可分割的一部分，与协议正文具有同等法律效力。如接单用户不同意相关变更，必须停止使用跑跑乐校园。继续使用则表示接单用户接受经修订的协议或规则。<br />
                  3.接单用户需遵守与跑跑乐校园签订的协议进行接单活动;接单用户一旦违背跑跑乐校园平台的规则，跑跑乐校园可随时解除与接单用户的合作关系;<br />
                  4.接单用户在接单过程中的注意事项:接单用户应在其时间、地点合理的情况下进行接单，并保证当天接的单在当天完成，逾期将扣除一定费用。<br />
                  (1）接单用户如遇突发情况，无法完成订单，应及时联系校区客服取消订单。<br />
                  (2)接单用户不得恶意破坏订单实物，若订单实物受损或，将由接单用户承担一切责任。<br />
                  (3）接单用户需要把物品送到接单员手中，方可以离开，若无送到接单员手中，出现任何丢件问题将由接单用户承担一切责任。<br />
                  5.在给平台用户提供服务的过程中，接单用户谨记:a)不得私自以跑跑乐校园的名义对外承诺任何事，否则后果由接单用户自己承担;<br />
                  b)不得逃避平台规定的服务费用;私下联系用户，让其将订单取消，并私下解决其订单要<br />
                  求;否则该订单不予退款并给予接单用户警告处分并取消该接单用户的接单资格。
                </div>
              </el-collapse-item>
              <el-collapse-item name="4"
                ><template v-slot:title>
                  <div style="font-size: 20px">三、跑跑乐校园的权利和义务</div></template
                >
                <div class="text">
                  1.跑跑乐校园根据本协议向接单用户提供信息及数据管理服务。<br />
                  2.跑跑乐校园有义务在现有技术上维护跑跑乐校园的正常运行，并努力提升和改进技术。<br />
                  3.跑跑乐校园有权利与不符合跑跑乐校园要求或未尽其义务的接单用户终止本协议并将其清退出跑跑乐校园。<br />
                  4.因网上平台的特殊性，跑跑乐校园没有义务对所有用户的信息、所有的交易行为以及有关的其它事项进行事先审查，但如存在下列情况:<br />
                  a)用户或其它第三方通知，认为某个具体用户或具体交易事项可能存在重大问题;<br />
                  b)用户或其它第三方向跑跑乐校园有违法或不当行为的，跑跑乐校园以标准对相关内容进行判别，可以明显认为该订单具有违法或不正当性质的;<br />
                  跑跑乐校园有权根据不同情况选择保留或删除相关信息或继续、停止对该用户提供服务，并追究相关法律责任。<br />

                  5.在跑跑乐校园上的交易与其它用户发生纠纷或诉讼的，用户通过司法机关或行政执法机关依照法定程序要求跑跑乐校园提供相关数据，跑跑乐校园应积极配合并提供有关资料。<br />
                  6.跑跑乐校园有权对接单用户的基本信息、交易信息及交易行为进行查阅，发现存在任何问题或怀疑，均有权向接单用户发出询问及要求改正的通知或者取消其接单用户的身份。<br />
                  7.经国家生效法律文书或行政处罚决定确认接单用户存在违法行为，或者跑跑乐校园有足够事实依据可以认定接单用户存在违法或违反协议行为的，跑跑乐校园有权在跑跑乐校园公布接单用户的违法或违规行为。<br />
                  8.接单用户在接收订单后，应及时完成相应订单，若没有按用户规定的时间完成任务，平台将扣除一定比例的金额(根据逾期情况而定)。频繁产生此情况者将被取消接单用户资格。<br />
                  9.接单用户在接收订单后，没有按要求完成订单，平台将扣除一定比例的金额。频繁产生此情况者将被取消接单用户资格。<br />
                  10.接单用户在接收订单后，发生逃单情况。平台将扣除所有金额并退款给用户。并取消接单用户资格。接单用户在发生特殊情况时，应及时联系校区客服取消该订单。<br />
                  11.接单员需到用户的允许下才可以把商晶放在所要求的区域，禁止擅自把用户的商品随意丢弃，否则若丢失后果由接单员承担所有责任。<br />
                  12.若因为接单员原因导致快递丢失或损坏，则接单员承担所有主要责任，并承担所丢失或损坏商品的费用，管理者负次要责任需配合相关部门进行协商探讨。
                </div>
              </el-collapse-item>
              <el-collapse-item name="5">
                <template v-slot:title> <div style="font-size: 20px">四、利润分配</div></template>
                <div class="text">
                  1.根据校区费率查看中的报表就行收取费率。2.跑跑乐校园可收取逾期订单的费用。
                </div>
              </el-collapse-item>
              <el-collapse-item name="6">
                <template v-slot:title> <div style="font-size: 20px">五、隐私保护</div></template>
                <div class="text">
                  1.跑跑乐校园将保护并保密所有接单人的信息，包括接单用户的学生证照、姓名、手机号码等。
                </div>
              </el-collapse-item>
              <el-collapse-item name="7">
                <template v-slot:title> <div style="font-size: 20px">六、免责声明</div></template>
                <div class="text">
                  1.接单用户与用户之间产生纠纷并造成一定人为伤害的，跑跑乐校园有权取消接单用户资格。<br />
                  2.接单用户向平台声明收到用户恶意举报，跑跑乐校园有义务进行调查，查明原因确实确定属于恶意举报的，跑跑乐校园将继续与接单用户履行协议。<br />
                  3.接单用户存在撒谎、隐瞒用户并造成用户一定损失的，跑跑乐校园有权取消接单用户资格。<br />
                  4.跑跑乐校园对接单用户的身份信息保护和保密，但若信息遭遇强大黑客攻破而泄露，跑跑乐校园将此视为意外风险，不可把控的因素，平台不对此负责。<br />
                  5.接单用户不得接受任何黄赌毒相关需求，接单用户一旦接收这类需求后所造成的后果将由接单用户个人承担，跑跑乐校园只承担监管失误。<br />
                  6.接单用户不得接收社会人士的需求，接单用户一旦接收社会人士（超出学校范围的订单)需求并造成的损失由接单用户个人承担，跑跑乐校园只承担监管不失误。<br />
                  7.接单用户在完成订单期间受伤或发生意外由接单用户个人承担。<br />
                  8.接单用户在跑跑乐校园平台上遭受一些不明身份的人的欺诈、伤害的，由接单用户个人承担。<br />
                  9.接单用户不得私自添加用户，引导用户脱离本平台交易，一经发现平台有权取消接单的资格，佣金清零，不予结算。<br />
                  接单用户作为使用跑跑乐校园一份子，需要您拥有良好的自我判断力，较强的执行力，乐于助人的精神。在此，希望各位接单用户履行好各自的权利与义务，遵守跑跑乐校园的规则和协议。感谢大家的支持与理解!!
                </div> </el-collapse-item
              ><el-button
                type="primary"
                style="margin-top: 10px; margin-left: 46%; width: 100px"
                round
                @click="nextstep(1)"
                >同意协议</el-button
              ></el-collapse-item
            >
          </el-collapse>
        </div>
      </div>

      <el-dialog v-model="centerDialogVisible" title="常见问题" width="30%" center>
        <div class="text">
          1、学号指学生证上面的号码。<br />2、身份证明指的是可以证明自己身份的图片。<br />3、需要加急请点击微信客服添加好友加急申请!
        </div>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="centerDialogVisible = false"> 确认 </el-button>
          </span>
        </template>
      </el-dialog>
      <el-dialog v-model="centerDialogVisible2" title="管理员联系方式" width="30%" center>
        <div class="text">
          管理员微信号:Zhou1585164882<br />请在工作日时联系管理员<br />管理员工作时间:中午是12：00之前，下午是18：00之前
        </div>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="centerDialogVisible2 = false">取消</el-button>
            <el-button type="primary" @click="centerDialogVisible2 = false"> 确认 </el-button>
          </span>
        </template>
      </el-dialog>

      <el-result
        icon="success"
        title="申请成功"
        sub-title="管理员会在7个工作日内完成审核,请耐心等待"
        v-if="active == 3"
      >
        <template #extra>
          <el-button type="primary" @click="backhome">返回首页</el-button>
        </template>
      </el-result>
    </el-tab-pane>
    <el-tab-pane>
      <template #label>
        <i
          class="el-icon-wodeshenqing-moren"
          style="
            position: relative;
            top: 2px;
            margin: 0 5px 0 0px;
            font-size: 20px;
            font-weight: 550;
            color: rgb(18, 18, 19);
          "
        ></i>
        <span>我的申请</span></template
      >
      <el-empty v-if="applytotal <= 0" description="您还未提交任何申请哦！" />

      <el-timeline v-if="applytotal > 0">
        <el-timeline-item :timestamp="myapply.applyInfo.applyTime" placement="top" type="success">
          <!-- <template #icon>
            <i
              class="el-icon-wodeshenqing-moren"
              style="
                position: relative;
                top: 2px;
                margin: 0 5px 0 0px;
                font-size: 20px;
                font-weight: 550;
                color: rgb(18, 18, 19);
              "
            ></i>
          </template> -->
          <el-card>
            <h4 style="text-align: center">申请提交成功，等待管理员审核</h4>
            <p style="color: rgb(144, 147, 151); font-size: 10px; text-align: center">
              管理员会在7个工作日内完成审核
            </p>
          </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="管理员审核" placement="top" :type="myapply.status">
          <el-card>
            <h4 v-if="myapply.applyInfo.status == 1" style="text-align: center">
              审核中，请耐心等待
            </h4>
            <h4 v-if="myapply.applyInfo.status == 2" style="text-align: center">
              您的申请已审核完成
            </h4>
          </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="审核结果" placement="top" :type="myapply.result">
          <el-card v-if="myapply.applyInfo.status == 2">
            <el-result
              v-if="myapply.applyInfo.auditResult == 1"
              icon="success"
              title="审核通过"
              sub-title="恭喜您申请成为骑手成功,现再您可以在任务大厅接取任务赚取佣金啦！"
            >
            </el-result>
            <el-result
              v-if="myapply.applyInfo.auditResult == 0"
              icon="error"
              title="非常抱歉，您的申请审核未通过"
              sub-title="具体原因请点击按钮查看"
            >
              <template #extra>
                <el-button type="primary">查看详情</el-button>
              </template>
            </el-result>
          </el-card>
        </el-timeline-item>
      </el-timeline></el-tab-pane
    >
  </el-tabs>
</template>
<script lang="ts" setup>
import { reactive, ref, onMounted } from 'vue'
import { ZoomIn } from '@element-plus/icons-vue'
import type { ElIcon, FormInstance, FormRules, UploadProps } from 'element-plus'
import { getToken } from '@/utils/auth'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'

import { submitRiderApply, getMyApply } from '@/api/user.js'
const formSize = ref('default')
const active = ref(0)
const ruleFormRef = ref<FormInstance>()
const router = useRouter()

const ruleForm = reactive({
  name: '',
  profession: '',
  preferences: [],
  identification: { name: '' },
  sno: ''
})
const activeNames = ref(['1', '2', '3', '4', '5', '6'])
const handleChange = (val: string[]) => {
  console.log(val)
}
const checkSno = (rule: any, value: any, callback: any) => {
  if (ruleForm.profession == '学生') {
    if (!value) {
      return callback(new Error('请输入学号 '))
    } else {
      callback()
    }
  } else {
    callback()
  }
}
//验证是否上传身份证明
const checkdefile = (rule: any, value: any, callback: any) => {
  console.log(value)
  if (value.name == '') {
    return callback(new Error('请上传身份证明 '))
  } else {
    callback()
  }
}
const rules = reactive<FormRules>({
  name: [
    { required: true, message: '请输入姓名', trigger: 'blur' },
    { min: 2, max: 5, message: '姓名长度为2-5', trigger: 'blur' }
  ],
  identification: [
    {
      required: true,
      validator: checkdefile,
      trigger: 'change'
    }
  ],
  profession: [{ required: true, message: '请选择您的职业', trigger: 'blur' }],
  sno: [{ validator: checkSno }]
})
const centerDialogVisible = ref(false)
const centerDialogVisible2 = ref(false)

const headers = ref({
  token: getToken()
})
const submitForm = async (formEl: FormInstance | undefined, step: number) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    let list = ruleForm.preferences
    let preferences = ''
    for (let i = 0; i < list.length; i++) {
      preferences += list[i] + ';'
    }
    const ruleForm1 = {
      name: ruleForm.name,
      profession: ruleForm.profession,
      preferences: preferences,
      identification: ruleForm.identification.name,
      sno: ruleForm.sno
    }
    if (valid) {
      submitRiderApply(ruleForm1).then((res) => {
        if (res.code === 2000) {
          ElMessage({
            type: 'success',
            message: '申请成功'
          })
          nextstep(step)
          getmyapply()
        }
      })
    } else {
      console.log('error submit!', fields)
    }
  })
}
const backhome = () => {
  router.push('/home')
}
const options = Array.from({ length: 10000 }).map((_, idx) => ({
  value: `${idx + 1}`,
  label: `${idx + 1}`
}))
const nextstep = (activeno: number) => {
  active.value = activeno
}
//上传成功将图片存入表单
const handleSuccess: UploadProps['onSuccess'] = (response, uploadFile) => {
  ruleForm.identification = uploadFile
  ruleForm.identification.name = response.data
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

const myapply = reactive({
  applyInfo: { applyTime: '', status: 0, auditResult: null },
  status: '',
  result: ''
})
const applytotal = ref(0)
const getmyapply = () => {
  getMyApply().then((res) => {
    myapply.applyInfo = res.data
    if (res.data != null) {
      applytotal.value = 1
      if (myapply.applyInfo.status == 1) {
        myapply.status = 'info'
      } else {
        myapply.status = 'success'
      }
      if (myapply.applyInfo.auditResult == 0) {
        myapply.result = 'danger'
      } else if (myapply.applyInfo.auditResult == 1) {
        myapply.result = 'success'
      }
      myapply.applyInfo.applyTime = formatter(res.data.createTime) + '提交申请'
    }
  })
}
const formatter = (val) => {
  const applyTime = val
  const time = applyTime.replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
  return time
}
onMounted(() => {
  getmyapply()
})
</script>
<style>
.form-body {
  margin-top: 20px;
}
.text {
  margin-left: 10px;
  font-size: 15px;
  font-weight: 600;
  line-height: 200%;
}
.font {
  font-weight: 600;
}
.el-form-item__error {
  font-size: 13px;
  color: rgb(197, 51, 51);
  font-weight: 900;
}
.el-icon-wodeshenqing-moren {
  vertical-align: middle;
  font-size: 25px;
}
</style>
