<template>
  <a-modal
    :title="form.id ? 'Edit' : 'Add'"
    width="600px"
    :closable="true"
    :visible="visibleFlag"
    @close="onClose"
    :onCancel="onClose"
    :maskClosable="false"
    :destroyOnClose="true"
  >
    <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
      <a-form-item label="Date" name="date">
        <a-input style="width: 100%" v-model:value="form.date" placeholder="Date" />
      </a-form-item>
      <a-form-item label="Time" name="time">
        <a-input style="width: 100%" v-model:value="form.time" placeholder="Time" />
      </a-form-item>
      <a-form-item label="Response Time" name="responsetime">
        <a-input style="width: 100%" v-model:value="form.responseTime" placeholder="Response Time" />
      </a-form-item>
      <a-form-item label="Question ID" name="questionID">
        <a-input style="width: 100%" v-model:value="form.questionID" placeholder="Question ID" />
      </a-form-item>
      <a-form-item label="Answer ID" name="answerID">
        <a-input style="width: 100%" v-model:value="form.answerID" placeholder="Answer ID" />
      </a-form-item>
      <a-form-item label="Question" name="question">
        <a-textarea style="width: 100%" v-model:value="form.question" placeholder="Question" />
      </a-form-item>
      <a-form-item label="Response" name="response">
        <a-textarea style="width: 100%" v-model:value="form.response" placeholder="Response" />
      </a-form-item>
    </a-form>

    <template #footer>
      <a-space>
        <a-button @click="onClose">Cancel</a-button>
        <a-button type="primary" @click="onSubmit">Save</a-button>
      </a-space>
    </template>
  </a-modal>
</template>
<script setup>
  import { reactive, ref, nextTick, defineEmits, defineExpose } from 'vue';
  import _ from 'lodash';
  import { message } from 'ant-design-vue';
  import { SmartLoading } from '/@/components/framework/smart-loading';
  import { responseApi } from '/@/api/smartWatch/response/response-api';
  import { smartSentry } from '/@/lib/smart-sentry';

  // ------------------------ Events ------------------------

  const emits = defineEmits(['reloadList']);

  // ------------------------ Show and Hide ------------------------
  // Visibility
  const visibleFlag = ref(false);

  function show(rowData) {
    Object.assign(form, formDefault);
    if (rowData && !_.isEmpty(rowData)) {
      Object.assign(form, rowData);
    }
    visibleFlag.value = true;
    nextTick(() => {
      formRef.value.clearValidate();
    });
  }

  function onClose() {
    Object.assign(form, formDefault);
    visibleFlag.value = false;
  }

  // ------------------------ Form ------------------------

  // Component ref
  const formRef = ref();

  const formDefault = {
    id: undefined,
    answerid:  undefined,
    date:  undefined,
    question:  undefined,
    questionid:  undefined,
    response:  undefined,
    responsetime:  undefined,
    time:  undefined
  };

  let form = reactive({ ...formDefault });

  // Clicking OK, validate the form
  async function onSubmit() {
    try {
      await formRef.value.validateFields();
      save();
    } catch (err) {
      message.error('Parameter validation error, please fill out the form data carefully!');
    }
  }

  // Create/Edit API
  async function save() {
    SmartLoading.show();
    try {
      if (form.id) {
        await responseApi.update(form);
      } else {
        await responseApi.add(form);
      }
      message.success('Operation successful');
      emits('reloadList');
      onClose();
    } catch (err) {
      smartSentry.captureError(err);
    } finally {
      SmartLoading.hide();
    }
  }

  defineExpose({
    show,
  });
</script>
