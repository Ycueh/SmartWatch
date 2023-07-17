<!--
  * expand menu
-->
<template>
  <div class="menu-container">
    <!-- logo, First column: top menu -->
    <TopMenu ref="topMenu" class="topMenu" :menuTree="menuTree" />
    <!-- second column: menu -->
    <RecursionMenu v-if="showRecursionMenu" class="recursion-menu" :selectedMenu="selectedMenu" />
  </div>
</template>
<script setup>
  import { computed, ref } from 'vue';
  import RecursionMenu from './recursion-menu.vue';
  import TopMenu from './top-menu.vue';
  import { useUserStore } from '/@/store/modules/system/user';

  defineEmits(['update:value']);
  const menuTree = computed(() => useUserStore().getMenuTree || []);

  const topMenu = ref();
  const selectedMenu = computed(() => {
    if (topMenu.value) {
      return topMenu.value.selectedMenu;
    }
    return {};
  });
  const showRecursionMenu = computed(() => {
    return selectedMenu.value && selectedMenu.value.children && selectedMenu.value.children.some((e) => e.visibleFlag);
  });
</script>
<style scoped lang="less">
  .menu-container {
    display: flex;
    height: 100%;
    .topMenu {
      width: 114px;
      flex-shrink: 0;
    }
    .recursion-menu {
      min-width: 126px;
      flex: 1;
    }
  }
</style>
