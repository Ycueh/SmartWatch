<!--
  * header
-->
<template>
  <a-space :size="10">
    <div class="setting">
      <!---setting-->
      <a-button type="text" @click="downloadFile" >
        Download
      </a-button>
    </div>

    <div class="download">
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
  import { fileApi } from '/@/api/smartWatch/file/file-api';
  import { ref } from 'vue';

  // setting
  const headerSetting = ref();
  function showSetting() {
    headerSetting.value.show();
  }
  async function downloadFile() {
    try {
      let response = await fileApi.download(); // Make the Axios GET request to download the file

      // Handle the response data as a Blob
      const blob = new Blob([response], { type: 'application/octet-stream' });

      // Create a temporary URL for the Blob
      const url = URL.createObjectURL(blob);

      // Create a hidden link and trigger the download
      const link = document.createElement('a');
      link.href = url;
      link.download = 'exported_smart_admin_v2.db';
      link.style.display = 'none';
      document.body.appendChild(link);
      link.click();

      // Clean up the temporary URL
      URL.revokeObjectURL(url);
    } catch (e) {
      console.error('Error downloading file:', e);
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
