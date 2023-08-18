<!--
  *  User change password dialog
-->
<template>
  <a-modal v-model:visible="visible" :zIndex="9999" :width="500" title="Hint" :closable="false" :maskClosable="false">
    <!--  -->
    <ul>
      <li>Account: {{ showLoginName }}</li>
      <li>Password: {{ showLoginPassword }}</li>
    </ul>
    <template #footer>
      <a-button
        type="primary"
        class="account-copy"
        :data-clipboard-text="`Account：${showLoginName} 
Password：${showLoginPassword}`"
        size="middle"
        @click="copy"
        >Copy the password and close</a-button
      >
    </template>
  </a-modal>
</template>
<script setup>
  import { message } from 'ant-design-vue';
  import Clipboard from 'clipboard';
  import { ref } from 'vue';

  let visible = ref(false); 
  let showLoginName = ref(''); 
  let showLoginPassword = ref(''); 

  function copy() {
    handleCopy();
    visible.value = false;
  }
  function showModal(loginName, loginPassword) {
    visible.value = true;
    showLoginName.value = loginName;
    showLoginPassword.value = loginPassword;
  }
  function handleCopy() {
    let clipboard = new Clipboard('.account-copy');
    clipboard.on('success', (e) => {
      message.info('Copy successfully');
      console.log('Copy successfully');
      // Release memory
      clipboard.destroy();
    });
    clipboard.on('error', (e) => {
      // Can not copy
      message.error('Browser does not support copying, please manually select copying');
      // 释放内存
      clipboard.destroy();
    });
  }
  defineExpose({
    showModal,
  });
</script>
<style lang="less" scoped>
  ul {
    margin: 0;
    padding: 0;
    list-style: none;
    padding-left: 32%;
    li {
      font-weight: bold;
      font-size: 16px;
    }
  }
</style>
>
