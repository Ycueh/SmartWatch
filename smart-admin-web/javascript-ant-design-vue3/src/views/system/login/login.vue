<!--
  * Login
  * 
  *
-->
<template>
  <div class="login-container">
    
    <div class="box-item login">
      <img class="login-qr" :src="loginQR" />
      <div class="login-title">Login</div>
      <a-form ref="formRef" class="login-form" :model="loginForm" :rules="rules">
        <a-form-item name="loginName">
          <a-input v-model:value.trim="loginForm.loginName" placeholder="Username" />
        </a-form-item>
        <a-form-item name="password">
          <a-input-password v-model:value="loginForm.password" autocomplete="on"
            :type="showPassword ? 'text' : 'password'" placeholder="Password" />
        </a-form-item>
        <a-form-item name="captchaCode">
          <a-input class="captcha-input" v-model:value.trim="loginForm.captchaCode" placeholder="Enter the code" />
          <img class="captcha-img" :src="captchaBase64Image" @click="getCaptcha" />
        </a-form-item>
<!--        <a-form-item>-->
<!--          <a-checkbox v-model:checked="rememberPwd">Remember the password</a-checkbox>-->
<!--          <span> ( Account：admin, password：123456)</span>-->
<!--        </a-form-item>-->
        <a-form-item>
          <div class="blank"></div>
          <div class="btn" @click="onLogin">Log in</div>
        </a-form-item>
      </a-form>
<!--      <div class="more">-->
<!--        <div class="title-box">-->
<!--          <p class="line"></p>-->
<!--          <p class="title">Other way</p>-->
<!--          <p class="line"></p>-->
<!--        </div>-->
<!--        <div class="login-type">-->
<!--          <img :src="aliLogin" />-->
<!--          <img :src="qqLogin" />-->
<!--          <img :src="googleLogin" />-->
<!--          <img :src="weiboLogin" />-->
<!--        </div>-->
<!--      </div>-->
    </div>
</div>
</template>
<script setup>
import {message} from 'ant-design-vue';
import {onMounted, onUnmounted, reactive, ref} from 'vue';
import {useRouter} from 'vue-router';
import {loginApi} from '/@/api/system/login/login-api';
import {SmartLoading} from '/@/components/framework/smart-loading';
import {LOGIN_DEVICE_ENUM} from '/@/constants/system/login-device-const';
import {useUserStore} from '/@/store/modules/system/user';
import {saveTokenToCookie} from '/@/utils/cookie-util';
import loginQR from '/@/assets/images/login/login-qr.png';

import {buildRoutes} from '/@/router/index';
import {smartSentry} from '/@/lib/smart-sentry';

//--------------------- login form ---------------------------------

const loginForm = reactive({
  loginName: 'admin',
  password: '',
  captchaCode: '',
  captchaUuid: '',
  loginDevice: LOGIN_DEVICE_ENUM.PC.value,
});
const rules = {
  loginName: [{ required: true, message: 'User name can not be null' }],
  password: [{ required: true, message: 'Password can not be null' }],
  captchaCode: [{ required: true, message: 'Code can not be null' }],
};

const showPassword = ref(false);
const router = useRouter();
const formRef = ref();
const rememberPwd = ref(false);

onMounted(() => {
  document.onkeyup = (e) => {
    if (e.keyCode == 13) {
      onLogin();
    }
  };
});

onUnmounted(() => {
  document.onkeyup = null;
});

//Login
async function onLogin() {
  formRef.value.validate().then(async () => {
    try {
      SmartLoading.show();
      const res = await loginApi.login(loginForm);
      stopRefrestCaptchaInterval();
      saveTokenToCookie(res.data.token ? res.data.token : '');
      message.success('Login successfully');
      //更新用户信息到pinia
      useUserStore().setUserLoginInfo(res.data);
      //构建系统的路由
      buildRoutes();
      router.push('/home');
    } catch (e) {
      if (e.data && e.data.code === 30001) {
        loginForm.captchaCode = '';
        getCaptcha();
      }
      smartSentry.captureError(e);
    } finally {
      SmartLoading.hide();
    }
  });
}

//--------------------- Captcha ---------------------------------

const captchaBase64Image = ref('');
async function getCaptcha() {
  try {
    let captchaResult = await loginApi.getCaptcha();
    captchaBase64Image.value = captchaResult.data.captchaBase64Image;
    loginForm.captchaUuid = captchaResult.data.captchaUuid;
    beginRefrestCaptchaInterval(captchaResult.data.expireSeconds);
  } catch (e) {
    console.log(e);
  }
}

let refrestCaptchaInterval = null;
function beginRefrestCaptchaInterval(expireSeconds) {
  if (refrestCaptchaInterval === null) {
    refrestCaptchaInterval = setInterval(getCaptcha, (expireSeconds - 5) * 1000);
  }
}

function stopRefrestCaptchaInterval() {
  if (refrestCaptchaInterval != null) {
    clearInterval(refrestCaptchaInterval);
    refrestCaptchaInterval = null;
  }
}

onMounted(getCaptcha);
</script>
<style lang="less" scoped>@import './login.less';</style>
