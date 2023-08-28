
<template>
  <a-tree-select
    :value="props.value"
    :treeData="treeData"
    :fieldNames="{ label: 'menuName', key: 'menuId', value: 'menuId' }"
    show-search
    tree-checkable
    style="width: 100%"
    :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
    placeholder="select menu"
    allow-clear
    tree-default-expand-all
    @change="onSelectChange"
  />
</template>
<script setup>
  import { onMounted, ref } from 'vue';
  import _ from 'lodash';
  import { menuApi } from '/@/api/system/menu/menu-api';
  import { buildMenuTableTree } from '/@/views/system/menu/menu-data-handler';
  import { MENU_TYPE_ENUM } from '/@/constants/system/menu-const';

  const props = defineProps({

    value: Array,
    multiple: {
      type: Boolean,
      default: false,
    },
  });

  const emit = defineEmits(['update:value']);

  let treeData = ref([]);
  onMounted(queryMenuTree);


  let menuList = [];
  async function queryMenuTree() {
    let res = await menuApi.queryMenu();
    menuList = res.data.filter((e) => e.menuType === MENU_TYPE_ENUM.MENU.value || e.menuType === MENU_TYPE_ENUM.CATALOG.value);
    for (const item of menuList) {
      if (item.menuType === MENU_TYPE_ENUM.CATALOG.value) {
        item.disabled = true;
      }
    }
    treeData.value = buildMenuTableTree(menuList);
  }

  /**
   * get menu list
   */
  function getMenuListByIdList(menuIdList) {
    return _.cloneDeep(menuList.filter((e) => menuIdList.indexOf(e.menuId) > -1));
  }

  function onSelectChange(e) {
    emit('update:value', e);
  }

  defineExpose({
    queryMenuTree,
    getMenuListByIdList,
  });
</script>
