<!--
  * sub recursive menu
-->
<template>
  <a-sub-menu :key="menuInfo.menuId">
    <template #icon>
      <component :is="$antIcons[menuInfo.icon]" />
    </template>
    <template #title>{{ menuInfo.menuName }}</template>
    <template v-for="item in menuInfo.children" :key="item.menuId">
      <template v-if="item.visibleFlag && !item.disabledFlag">
        <template v-if="!item.children">
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
  </a-sub-menu>
</template>
<script setup>
  const props = defineProps({
    menuInfo: {
      type: Object,
      default: () => ({}),
    },
  });

  const emits = defineEmits(['turnToPage']);
  const turnToPage = (menu) => {
    emits('turnToPage', menu);
  };
</script>
