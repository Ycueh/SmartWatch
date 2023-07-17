<!--
  * top menu
-->
<template>
  <div class="top-menu-container">
    <!-- top area -->
    <div class="logo" @click="onGoHome">
<!--      <img class="logo-img" :src="logoImg" />-->
      <div class="title" style="color: rgb(35, 105, 255)">EMA Testing</div>
    </div>
    <!-- top menu display -->
    <a-menu :selectedKeys="selectedKeys" mode="inline" :theme="theme">
      <template v-for="item in props.menuTree" :key="item.menuId">
        <template v-if="item.visibleFlag">
          <a-menu-item :key="item.menuId.toString()" @click="onSelectMenu(item)">
            <template #icon>
              <component :is="$antIcons[item.icon]" />
            </template>
            {{ menuNameAdapter(item.menuName) }}
          </a-menu-item>
        </template>
      </template>
    </a-menu>
  </div>
</template>
<script setup>
  import _ from 'lodash';
  import { computed, ref, watch } from 'vue';
  import { useRoute } from 'vue-router';
  import logoImg from '/@/assets/images/logo/smart-admin-logo.png';
  import { HOME_PAGE_NAME } from '/@/constants/system/home-const';
  import { MENU_TYPE_ENUM } from '/@/constants/system/menu-const';
  import { router } from '/@/router';
  import { useAppConfigStore } from '/@/store/modules/system/app-config';
  import { useUserStore } from '/@/store/modules/system/user';

  const theme = computed(() => useAppConfigStore().$state.sideMenuTheme);

  const props = defineProps({
    menuTree: Array,
  });
  const selectedMenu = ref();
  let currentRoute = useRoute();

  const parentMenuList = computed(() => {
    let currentName = currentRoute.name;
    if (!currentName || typeof currentName !== 'string') {
      return [];
    }
    let menuParentIdListMap = useUserStore().getMenuParentIdListMap;
    return menuParentIdListMap.get(currentName) || [];
  });

  const selectedKeys = computed(() => {
    if (selectedMenu.value) {
      return [selectedMenu.value.menuId.toString()];
    }
    return parentMenuList.value.map((e) => e.name);
  });

  // 展开菜单的顶级目录名字适配，只展示两个字为好
  function menuNameAdapter(name){
    return name.substr(0,2);
  }

  //monitor route changes
  watch(
    currentRoute,
    () => {
      selectedMenu.value = undefined;
      let menuList = props.menuTree.map((e) => e.menuId.toString());
      let parentIdList = _.intersection(menuList, selectedKeys.value);
      if (parentIdList.length > 0) {
        let parentId = parentIdList[0];
        let parentItem = props.menuTree.find((e) => e.menuId == Number(parentId));
        selectedMenu.value = parentItem;
      }
    },
    {
      immediate: true,
    }
  );
  // page jump
  function onSelectMenu(route) {
    selectedMenu.value = route;
    if (route.menuType == MENU_TYPE_ENUM.MENU.value && (_.isEmpty(route.children) || route.children.every((e) => !e.visibleFlag))) {
      router.push({ name: route.menuId.toString() });
    }
  }
  //go home page
  function onGoHome() {
    router.push({ name: HOME_PAGE_NAME });
  }
  defineExpose({
    selectedMenu,
  });
</script>
<style scoped lang="less">
  .top-menu-container {
    height: 100%;
  }
  .logo {
    height: @header-user-height;
    line-height: @header-user-height;
    padding: 0px 15px 0px 15px;
    width: 100%;
    z-index: 9999;
    display: flex;
    justify-content: space-between;
    cursor: pointer;

    .logo-img {
      width: 40px;
      height: 40px;
    }

    .title {
      font-size: 16px;
      font-weight: 600;
      overflow: hidden;
      color: v-bind('theme === "light" ? "#001529": "#ffffff"');
    }
  }
</style>
