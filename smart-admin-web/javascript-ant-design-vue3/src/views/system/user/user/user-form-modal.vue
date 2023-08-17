<template>
  <a-drawer :title="form.userId ? 'Update' : 'Add'" :width="500" :visible="visible" :body-style="{ paddingBottom: '80px' }" @close="onClose">
    <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">


      <a-form-item label="login_name" name="login_name">
        <a-input v-model:value="form.login_name" placeholder="Please enter login_name" />
      </a-form-item>
      <a-form-item label="actual_name" name="actual_name">
        <a-input v-model:value="form.actual_name" placeholder="Please enter actual_name" />
      </a-form-item>
      <a-form-item label="phone" name="phone">
        <a-input v-model:value="form.phone" placeholder="Please enter phone" />
      </a-form-item>

      <a-form-item label="gender" name="gender">
        <a-select v-model:value="form.gender" placeholder="Please enter gender">
          <a-select-option :value="0">male</a-select-option>
          <a-select-option :value="1">female</a-select-option>
        </a-select>
        <!-- <a-input v-model:value="form.gender" placeholder="Please enter gender" /> -->
      </a-form-item>
      <a-form-item label="disabledFlag" name="disabledFlag">
        <a-input v-model:value="form.disabledFlag" placeholder="Please enter disabledFlag" />
      </a-form-item>
      <a-form-item label="administratorFlag" name="administratorFlag">
        <a-input v-model:value="form.administratorFlag" placeholder="Please enter administratorFlag" />
      </a-form-item>
      <a-form-item label="createTime" name="createTime">
        <a-input v-model:value="form.createTime" placeholder="Please enter createTime" />
      </a-form-item>
      
      <a-form-item label="roleNameList" name="roleNameList">
        <a-input v-model:value="form.roleNameList" placeholder="Please enter roleNameList" />
      </a-form-item>

    </a-form>
    <div
        :style="{
        position: 'absolute',
        right: 0,
        bottom: 0,
        width: '100%',
        borderTop: '1px solid #e9e9e9',
        padding: '10px 16px',
        background: '#fff',
        textAlign: 'right',
        zIndex: 1,
      }"
    >
      <a-button style="margin-right: 8px" @click="onClose">Cancel</a-button>
      <a-button type="primary" @click="onSubmit">Submit</a-button>
    </div>
  </a-drawer>
</template>

<script setup>
  import {nextTick, reactive, ref} from "vue";
  import _ from "lodash";
  import {message} from "ant-design-vue";
  import {smartSentry} from "/@/lib/smart-sentry";
  import {SmartLoading} from "/@/components/framework/smart-loading";
  import {userApi} from "/@/api/system/user/user-api";

  const emit = defineEmits('reloadList');

  const formRef = ref();
  const formDefault = {
    userId:0,
    login_name: undefined,
    actual_name: undefined,
    phone: undefined,
    gender:undefined,
    disabledFlag:undefined,
    administratorFlag:undefined,
    createTime:undefined,
    roleIdList:undefined,
    roleNameList:undefined,
  };
  let form = reactive({ ...formDefault });
 

  const visible = ref(false);

  function showDrawer(rowData){
    Object.assign(form, formDefault);
    if (rowData && !_.isEmpty(rowData)) {
      Object.assign(form, rowData);
    }
    visible.value = true;
    nextTick(() => {
      formRef.value.clearValidate();
    });
  }

  function onClose() {
    Object.assign(form, formDefault);
    visible.value = false;
  }

  async function  onSubmit(){
    formRef.value.validate().then(async () =>{
      SmartLoading.show();
      try {
        let params = _.cloneDeep(form);
        if(form.userId) {
          await userApi.updateUser(params);
        }else {
          await userApi.addUser(params);
        }
        message.success(`${form.userId ? 'edit' : 'add'}success`);
        onClose();
        emit('reloadList');
      } catch (error) {
        smartSentry.captureError(error);
      } finally {
        SmartLoading.hide();
      }
    }).catch((error) => {
      console.log('error', error);
      message.error('Parameter validation error, please fill in the form data carefully!');
    });
  }
  defineExpose({
    showDrawer,
  });
</script>
