<!--
  * Role menu
  * 
  *
-->
<template>
  <li v-for="module in props.tree" :key="module.menuId">
    <div class="menu" :style="{ marginLeft: `${props.index * 4}%` }">
      <a-checkbox @change="selectCheckbox(module)" class="checked-box-label" :value="module.menuId">{{ module.menuName }} </a-checkbox>
      <div v-if="module.children && module.children.some((e) => e.menuType == MENU_TYPE_ENUM.POINTS.value)">
        <RoleTreePoint :tree="module.children" @selectCheckbox="selectCheckbox" />
      </div>
    </div>
    <template v-if="module.children && !module.children.some((e) => e.menuType == MENU_TYPE_ENUM.POINTS.value)">
      <RoleTreeMenu :tree="module.children" :index="props.index + 1" />
    </template>
  </li>
</template>
<script setup>
  import { MENU_TYPE_ENUM } from '/@/constants/system/menu-const';
  import { useRoleStore } from '/@/store/modules/system/role';
  import RoleTreePoint from './role-tree-point.vue';
  import RoleTreeMenu from '../role-tree/role-tree-menu.vue';

  const props = defineProps({
    tree: {
      type: Array,
      default: [],
    },
    index: {
      type: Number,
      default: 0,
    },
  });
  defineEmits('update:value');
  let roleStore = useRoleStore();
  function selectCheckbox(module) {
    if (!module.menuId) {
      return;
    }
    let checkedData = roleStore.checkedData;
    let findIndex = checkedData.indexOf(module.menuId);
    if (findIndex == -1) {
      roleStore.addCheckedDataAndChildren(module);
      roleStore.selectUpperLevel(module);
      if (module.contextMenuId) {
        roleStore.addCheckedData(module.contextMenuId);
      }
    } else {
      roleStore.deleteCheckedDataAndChildren(module);
    }
  }
</script>
