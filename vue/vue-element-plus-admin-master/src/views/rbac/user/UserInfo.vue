<script setup>
import { onMounted, reactive } from "vue";
import { info, changePassword } from "@/apis/rbac/user";
import { ElMessage } from "element-plus";
import { useUserStore } from "@/stores/user";
import { Plus } from "@element-plus/icons-vue";

// 获取 store 用于存取数据
const userStore = useUserStore();

/**
 * 个人信息数据
 */
const descriptions = reactive({
  title: "个人信息",
  column: 1,
  item: {
    username: "账号",
    nickname: "昵称",
    gender: "性别",
    phone: "电话",
    email: "右键",
    isDeleted: "删除状态",
    status: "状态",
    pwdResetTime: "最后修改密码时间",
    createTime: "创建时间",
    updateTime: "修改时间",
    create_by: "创建者",
    update_time: "修改者",
  },
  user: {},
});

/**
 * 修改密码对话框数据
 */
const changePasswordDialog = reactive({
  dialogVisible: false,
  labelWidth: "100px",
  formData: {
    oldPassword: "",
    newPassword: "",
    fixPassword: "",
  },
});

/**
 * 修改密码提交
 */
const changePasswordSubmit = () => {
  changePassword(changePasswordDialog.formData).then((response) => {
    const { message } = response;
    changePasswordDialog.dialogVisible = false;
    ElMessage({
      type: "success",
      showClose: true,
      message: message,
      duration: 3000,
    });
  });
};

/**
 * 修改头像请求参数
 */
const requestParam = reactive({
  baseUrl: import.meta.env.VITE_API_BASEPATH,
  headers: {
    authorization: userStore.getToken(),
  },
});

/**
 * 上传成功后处理器
 * @param {*} response /
 * @param {*} uploadFile /
 */
const handleAvatarSuccess = (response) => {
  descriptions.user.avatarPath = response.data;
};

onMounted(() => {
  info().then((response) => {
    const { data } = response;
    descriptions.user = data;
  });
});
</script>

<template>
  <el-row v-if="descriptions.user.username" class="pannel" :gutter="20">
    <el-col :span="8">
      <el-divider> 头像 </el-divider>
      <el-upload
        class="avatar"
        :action="requestParam.baseUrl + '/rbac/user/avatar'"
        :headers="requestParam.headers"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
      >
        <img
          v-if="descriptions.user.avatarPath"
          :src="requestParam.baseUrl + descriptions.user.avatarPath"
        />
        <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
      </el-upload>
    </el-col>
    <el-col :span="16">
      <el-descriptions
        :border="true"
        :title="descriptions.title"
        :column="descriptions.column"
      >
        <template #extra>
          <el-button
            type="primary"
            @click="changePasswordDialog.dialogVisible = true"
          >
            修改密码
          </el-button>
        </template>

        <el-descriptions-item
          v-for="(key, value) in descriptions.item"
          :key="key"
        >
          <template #label>
            <div class="cell-item">
              {{ key }}
            </div>
          </template>
          {{ descriptions.user[value] }}
        </el-descriptions-item>
      </el-descriptions>
    </el-col>
  </el-row>

  <el-dialog
    v-model="changePasswordDialog.dialogVisible"
    title="修改密码"
    width="400"
    align-center
    draggable
  >
    <el-form
      :model="changePasswordDialog.formData"
      :label-width="changePasswordDialog.labelWidth"
      label-position="left"
    >
      <el-form-item label="原密码">
        <el-input
          v-model="changePasswordDialog.formData.oldPassword"
          type="password"
          clearable
          show-password
        />
      </el-form-item>
      <el-form-item label="新密码">
        <el-input
          v-model="changePasswordDialog.formData.newPassword"
          type="password"
          clearable
          show-password
        />
      </el-form-item>
      <el-form-item label="确认密码">
        <el-input
          v-model="changePasswordDialog.formData.fixPassword"
          type="password"
          clearable
          show-password
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="changePasswordSubmit">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style scoped>
.pannel {
  padding: 20px;
}
.avatar {
}
.avatar img {
  width: 100%;
}
</style>
