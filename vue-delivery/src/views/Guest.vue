<template>
  <el-card class="guest-container">
    <template #header>
      <div class="header">
        <el-input
          :prefix-icon="Search"
          v-model="search"
          size="large"
          placeholder="请输入查找对象"
          style="width: 300px; margin-left: 80%"
        />
      </div>
    </template>
    <!-- <el-button @click="resetDateFilter">reset date filter</el-button>
    <el-button @click="clearFilter">reset all filters</el-button> -->
    <el-table :data="filterTableData" style="width: 100%">
      <template v-slot:empty> <el-empty description="暂时还没有要审核的申请哦！" /> </template>
      <!-- <template #column> -->
      <!-- <el-table-column type="selection" width="55"> </el-table-column> -->
      <el-table-column prop="name" label="申请人"> </el-table-column>
      <el-table-column prop="sno" label="学号"
        ><template v-slot="scope">
          <el-empty
            :image-size="50"
            v-show="!scope.row.sno"
            style="margin-right: 83%"
            description=" "
          />
          {{ scope.row.sno }}
        </template>
      </el-table-column>

      <el-table-column prop="profession" label="职业"> </el-table-column>
      <el-table-column prop="createTime" label="申请时间" :formatter="formatter"> </el-table-column>
      <el-table-column prop="identification" label="身份证明">
        <template v-slot="scope">
          <el-image
            style="width: 100px; height: 100px"
            :src="'http://127.0.0.1:5173/src/assets/img/' + scope.row.identification"
            :zoom-rate="1.2"
            fit="contain"
            :preview-src-list="scope.row.srcList"
            :initial-index="0"
            :preview-teleported="true"
          />
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="default" type="primary" @click="showdialogVisible(scope.row.userId, 1)"
            >审核通过</el-button
          >
          <!-- @click="handleEdit(scope.$index, scope.row)@click="handleDelete(scope.$index, scope.row)" -->
          <el-button size="default" type="danger" @click="showdialogVisible(scope.row.userId, 0)"
            >审核不通过</el-button
          >
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
  <el-dialog v-model="dialogVisible" width="30%">
    <span>确定执行该操作吗</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button
          type="primary"
          @click="examineApply(state.examine.userId, state.examine.auditResult)"
        >
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { ref, onMounted, reactive, computed } from 'vue'
import { ElMessage } from 'element-plus'
import { getAllApply } from '@/api/apply.js'
import { examine } from '@/api/admin.js'
import { Search } from '@element-plus/icons-vue'

const currentPage = ref(1)
const pageSize = ref(5)
const totalnum = ref(0)
const small = ref(false)
const disabled = ref(false)
const background = ref(false)
const dialogVisible = ref(false)
const search = ref('')
const state = reactive({
  form: [
    {
      name: '',
      sno: '',
      profession: '',
      createTime: ''
    }
  ],
  imgbaseurl: '',
  examine: { userId: '', auditResult: '' }
})
onMounted(() => {
  getallApply()
})
const getallApply = () => {
  getAllApply(currentPage.value, pageSize.value).then((res) => {
    totalnum.value = res.data.total
    res.data.list.forEach((element) => {
      element.srcList = ['http://127.0.0.1:5173/src/assets/img/' + element.identification]
    })
    state.form = res.data.list
    // console.log(srcList)
  })
}
const filterTableData = computed(() =>
  state.form.filter(
    (data) =>
      !search.value ||
      data.name.toLowerCase().includes(search.value.toLowerCase()) ||
      data.sno.includes(search.value) ||
      data.profession.includes(search.value) ||
      data.createTime.includes(search.value)
  )
)
const handleCurrentChange = () => {
  getallApply()
}
const examineApply = (userId, auditResult) => {
  examine(userId, auditResult).then((res) => {
    if (res.code == 2000) {
      ElMessage({
        type: 'success',
        message: '操作成功'
      })
      dialogVisible.value = false

      getallApply()
    }
  })
}
const showdialogVisible = (userId, auditResult) => {
  dialogVisible.value = true
  state.examine.userId = userId
  state.examine.auditResult = auditResult
}
const formatter = (row) => {
  const applyTime = row.createTime
  const time = applyTime.replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
  return time
}
</script>

<style></style>
