<!--
  * bread crumb
-->
<template>
  <a-breadcrumb separator=">" style="display: inline" v-if="breadCrumbFlag">
    <a-breadcrumb-item v-for="(item, index) in parentMenuList" :key="index">{{ item.title }}</a-breadcrumb-item>
    <a-breadcrumb-item>{{ currentRoute.meta.title }}</a-breadcrumb-item>
  </a-breadcrumb>
</template>
<script setup>
  import { useRoute } from 'vue-router';
  import { useUserStore } from '/@/store/modules/system/user';
  import { computed } from 'vue';
  import { useAppConfigStore } from '/@/store/modules/system/app-config';

  // whether to show breadcrumbs
  const breadCrumbFlag = computed(() =>  useAppConfigStore().$state.breadCrumbFlag);

  let currentRoute = useRoute();
  //monitor breadcrumbs based on routes
  const parentMenuList = computed(() => {
    let currentName = currentRoute.name;
    if (!currentName || typeof currentName !== 'string') {
      return [];
    }
    let menuParentIdListMap = useUserStore().getMenuParentIdListMap;
    return menuParentIdListMap.get(currentName) || [];
  });
</script>
