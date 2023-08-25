<template>
  <div class="topbar">
    <!-- container -->
    <div class="container clearfix">
      <!-- welcome area -->
      <div class="welcome leftfix">
        <span>Populating a database to be loaded to smartwatches for behavioral testing</span>
      </div>
      <!-- login area -->
<!--      <div class="top-nav rightfix">-->
<!--        <a class="login" href="">Log in</a>-->
<!--        &lt;!&ndash; <a href="">Sign up</a> &ndash;&gt;-->
<!--      </div>-->
    </div>
  </div>

  <div class="header">
    <div class="container clearfix">
      <!-- university logo -->
      <div class="logo leftfix">
        <div class="logoset">
          <img src="./assets/images/UoB-logo.png" alt="UoB-logo" />
        </div>
      </div>
<!--      &lt;!&ndash; search area &ndash;&gt;-->
<!--      <div class="search rightfix">-->
<!--        <form action="#">-->
<!--          <input type="text" />-->
<!--          <button></button>-->
<!--        </form>-->
<!--      </div>-->
    </div>
  </div>

  <a-config-provider :locale="antdLocale">
    <!--- Global Loading --->
    <a-spin :spinning="spinning"  tip="Please wait a moment, I'm loading..." size="large">
      <!--- Routes --->
      <RouterView />
    </a-spin>
  </a-config-provider>
</template>


<script setup>
  import dayjs from 'dayjs';
  import { computed } from 'vue';
  import { messages } from '/@/i18n/index';
  import { useAppConfigStore } from '/@/store/modules/system/app-config';
  import { useSpinStore } from './store/modules/system/spin';

  const antdLocale = computed(() => messages[useAppConfigStore().language].antdLocale);
  const dayjsLocale = computed(() => messages[useAppConfigStore().language].dayjsLocale);
  dayjs.locale(dayjsLocale);

  let spinStore = useSpinStore();
  const spinning = computed(() => spinStore.loading);
</script>

<style>
  @import "../public/style/style.css";
</style>
