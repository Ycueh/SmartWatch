<!--
  * recursive menu
-->
<template>
  <div class="resursion-container">
    <!-- top menu name -->
    <div class="top-menu">
      <span class="ant-menu">{{ props.selectedMenu.menuName }}</span>
    </div>
    <!-- secondary menu display -->
    <a-menu :selectedKeys="selectedKeys" :openKeys="openKeys" mode="inline">
      <template v-for="item in props.selectedMenu.children" :key="item.menuId">
        <template v-if="item.visibleFlag">
          <template v-if="$lodash.isEmpty(item.children)">
            <a-menu-item :key="item.menuId.toString()" @click="turnToPage(item)">
              <template #icon v-if="item.icon">
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
  </div>
</template>
<script setup>
  import { computed } from 'vue';
  import { useRoute } from 'vue-router';
  import { router } from '/@/router';
  import SubMenu from './sub-menu.vue';
  import { HOME_PAGE_NAME } from '/@/constants/system/home-const';
  import { useUserStore } from '/@/store/modules/system/user';
  import _ from 'lodash';

  let props = defineProps({
    selectedMenu: Object,
  });

  defineEmits('update:value');

  //expanded menu
  let currentRoute = useRoute();
  const selectedKeys = computed(() => {
    return [currentRoute.name];
  });

  const parentMenuList = computed(() => {
    let currentName = currentRoute.name;
    if (!currentName || typeof currentName !== 'string') {
      return [];
    }
    let menuParentIdListMap = useUserStore().getMenuParentIdListMap;
    return menuParentIdListMap.get(currentName) || [];
  });

  const openKeys = computed(() => {
    // expand the current page
    // return parentMenuList.value.map((e) => e.name);
    // expand all pages
    let children = props.selectedMenu.children;
    if (!children || _.isEmpty(children)) {
      return [];
    }
    return children.map((e) => e.menuId.toString());
  });
  // page jump
  function turnToPage(route) {
    router.push({ name: route.menuId.toString() });
  }

  function goHome() {
    router.push({ name: HOME_PAGE_NAME });
  }
</script>
<style scoped lang="less">
  .resursion-container {
    height: 100%;
    background: #ffffff;
  }
  .top-menu {
    overflow: hidden;
    display: flex;
    align-items: center;
    justify-content: center;
    height: @header-user-height;
    font-size: 16px;
    color: #515a6e;
    border-bottom: 1px solid #f3f3f3;
    border-right: 1px solid #f3f3f3;
  }
</style>
