<template>
  <a-drawer :title="form.id ? 'Update' : 'Add'" :width="500" :visible="visible" :body-style="{ paddingBottom: '80px' }" @close="onClose">
    <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
<!--      <a-form-item label="id" name="id">-->
<!--        <a-input v-model:value="form.id" placeholder="Please enter ID" />-->
<!--      </a-form-item>-->
<!--      <a-form-item label="questionId" name="questionId">-->
<!--        <a-input v-model:value="form.questionId" placeholder="Please enter questionID" />-->
<!--      </a-form-item>-->
      <a-form-item label="question" name="question">
        <a-input v-model:value="form.question" placeholder="Please enter question" />
      </a-form-item>
<!--      <a-form-item label="answer1ID" name="answer1Id">-->
<!--        <a-input v-model:value="form.answer1Id" placeholder="Please enter answer1ID" />-->
<!--      </a-form-item>-->
      <a-form-item label="answer1" name="answer1">
        <a-input v-model:value="form.answer1" placeholder="Please enter answer1" />
      </a-form-item>
<!--      <a-form-item label="answer2ID" name="answer2Id">-->
<!--        <a-input v-model:value="form.answer2Id" placeholder="Please enter answer2ID" />-->
<!--      </a-form-item>-->
      <a-form-item label="answer2" name="answer2">
        <a-input v-model:value="form.answer2" placeholder="Please enter answer2" />
      </a-form-item>
<!--      <a-form-item label="answer3ID" name="answer3Id">-->
<!--        <a-input v-model:value="form.answer3Id" placeholder="Please enter answer3ID" />-->
<!--      </a-form-item>-->
      <a-form-item label="answer3" name="answer3">
        <a-input v-model:value="form.answer3" placeholder="Please enter answer3" />
      </a-form-item>
<!--      <a-form-item label="answer4ID" name="answer4Id">-->
<!--        <a-input v-model:value="form.answer4Id" placeholder="Please enter answer4ID" />-->
<!--      </a-form-item>-->
      <a-form-item label="answer4" name="answer4">
        <a-input v-model:value="form.answer4" placeholder="Please enter answer4" />
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
  import {questionApi} from "/@/api/smartWatch/question/question-api";

  const emit = defineEmits('reloadList');

  const formRef = ref();
  const formDefault = {
    id: 0,
    questionId: undefined,
    question: undefined,
    answer1Id: undefined,
    answer1: undefined,
    answer2Id: undefined,
    answer2: undefined,
    answer3Id: undefined,
    answer3: undefined,
    answer4Id: undefined,
    answer4: undefined,
  };
  let form = reactive({ ...formDefault });
  const rules = {
    // questionId: [{ required: true, message: 'questionId can not be empty' }],
    // question: [{ required: true, message: 'question can not be empty' }],
    // answer1Id: [{ required: true, message: 'answer1Id can not be empty' }],
    // answer2Id: [{ required: true, message: 'answer2Id can not be empty' }],
    // answer3Id: [{ required: true, message: 'answer3Id can not be empty' }],
    // answer4Id: [{ required: true, message: 'answer4Id can not be empty' }],
  };

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
        if(form.id) {
          await questionApi.update(params);
        }else {
          await questionApi.create(params);
        }
        message.success(`${form.id ? 'edit' : 'add'}success`);
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
