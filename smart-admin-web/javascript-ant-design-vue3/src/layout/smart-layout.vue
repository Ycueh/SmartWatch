<!--
  *  layout multiple modes
-->
<template>
  <!--side layout mode-->
  <SmartSideLayout v-if="layout === LAYOUT_ENUM.SIDE.value" />
  <!--expand side layout mode-->
  <SmartSideExpandLayout v-if="layout === LAYOUT_ENUM.SIDE_EXPAND.value" />
</template>
<script setup>
  import { computed } from 'vue';
  import SmartSideExpandLayout from './smart-side-expand-layout.vue';
  import { LAYOUT_ENUM } from '/@/constants/layout-const';
  import SmartSideLayout from '/@/layout/smart-side-layout.vue';
  import { useAppConfigStore } from '/@/store/modules/system/app-config';

  const layout = computed(() => useAppConfigStore().$state.layout);
</script>
