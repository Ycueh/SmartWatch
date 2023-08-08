<!--
  * Role setting
  *
-->
<template>
  <a-card class="role-container">
    <a-tabs v-model:activeKey="activeKey">
      <a-tab-pane key="1" tab="Role-Authorities">
        <RoleTree />
      </a-tab-pane>
      <a-tab-pane key="2" tab="Role-User list">
        <RoleUserList />
      </a-tab-pane>
    </a-tabs>
  </a-card>
</template>
<script setup>
  import { ref } from 'vue';
  import RoleUserList from '../role-user-list/index.vue';
  import RoleTree from '../role-tree/index.vue';

  defineProps({
    value: Number,
  });

  defineEmits(['update:value']);

  let activeKey = ref();
</script>
<style scoped lang="less">
  .role-container {
    height: 100%;
  }
</style>
