<script setup>
import TableViewVue from "./TableView.vue";
import EditVieVue from "./EditVie.vue";
import { provide, ref } from "vue";
const isEditView = ref(false);
//向子组件传递参数
provide("isEditView", isEditView);
</script>
<template>
  <TableViewVue v-if="!isEditView" />
  <EditVieVue v-if="isEditView" />
</template>
