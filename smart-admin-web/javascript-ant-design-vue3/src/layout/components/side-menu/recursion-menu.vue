<!--
  * classic, recursive menu
-->
<template>
  <a-menu
    v-model:openKeys="openKeys"
    v-model:selectedKeys="selectedKeys"
    class="smart-menu"
    mode="inline"
    :theme="theme"
    :inlineCollapsed="inlineCollapsed"
  >
    <template v-for="item in menuTree" :key="item.menuId">
      <template v-if="item.visibleFlag && !item.disabledFlag">
        <template v-if="$lodash.isEmpty(item.children)">
          <a-menu-item :key="item.menuId" @click="turnToPage(item)">
            <template #icon>
              <component :is="$antIcons[item.icon]" />
            </template>
            {{ item.menuName }}
          </a-menu-item>
        </template>
        <template v-else>
          <SubMenu :menu-info="item" :key="item.menuId" @turnToPage="turnToPage" />
        </template>
      </template>
    </template>
  </a-menu>
</template>
<script setup>
  import _ from 'lodash';
  import { computed, ref, watch } from 'vue';
  import { useRoute } from 'vue-router';
  import SubMenu from './sub-menu.vue';
  import { router } from '/@/router/index';
  import { useAppConfigStore } from '/@/store/modules/system/app-config';
  import { useUserStore } from '/@/store/modules/system/user';

  const theme = computed(() => useAppConfigStore().$state.sideMenuTheme);

  const props = defineProps({
    inlineCollapsed: {
      type: Boolean,
      default: false,
    },
  });

  const menuTree = computed(() => useUserStore().getMenuTree || []);

  //expand menu
  let currentRoute = useRoute();
  const selectedKeys = ref([]);
  const openKeys = ref([]);

  // page jump
  function turnToPage(menu) {
    router.push({ path: menu.path });
  }

  /**
   * monitor router
   */
  function updateOpenKeysAndSelectKeys() {
    // update selection
    selectedKeys.value = [_.toNumber(currentRoute.name)];

    /**
     * update expand menu
     */
    //get expand menu key
    let menuParentIdListMap = useUserStore().getMenuParentIdListMap;
    let parentList = menuParentIdListMap.get(currentRoute.name) || [];
    let needOpenKeys = _.map(parentList, 'name').map(Number);
    // merge arrays
    openKeys.value = _.union(openKeys.value, needOpenKeys);
  }

  watch(
    currentRoute,
    () => {
      updateOpenKeysAndSelectKeys();
    },
    {
      immediate: true,
    }
  );

  defineExpose({
    updateOpenKeysAndSelectKeys,
  });
</script>

<style lang="less" scoped>
  .smart-menu {
    position: relative;
  }
</style>
