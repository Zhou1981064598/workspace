<template>
  <div style="width: 100%">
    <el-table :data="filterTableData" style="width: 100%; margin-top: 1%" align="middle">
      <template v-slot:empty> <el-empty description="暂时没有可接取的任务哦！" /> </template>

      <el-table-column label="寄件人姓名" prop="name" />
      <el-table-column label="联系电话" prop="phone" />
      <el-table-column label="取货地址" prop="pickupAddress" />
      <el-table-column label="快递重量(单位kg)" prop="weight" />
      <el-table-column label="寄件类型" prop="sendType" />
      <el-table-column label="寄送商家" prop="sendAddress" />

      <el-table-column align="right">
        <template #header>
          <el-input :prefix-icon="Search" v-model="search" size="default" placeholder="搜索任务" />
        </template>
        <template #default="scope">
          <el-button size="small" @click="selectPackageInf(scope.row)">详细信息</el-button>

          <el-button
            size="small"
            type="primary"
            @click="showDialog(scope.row.id, scope.row.orderId, scope.row.type)"
            >接单</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>

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
  <el-dialog v-model="state.dialogTableVisible" class="missionDataBody">
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
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><location
            /></el-icon>
            寄件商家
          </div>
        </template>
        {{ state.row[0].sendAddress }}
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
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><Notification
            /></el-icon>
            寄件类型
          </div>
        </template>
        {{ state.row[0].sendType }}
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
        {{ state.row[0].weight }}kg
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
  <el-dialog v-model="dialogVisible" width="30%">
    <span>确定要接受该订单吗</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addmission()"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script lang="ts" setup>
import { getPackageSendInf } from '@/api/package.js'
import { acceptMission } from '@/api/mission.js'
import { ElMessage } from 'element-plus'
import { reactive, ref, computed } from 'vue'
import { Search } from '@element-plus/icons-vue'
const search = ref('')
const size = ref('')
const currentPage = ref(1)
const pageSize = ref(5)
const state = reactive({
  packageForm: [
    {
      name: '',
      phone: '',
      pickupAddress: '',
      weight: '',
      sendType: '',
      sendAddress: ''
    }
  ],
  row: [
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
  dialogTableVisible: false,
  infId: '',
  type: '',
  orderId: ''
})
const small = ref(false)
const disabled = ref(false)
const background = ref(false)
const totalnum = ref(0)
const reward = ref()
const dialogVisible = ref(false)
const filterTableData = computed(() =>
  state.packageForm.filter(
    (data) =>
      !search.value ||
      data.name.toLowerCase().includes(search.value.toLowerCase()) ||
      data.phone.toLowerCase().includes(search.value.toLowerCase()) ||
      data.pickupAddress.toLowerCase().includes(search.value.toLowerCase()) ||
      data.weight.toLowerCase().includes(search.value.toLowerCase()) ||
      data.sendType.toLowerCase().includes(search.value.toLowerCase()) ||
      data.sendAddress.toLowerCase().includes(search.value.toLowerCase())
  )
)

getPackageSendInf(currentPage.value, pageSize.value).then((res) => {
  totalnum.value = res.data.total
  state.packageForm = res.data.list
})
const handleCurrentChange = (val: number) => {
  getPackageSendInf(val, pageSize.value).then((res) => {
    state.packageForm = res.data.list
  })
}

const showDialog = (Id, orderid, type) => {
  dialogVisible.value = true
  state.infId = Id
  state.type = type
  state.orderId = orderid
}
const addmission = () => {
  acceptMission(state.infId, state.orderId, state.type).then((res) => {
    getPackageSendInf(currentPage.value, pageSize.value).then((res) => {
      totalnum.value = res.data.total
      state.packageForm = res.data.list
    })
    if (res.code == 2000) {
      ElMessage({
        type: 'success',
        message: '接单成功'
      })
    }
  })
  dialogVisible.value = false
}

const selectPackageInf = (row) => {
  state.dialogTableVisible = true
  state.row[0] = row
  const weight = parseInt(state.row[0].weight)
  if (weight < 5) {
    reward.value = '5元'
  } else if (weight >= 5 && weight < 25) {
    reward.value = '10元'
  } else {
    reward.value = '20元'
  }
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
