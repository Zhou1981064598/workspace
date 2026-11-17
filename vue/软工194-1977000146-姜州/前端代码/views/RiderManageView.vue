<template>
  <el-card class="guest-container">
    <template #header>
      <div class="header">
        <el-button type="primary" :icon="Plus" @click="activateUser(selectId)">解除禁用</el-button>
        <el-button type="danger" :icon="Delete" @click="banUser(selectId)">禁用账户</el-button>
        <el-input
          :prefix-icon="Search"
          v-model="search"
          size="large"
          placeholder="请输入查找对象"
          style="width: 300px; margin-left: 80%"
        />
      </div>
    </template>
    <el-table
      ref="multipleTableRef"
      :data="filterTableData"
      style="width: 100%"
      @selection-change="handleSelectionChange"
      :row-key="getRowKeys"
      border
    >
      <template v-slot:empty> <el-empty description="暂时还没有骑手哦！" /> </template>
      <el-table-column type="selection" width="55" :reserve-selection="true" />
      <el-table-column prop="realName" label="骑手姓名"> </el-table-column>
      <el-table-column prop="phone" label="联系方式"> </el-table-column>
      <el-table-column prop="email" label="邮箱"> </el-table-column>
      <el-table-column prop="profession" label="职业"> </el-table-column>
      <el-table-column prop="preferences" label="取件偏好"> </el-table-column>
      <el-table-column prop="score" label="骑手评分"> </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-popover placement="top" :width="250" trigger="click" ref="popoverRef">
            <template #reference>
              <el-button size="default" type="primary">派单</el-button></template
            >
            <el-row :gutter="20">
              <el-col :span="12"
                ><a @click="showpick(scope.row.id)" style="cursor: pointer">快递代取</a></el-col
              >
              <el-col :span="12"
                ><a @click="showdaiji(scope.row.id)" style="cursor: pointer">快递代寄</a></el-col
              >
            </el-row>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
  <div class="demo-pagination-block">
    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :small="small"
      :disabled="disabled"
      :background="background"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalnum"
      @current-change="handleCurrentChange"
    />
  </div>
  <el-dialog v-model="pick" style="width: 80%">
    <el-table :data="filterTableData2" style="width: 100%">
      <template v-slot:empty> <el-empty description="暂时没有可接取的任务哦！" /> </template>
      <el-table-column label="收件人" prop="name" />
      <el-table-column label="联系电话" prop="phone" />
      <el-table-column label="快递大小" prop="size" />
      <el-table-column label="取件信息" prop="message" />
      <el-table-column label="取件地址" prop="pickupAddress" />
      <el-table-column label="收件地址" prop="pickupAddress" />
      <el-table-column align="right">
        <template #header>
          <el-input :prefix-icon="Search" v-model="search1" size="default" placeholder="搜索任务" />
        </template>
        <template #default="scope">
          <el-button size="small" @click="selectPackageInf(scope.row)">详细信息</el-button>
          <el-button
            size="small"
            type="primary"
            @click="showpaidanDialog(scope.row.id, scope.row.orderId, scope.row.type)"
            >派单</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="demo-pagination-block">
      <el-pagination
        v-model:current-page="currentPage1"
        v-model:page-size="pageSize1"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalnum1"
        @current-change="handleCurrentChange1"
      />
    </div>
  </el-dialog>
  <el-dialog v-model="send" style="width: 80%">
    <el-table :data="filterTableData3" style="width: 100%; margin-top: 1%" align="middle">
      <template v-slot:empty> <el-empty description="暂时没有可接取的任务哦！" /> </template>
      <el-table-column label="寄件人姓名" prop="name" />
      <el-table-column label="联系电话" prop="phone" />
      <el-table-column label="取货地址" prop="pickupAddress" />
      <el-table-column label="快递重量(单位kg)" prop="weight" />
      <el-table-column label="寄件类型" prop="sendType" />
      <el-table-column label="寄送商家" prop="sendAddress" />

      <el-table-column align="right">
        <template #header>
          <el-input :prefix-icon="Search" v-model="search2" size="default" placeholder="搜索任务" />
        </template>
        <template #default="scope">
          <el-button size="small" @click="selectPackageInf1(scope.row)">详细信息</el-button>

          <el-button
            size="small"
            type="primary"
            @click="showpaidanDialog1(scope.row.id, scope.row.orderId, scope.row.type)"
            >派单</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="demo-pagination-block">
      <el-pagination
        v-model:current-page="currentPage2"
        v-model:page-size="pageSize2"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalnum2"
        @current-change="handleCurrentChange2"
      /></div
  ></el-dialog>
  <el-dialog v-model="pickdetil" class="missionDataBody">
    <el-descriptions class="margin-top" title="详细信息" :column="2" :size="size" border>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <user />
            </el-icon>
            收件人
          </div>
        </template>
        {{ state.row[0].name }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <iphone /> </el-icon
            >联系电话
          </div>
        </template>
        {{ state.row[0].phone }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <location /> </el-icon
            >取件地址
          </div>
        </template>
        {{ state.row[0].pickupAddress }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <location /> </el-icon
            >收件地址
          </div>
        </template>
        {{ state.row[0].acceptAddress }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><Notification
            /></el-icon>
            任务类型
          </div>
        </template>
        快递代取
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <tickets /> </el-icon
            >快递单号
          </div>
        </template>
        {{ state.row[0].deliveryNum }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><MessageBox
            /></el-icon>
            快递大小
          </div>
        </template>
        {{ state.row[0].size }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <tickets /> </el-icon
            >取件信息
          </div>
        </template>
        <div class="message-data">{{ state.row[0].message }}</div>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><Picture
            /></el-icon>
            身份码
          </div>
        </template>
        <el-tag type="warning" class="mx-1" effect="dark" size="large"
          ><b style="font-size: 15px">隐秘，接单后可查看</b>
        </el-tag>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"><Coin /></el-icon>
            可得佣金
          </div>
        </template>
        {{ reward }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item" style="height: 100px">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><Notebook
            /></el-icon>
            备注
          </div>
        </template>
        {{ state.row[0].remarks }}
      </el-descriptions-item>
    </el-descriptions>
  </el-dialog>
  <el-dialog v-model="senddetil" class="missionDataBody">
    <el-descriptions class="margin-top" title="详细信息" :column="2" :size="size" border>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <user />
            </el-icon>
            寄件人
          </div>
        </template>
        {{ state.row1[0].name }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <iphone /> </el-icon
            >联系电话
          </div>
        </template>
        {{ state.row1[0].phone }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><location
            /></el-icon>
            寄件商家
          </div>
        </template>
        {{ state.row1[0].sendAddress }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <location /> </el-icon
            >取件地址
          </div>
        </template>
        {{ state.row1[0].pickupAddress }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><Notification
            /></el-icon>
            寄件类型
          </div>
        </template>
        {{ state.row1[0].sendType }}
      </el-descriptions-item>

      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><MessageBox
            /></el-icon>
            快递重量
          </div>
        </template>
        {{ state.row1[0].weight }}kg
      </el-descriptions-item>

      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"><Coin /></el-icon>
            可得佣金
          </div>
        </template>
        {{ reward1 }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item" style="height: 100px">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><Notebook
            /></el-icon>
            备注
          </div>
        </template>
        {{ state.row1[0].remarks }}
      </el-descriptions-item>
    </el-descriptions>
  </el-dialog>
  <el-dialog v-model="acceptorder" width="30%">
    <span>确定分配该订单吗？</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="acceptorder = false">取消</el-button>
        <el-button type="primary" @click="addmission()"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
  <el-dialog v-model="acceptorder1" width="30%">
    <span>确定分配该订单吗？</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="acceptorder1 = false">取消</el-button>
        <el-button type="primary" @click="addmission1()"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { ref, onMounted, reactive, computed } from 'vue'
import { getPackagePickInf, getPackageSendInf } from '@/api/package.js'
import { ElMessage } from 'element-plus'
import { getAllRider } from '@/api/user.js'
import { Search } from '@element-plus/icons-vue'
import { Plus, Delete } from '@element-plus/icons-vue'
import { arrangeMission, banUsers, activateUsers } from '@/api/admin.js'
// 分页参数
const currentPage = ref(1)
const currentPage1 = ref(1)
const currentPage2 = ref(1)
const pageSize = ref(5)
const pageSize1 = ref(5)
const pageSize2 = ref(5)
const totalnum = ref(0)
const totalnum1 = ref(0)
const totalnum2 = ref(0)

const background = ref(false)

// 按钮参数
const small = ref(false)

// 对话框参数
const size = ref('')
const disabled = ref(false)
const pick = ref(false)
const send = ref(false)
const pickdetil = ref(false)
const senddetil = ref(false)
const acceptorder = ref(false)
const acceptorder1 = ref(false)
const search = ref('')
const search1 = ref('')
const search2 = ref('')

const reward = ref()
const reward1 = ref()

const state = reactive({
  courierId: '',
  courierId1: '',
  packagepickForm: [
    {
      name: '',
      phone: '',
      pickupAddress: '',
      acceptAddress: '',
      deliveryNum: '',
      remarks: '',
      size: '',
      message: ''
    }
  ],
  packagesendForm: [
    {
      name: '',
      phone: '',
      pickupAddress: '',
      weight: '',
      sendType: '',
      sendAddress: ''
    }
  ],
  form: [
    {
      realName: '',
      sno: '',
      profession: '',
      preferences: '',
      score: ''
    }
  ],
  imgbaseurl: '',
  row: [
    {
      name: '',
      phone: '',
      pickupAddress: '',
      acceptAddress: '',
      deliveryNum: '',
      remarks: '',
      size: '',
      message: ''
    }
  ],
  row1: [
    {
      name: '',
      phone: '',
      pickupAddress: '',
      acceptAddress: '',
      deliveryNum: '',
      remarks: '',
      size: '',
      weight: '',
      message: '',
      sendAddress: '',
      sendType: ''
    }
  ],
  infId: '',
  type: '',
  orderId: '',
  infId1: '',
  type1: '',
  orderId1: ''
})
onMounted(() => {
  getallRider()
})
const getallRider = () => {
  getAllRider(currentPage.value, pageSize.value).then((res) => {
    totalnum.value = res.data.total
    state.form = res.data.list
  })
}
const getpackagepickinf = () => {
  getPackagePickInf(currentPage1.value, pageSize1.value).then((res) => {
    totalnum1.value = res.data.total
    state.packagepickForm = res.data.list
  })
}
const getpackagesendinf = () => {
  getPackageSendInf(currentPage2.value, pageSize2.value).then((res) => {
    totalnum2.value = res.data.total
    state.packagesendForm = res.data.list
  })
}
const selectPackageInf = (row) => {
  pickdetil.value = true
  state.row[0] = row
  const size = state.row[0].size
  if (size == '小件') {
    reward.value = '3元'
  } else if (size == '大件') {
    reward.value = '6元'
  } else {
    reward.value = '15元'
  }
}
const selectPackageInf1 = (row) => {
  senddetil.value = true
  state.row1[0] = row
  const weight = parseInt(state.row1[0].weight)
  if (weight < 5) {
    reward1.value = '5元'
  } else if (weight >= 5 && weight < 25) {
    reward1.value = '10元'
  } else {
    reward1.value = '20元'
  }
}
const filterTableData = computed(() =>
  state.form.filter(
    (data) =>
      !search.value ||
      data.realName.toLowerCase().includes(search.value.toLowerCase()) ||
      data.profession.toLowerCase().includes(search.value.toLowerCase()) ||
      data.preferences.toLowerCase().includes(search.value.toLowerCase())
  )
)
const filterTableData2 = computed(() =>
  state.packagepickForm.filter(
    (data) =>
      !search1.value ||
      data.name.toLowerCase().includes(search1.value.toLowerCase()) ||
      data.phone.toLowerCase().includes(search1.value.toLowerCase()) ||
      data.pickupAddress.toLowerCase().includes(search1.value.toLowerCase()) ||
      data.acceptAddress.toLowerCase().includes(search1.value.toLowerCase()) ||
      data.deliveryNum.toLowerCase().includes(search1.value.toLowerCase()) ||
      data.size.toLowerCase().includes(search1.value.toLowerCase()) ||
      data.message.toLowerCase().includes(search1.value.toLowerCase())
  )
)
const filterTableData3 = computed(() =>
  state.packagesendForm.filter(
    (data) =>
      !search2.value ||
      data.name.toLowerCase().includes(search2.value.toLowerCase()) ||
      data.phone.toLowerCase().includes(search2.value.toLowerCase()) ||
      data.pickupAddress.toLowerCase().includes(search2.value.toLowerCase()) ||
      data.weight.toLowerCase().includes(search2.value.toLowerCase()) ||
      data.sendType.toLowerCase().includes(search2.value.toLowerCase()) ||
      data.sendAddress.toLowerCase().includes(search2.value.toLowerCase())
  )
)
const handleCurrentChange = () => {
  getallRider()
}
const handleCurrentChange1 = () => {
  getpackagepickinf()
}
const handleCurrentChange2 = () => {
  getPackageSendInf()
}
const showpick = (userId) => {
  pick.value = true
  state.courierId = userId
  getpackagepickinf()
}
const showdaiji = (userId) => {
  send.value = true
  state.courierId1 = userId
  getpackagesendinf()
}
const showpaidanDialog = (Id, orderid, type) => {
  acceptorder.value = true
  state.infId = Id
  state.type = type
  state.orderId = orderid
}

const showpaidanDialog1 = (Id, orderid, type) => {
  acceptorder1.value = true
  state.infId1 = Id
  state.type1 = type
  state.orderId1 = orderid
}
const addmission = () => {
  arrangeMission(state.infId, state.orderId, state.type, state.courierId).then((res) => {
    getPackagePickInf(currentPage1.value, pageSize1.value).then((res) => {
      totalnum1.value = res.data.total
      state.packagepickForm = res.data.list
    })
    if (res.code == 2000) {
      ElMessage({
        type: 'success',
        message: '派单成功'
      })
    }
  })
  acceptorder.value = false
}
const addmission1 = () => {
  arrangeMission(state.infId1, state.orderId1, state.type1, state.courierId1).then((res) => {
    getPackageSendInf(currentPage2.value, pageSize2.value).then((res) => {
      totalnum2.value = res.data.total
      state.packagesendForm = res.data.list
    })
    if (res.code == 2000) {
      ElMessage({
        type: 'success',
        message: '派单成功'
      })
    }
  })
  acceptorder1.value = false
}

const multipleTableRef = ref()
const selectNumber = ref(0) //表格select选中的条数
const selectId = ref([]) //表格select复选框选中的id
const multipleSelection = ref([])

//选中的list
const getRowKeys = (row) => {
  //记录每行的key值
  return row.id
}
//当表格选择项发生变化时会触发该事件
const handleSelectionChange = (val) => {
  // 解决来回切换页面，也无法清除上次选中情况
  multipleSelection.value = val
  selectNumber.value = multipleSelection.value.length
  selectId.value = []
  if (val) {
    undefined
    val.forEach((element) => {
      undefined
      if (element) {
        undefined
        selectId.value.push(element.id)
      }
    })
  }
}
const banUser = (selectId) => {
  banUsers(selectId).then((res) => {
    if (res.code == 2000) {
      ElMessage({
        type: 'success',
        message: '禁用成功'
      })
    }
  })
}
const activateUser = (selectId) => {
  activateUsers(selectId).then((res) => {
    if (res.code == 2000) {
      ElMessage({
        type: 'success',
        message: '操作成功'
      })
    }
  })
}
</script>

<style>
.el-table-column {
  font-size: 20px;
}
.cell-item {
  width: 100px;
}
.cell-data {
  width: 300px;
}
.message-data {
  height: 100px;
}
.missionDataBody {
  width: 1200px;
}
</style>
