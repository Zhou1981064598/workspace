<script setup>
import TableComponent from "@/components/tables/TableComponent.vue";
import { column } from "./column";
const module = import.meta.globEager("@/apis/goods/spu.js");
</script>
<template>
  <TableComponent :module="module" :column="column" recovery="true" />
</template>
