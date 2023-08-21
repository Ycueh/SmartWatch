<!--
  * avatar
-->

<template>
  <a-dropdown class="header-trigger">
    <div class="wrapper">
      <a-avatar style="margin: 0 5px" :size="24" id="smartAdminAvatar">
        {{ avatarName }}
<!--        U-->
      </a-avatar>

     <span class="name">{{ actualName }}</span>
    </div>
    <template #overlay>
      <a-menu :class="['avatar-menu']">
        <a-menu-item @click="onRefresh">
          <span>Refresh permissions</span>
        </a-menu-item>
        <a-menu-item @click="showUpdatePwdModal">
          <span>Update password</span>
        </a-menu-item>
        <a-menu-item @click="onLogout">
          <span>Log out</span>
        </a-menu-item>
      </a-menu>
    </template>
  </a-dropdown>
  <HeaderResetPassword ref="resetPasswordRef" />
</template>
<script setup>
  import { computed, ref, onMounted } from 'vue';
  import { loginApi } from '/@/api/system/login/login-api';

  import { useUserStore } from '/@/store/modules/system/user';
  import { clearAllCoolies } from '/@/utils/cookie-util';
  import { localClear } from '/@/utils/local-util';
  import { smartSentry } from '/@/lib/smart-sentry';
  import HeaderResetPassword from './header-reset-password-modal/index.vue';

  // Avatar background color
  const AVATAR_BACKGROUND_COLOR_ARRAY = ['#87d068', '#00B853', '#f56a00', '#1890ff'];

  //Monitor logout method
  async function onLogout() {
    try {
      await loginApi.logout();
    } catch (e) {
      smartSentry.captureError(e);
    } finally {

      localClear();
      clearAllCoolies();
      useUserStore().logout();
      location.reload();
    }
  }

  

  // Refresh user information (including basic user information, permission information, etc.)
  async function onRefresh() {
    await loginApi.refresh();
    location.reload();
  }

  // ------------------------ reset password ------------------------
  const resetPasswordRef = ref();

  function showUpdatePwdModal() {
    resetPasswordRef.value.showModal();
  }

  // ------------------------ avatar, name ------------------------

  const avatarName = ref('');
  const actualName = computed(() => useUserStore().loginName);
  // update avatar
  function updateAvatar() {
    if (useUserStore().actualName) {
      avatarName.value = useUserStore().actualName.substr(0, 1);
      const avatar = document.getElementById('smartAdminAvatar');
      if (avatar) {
        avatar.style.backgroundColor = AVATAR_BACKGROUND_COLOR_ARRAY[hashcode(avatarName.value) % 4];
      }
    }
  }

  /**
   * choose color
   */
  function hashcode(str) {
    let hash = 1,
      i,
      chr;
    if (str.length === 0) return hash;
    for (i = 0; i < str.length; i++) {
      chr = str.charCodeAt(i);
      hash = (hash << 5) - hash + chr;
      hash |= 0; // Convert to 32bit integer
    }
    return hash;
  }

  onMounted(updateAvatar);
</script>
<style lang="less" scoped>
  .wrapper {
    cursor: pointer;
    display: flex;
    align-items: center;
  }
  .header-trigger {
    height: @header-user-height;
    line-height: @header-user-height;

    .avatar {
      vertical-align: middle;
    }

    .name {
      margin-left: 5px;
      font-weight: 500;
    }
  }
</style>
