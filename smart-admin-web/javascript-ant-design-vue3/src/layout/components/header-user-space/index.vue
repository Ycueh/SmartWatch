<!--
  * header
-->
<template>
  <a-space :size="10">
    <div class="setting">
<!--      <a-input-search-->
<!--        @click="search"-->
<!--        style="margin-right: 30px; width: 250px"-->
<!--        placeholder="1024创新实验室、小镇程序员"-->
<!--        enter-button="搜索"-->
<!--        size="small"-->
<!--      />-->
      <!---消息通知--->
<!--      <HeaderMessage ref="headerMessage" />-->
      <!---国际化--->
      <!-- <a-button type="text" @click="showSetting" class="operate-icon">
        <template #icon><switcher-outlined /></template>
        i18n
      </a-button> -->
      <!---setting-->
      <a-button type="text" @click="showSetting" class="operate-icon">
        <template #icon><setting-outlined /></template>
      </a-button>
    </div>
    <!---avatar info--->
    <div class="user-space-item">
      <HeaderAvatar />
    </div>
    <!---帮助文档--->
<!--    <div class="user-space-item" @click="showHelpDoc">-->
<!--      <question-circle-two-tone style="font-size: 18px; margin-right: 5px; margin-top: 5px" />-->
<!--      <span>帮助文档</span>-->
<!--    </div>-->

    <HeaderSetting ref="headerSetting" />
  </a-space>
</template>

<script setup>
  import HeaderAvatar from './header-avatar.vue';
  import HeaderSetting from './header-setting.vue';
  import HeaderMessage from './header-message.vue';
  import { useAppConfigStore } from '/@/store/modules/system/app-config';
  import { ref } from 'vue';

  // setting
  const headerSetting = ref();
  function showSetting() {
    headerSetting.value.show();
  }

  //message
  const headerMessage = ref();
  function showMessage() {
    headerMessage.value.showMessage();
  }

  //help doc
  function showHelpDoc() {
    useAppConfigStore().showHelpDoc();
  }

  //search
  function search(){
    window.open("https://1024lab.net");
  }
</script>

<style lang="less" scoped>
  .user-space-item {
    height: 100%;
    color: inherit;
    padding: 0 12px;
    cursor: pointer;
    align-self: center;

    a {
      color: inherit;

      i {
        font-size: 16px;
      }
    }
  }

  .user-space-item:hover {
    color: @primary-color;
    background: @hover-bg-color;
  }

  .setting {
    height: @header-user-height;
    line-height: @header-user-height;
    vertical-align: middle;
    display: flex;
    align-items: center;
  }
  .operate-icon {
    margin-left: 20px;
  }
</style>
