<!--
  * update password
-->
<template>
  <a-modal :visible="visible" title="update password" ok-text="confirm" cancel-text="cancel" @ok="updatePwd" @cancel="cancelModal">
    <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
      <a-form-item label="old password" name="oldPassword">
        <a-input v-model:value.trim="form.oldPassword" type="password" placeholder="Please enter the old password" />
      </a-form-item>
      <a-form-item label="new password" name="newPassword" :help="tips">
        <a-input v-model:value.trim="form.newPassword" type="password" placeholder="Please enter new password" />
      </a-form-item>
      <a-form-item label="confirm password" name="confirmPwd" :help="tips">
        <a-input v-model:value.trim="form.confirmPwd" type="password" placeholder="Please enter the password to confirm" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script setup>
  import { ref, reactive } from 'vue';
  import { message } from 'ant-design-vue';
  import { userApi } from '/@/api/system/user/user-api';
  import { SmartLoading } from '/@/components/framework/smart-loading';
  import { smartSentry } from '/@/lib/smart-sentry';

  const visible = ref(false);
  const formRef = ref();
  const tips = 'Combination of letters (not limited to uppercase and lowercase) and numbers, 6-15 digits'; //Validation rules
  const reg = /(^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$)/; //Validation regularity

  const rules = {
    oldPassword: [{ required: true, message: 'Please enter the old password' }],
    newPassword: [{ type: 'string',  pattern: reg, message: 'Invalid password format' }],
    confirmPwd: [{ type: 'string',  pattern: reg, message: 'Please enter the password to confirm' }],
  };

  const formDefault = {
    oldPassword: '',
    newPassword: '',
  };
  let form = reactive({
    ...formDefault,
  });

  async function updatePwd() {
    formRef.value
      .validate()
      .then(async () => {
        if (form.newPassword != form.confirmPwd) {
          message.error('The new password does not match the confirmation password');
          return;
        }
        SmartLoading.show();
        try {
          await userApi.updateUserPassword(form);
          message.success('Successfully modified');
          visible.value = false;
        } catch (error) {
          smartSentry.captureError(error);
        } finally {
          SmartLoading.hide();
        }
      })
      .catch((error) => {
        console.log('error', error);
        message.error('Parameter validation error, please fill in the form data carefully!');
      });
  }

  function showModal() {
    visible.value = true;
  }

  function cancelModal() {
    visible.value = false;
  }

  defineExpose({ showModal });
</script>
