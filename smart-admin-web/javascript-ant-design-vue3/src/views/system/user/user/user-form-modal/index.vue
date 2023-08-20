<template>
  <a-drawer :title="form.userId ? 'Update' : 'Add'" :width="500" :visible="visible" :body-style="{ paddingBottom: '80px' }" @close="onClose">
    <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">

     
      <a-form-item label="login_name" name="loginName">
        <a-input v-model:value="form.loginName" placeholder="Please enter login_name" />
      </a-form-item>
      <a-form-item label="actual_name" name="actualName">
        <a-input v-model:value="form.actualName" placeholder="Please enter actual_name" />
      </a-form-item>
      <a-form-item label="phone" name="phone">
        <a-input v-model:value="form.phone" placeholder="Please enter phone" />
      </a-form-item>

      <a-form-item label="gender" name="gender">
        <a-select v-model:value="form.gender" placeholder="Please choose gender">
          <a-select-option :value="0">male</a-select-option>
          <a-select-option :value="1">female</a-select-option>
        </a-select>
        <!-- <a-input v-model:value="form.gender" placeholder="Please enter gender" /> -->
      </a-form-item>
      <a-form-item label="disabledFlag" name="disabledFlag">
        <a-select v-model:value="form.disabledFlag" placeholder="Please choose disabledFlag">
          <a-select-option :value="0">false</a-select-option>
          <a-select-option :value="1">true</a-select-option>
        </a-select>
      </a-form-item>
      <!-- <a-form-item label="administratorFlag" name="administratorFlag">
        <a-select v-model:value="form.administratorFlag" placeholder="Please choose administratorFlag">
          <a-select-option :value="0">false</a-select-option>
          <a-select-option :value="1">true</a-select-option>
        </a-select>
      </a-form-item> -->
      <!-- <a-form-item label="createTime" name="createTime">
        <a-input v-model:value="form.createTime" placeholder="Please enter createTime" />
      </a-form-item> -->
      
      <a-form-item label="role" name="roleIdList">
        <a-select mode="multiple" v-model:value="form.roleIdList" optionFilterProp="title" placeholder="Please choose a role">
          <a-select-option v-for="item in roleList" :key="item.roleId" :title="item.roleName">{{ item.roleName }}</a-select-option>
        </a-select>
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
    <UserPasswordDialog ref="userPasswordDialog" />
  </a-drawer>
</template>

<script setup>
  import {nextTick, reactive, ref} from "vue";
  import _ from "lodash";
  import {message} from "ant-design-vue";
  import {smartSentry} from "/@/lib/smart-sentry";
  import {SmartLoading} from "/@/components/framework/smart-loading";
  import {userApi} from "/@/api/system/user/user-api";
  import { roleApi } from '/@/api/system/role/role-api';
  import UserPasswordDialog from "/@/views/system/user/user/user-password-dialog/index.vue";

  const emit = defineEmits('reloadList');
  let userPasswordDialog = ref();

  //show the role list
  const roleList = ref([]); 
  async function queryAllRole() {
    let res = await roleApi.queryAll();
    roleList.value = res.data;
  }

  const formRef = ref();
  const formDefault = {
    userId:0,
    loginName: undefined,
    actualName: undefined,
    phone: undefined,
    gender:undefined,
    disabledFlag:0,
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
      queryAllRole();
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
          message.success("edit successfully");
        }else {
          let { data: passWord } = await userApi.addUser(params);
          message.success("add successfully")
          userPasswordDialog.value.showModal(form.loginName, passWord);
        }
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
