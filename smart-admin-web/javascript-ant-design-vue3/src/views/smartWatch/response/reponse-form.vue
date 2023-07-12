<!--
  * 系统更新日志
  *
  * @Author:    卓大
  * @Date:      2022-09-26 14:53:50
  * @Copyright  1024创新实验室
-->
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
        <a-input style="width: 100%" v-model:value="form.time"  placeholder="Time" />
      </a-form-item>
      <a-form-item label="ResponseTime" name="responsetime">
        <a-input style="width: 100%" v-model:value="form.responseTime" placeholder="responseTime" />
      </a-form-item>
      <a-form-item label="QuestionID" name="questionID">
        <a-input style="width: 100%" v-model:value="form.questionID" placeholder="questionID" />
      </a-form-item>
      <a-form-item label="AnswerID" name="answerID">
        <a-input style="width: 100%" v-model:value="form.answerID" placeholder="answerID" />
      </a-form-item>
      <a-form-item label="Question" name="question">
        <a-textarea style="width: 100%" v-model:value="form.question" placeholder="question" />
      </a-form-item>
      <a-form-item label="Response" name="response">
        <a-textarea style="width: 100%" v-model:value="form.response" placeholder="response" />
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
  import { reactive, ref, nextTick } from 'vue';
  import _ from 'lodash';
  import { message } from 'ant-design-vue';
  import { SmartLoading } from '/@/components/framework/smart-loading';
  import { responseApi } from '/@/api/smartWatch/response/response-api';
  import { smartSentry } from '/@/lib/smart-sentry';

  // ------------------------ 事件 ------------------------

  const emits = defineEmits(['reloadList']);

  // ------------------------ 显示与隐藏 ------------------------
  // 是否显示
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

  // ------------------------ 表单 ------------------------

  // 组件ref
  const formRef = ref();

  const formDefault = {
    id:undefined,
    answerid:  undefined,
    date:  undefined,
    question:  undefined,
    questionid:  undefined,
    response:  undefined,
    responsetime:  undefined,
    time:  undefined
  };

  let form = reactive({ ...formDefault });

  // 点击确定，验证表单
  async function onSubmit() {
    try {
      await formRef.value.validateFields();
      save();
    } catch (err) {
      message.error('参数验证错误，请仔细填写表单数据!');
    }
  }

  // 新建、编辑API
  async function save() {
    SmartLoading.show();
    try {
      if (form.id) {
        await responseApi.update(form);
      } else {
        await responseApi.add(form);
      }
      message.success('操作成功');
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
