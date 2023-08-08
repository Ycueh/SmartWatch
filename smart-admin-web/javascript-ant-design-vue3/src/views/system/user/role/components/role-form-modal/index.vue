<!--
  * Role Form
  *
-->
<template>
  <a-modal :title="form.roleId ? 'Edit Role' : 'Add Role'" :width="600" :visible="modalVisible" @cancel="onClose" :footer="null">
    <a-form ref="formRef" :model="form" :rules="rules" :labelCol="{ span: 4 }">
      <a-form-item label="Role Name" name="roleName">
        <a-input style="width: 100%" placeholder="Please input role name" v-model:value="form.roleName" />
      </a-form-item>
      <a-form-item label="Role Remark">
        <a-input style="width: 100%" placeholder="Please input role remark" v-model:value="form.remark" />
      </a-form-item>
    </a-form>

    <div class="footer">
      <a-button style="margin-right: 8px" @click="onClose">Cancel</a-button>
      <a-button type="primary" @click="submitForm">Submit</a-button>
    </div>
  </a-modal>
</template>

<script setup>
  import { message } from 'ant-design-vue';
  import { reactive, ref } from 'vue';
  import { roleApi } from '/@/api/system/role/role-api';
import { smartSentry } from '/@/lib/smart-sentry';
  import { SmartLoading } from '/@/components/framework/smart-loading';
  // ----------------------- 以下是字段定义 emits props ---------------------
  let emits = defineEmits(['refresh']);

  defineExpose({
    showModal,
  });

  // ----------------------- modal 显示与隐藏 ---------------------
  const modalVisible = ref(false);

  function showModal(role) {
    Object.assign(form, formDefault);
    if (role) {
      Object.assign(form, role);
    }
    modalVisible.value = true;
  }

  function onClose() {
    Object.assign(form, formDefault);
    modalVisible.value = false;
  }

  // ----------------------- 表单 ---------------------

  const formRef = ref();

  const formDefault = {
    id: undefined,
    remark: undefined,
    roleName: undefined,
  };

  let form = reactive({ ...formDefault });

  // Role rules
  const rules = {
    roleName: [{ required: true, message: 'Please input role names' }],
  };

  // Submit form
  async function submitForm() {
    formRef.value
      .validate()
      .then(async () => {
        SmartLoading.show();
        try {
          if (form.roleId) {
            await roleApi.updateRole(form);
          } else {
            await roleApi.addRole(form);
          }
          message.info(`${form.roleId ? 'Edit' : 'Add'}Successfully`);
          emits('refresh');
          onClose();
        } catch (e) {
          smartSentry.captureError(e);
        } finally {
          SmartLoading.hide();
        }
      })
      .catch((error) => {
        message.error('Form is not correct, please fill the form again');
      });
  }
</script>

<style scoped lang="less">
  .footer {
    width: 100%;
    border-top: 1px solid #e9e9e9;
    padding: 10px 16px;
    background: #fff;
    text-align: right;
    z-index: 1;
  }
</style>
