<!--
  * header
-->
<template>
  <a-space :size="10">
<!--    <div class="download">-->
<!--      &lt;!&ndash;-download&ndash;&gt;-->
<!--      <a-button type="text" @click="download" >-->
<!--        Download-->
<!--      </a-button>-->
<!--    </div>-->

    <div class="setting">
      <!---setting-->
      <a-button type="text" @click="showSetting" class="operate-icon">
        <template #icon><setting-outlined /></template>
      </a-button>
    </div>
    <!---avatar info--->
    <div class="user-space-item">
      <HeaderAvatar />
    </div>
    <HeaderSetting ref="headerSetting" />
  </a-space>
</template>

<script setup>
  import HeaderAvatar from './header-avatar.vue';
  import HeaderSetting from './header-setting.vue';

  import { ref } from 'vue';

  // setting
  const headerSetting = ref();
  function showSetting() {
    headerSetting.value.show();
  }
  async function download() {
    try {
      await fileApi.downloadFile();
    } catch (e) {
      console.log(e);
    }
  }

  //message
  const headerMessage = ref();
  function showMessage() {
    headerMessage.value.showMessage();
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
