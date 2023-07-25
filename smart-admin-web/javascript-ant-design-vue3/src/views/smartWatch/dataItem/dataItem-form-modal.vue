<template>
  <a-drawer :title="form.id ? 'Update' : 'Add'" :width="500" :visible="visible" :body-style="{ paddingBottom: '80px' }" @close="onClose">
    <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
      <a-form-item label="datastamp" name="datastamp">
          <a-input v-model:value="form.datastamp" placeholder="Please enter datastamp" />
        </a-form-item>
        <a-form-item label="timestamp" name="timestamp">
          <a-input v-model:value="form.timestamp" placeholder="Please enter timestamp" />
        </a-form-item>
        <a-form-item label="dataitem1" name="dataitem1">
          <a-input v-model:value="form.dataitem1" placeholder="Please enter dataitem1" />
        </a-form-item>
        <a-form-item label="dataitem2" name="dataitem2">
          <a-input v-model:value="form.dataitem2" placeholder="Please enter dataitem2" />
        </a-form-item>
        <a-form-item label="dataitem3" name="dataitem3">
          <a-input v-model:value="form.dataitem3" placeholder="Please enter dataitem3" />
        </a-form-item>
        <a-form-item label="dataitem4" name="dataitem4">
          <a-input v-model:value="form.dataitem4" placeholder="Please enter dataitem4" />
        </a-form-item>
        <a-form-item label="dataitem5" name="dataitem5">
          <a-input v-model:value="form.dataitem5" placeholder="Please enter dataitem5" />
        </a-form-item>
        <a-form-item label="dataitem6" name="dataitem6">
          <a-input v-model:value="form.dataitem6" placeholder="Please enter dataitem6" />
        </a-form-item>
        <a-form-item label="dataitem7" name="dataitem7">
          <a-input v-model:value="form.dataitem7" placeholder="Please enter dataitem7" />
        </a-form-item>
        <a-form-item label="dataitem8" name="dataitem8">
          <a-input v-model:value="form.dataitem8" placeholder="Please enter dataitem8" />
        </a-form-item>
        <a-form-item label="dataitem9" name="dataitem9">
          <a-input v-model:value="form.dataitem9" placeholder="Please enter dataitem9" />
        </a-form-item>
        <a-form-item label="dataitem10" name="dataitem10">
          <a-input v-model:value="form.dataitem10" placeholder="Please enter dataitem10" />
        </a-form-item>
        <a-form-item label="dataitem11" name="dataitem11">
          <a-input v-model:value="form.dataitem11" placeholder="Please enter dataitem11" />
        </a-form-item>
        <a-form-item label="dataitem12" name="dataitem12">
          <a-input v-model:value="form.dataitem12" placeholder="Please enter dataitem12" />
        </a-form-item>
        <a-form-item label="dataitem13" name="dataitem13">
          <a-input v-model:value="form.dataitem13" placeholder="Please enter dataitem13" />
        </a-form-item>
        <a-form-item label="dataitem14" name="dataitem14">
          <a-input v-model:value="form.dataitem14" placeholder="Please enter dataitem14" />
        </a-form-item>
        <a-form-item label="dataitem15" name="dataitem1">
          <a-input v-model:value="form.dataitem15" placeholder="Please enter dataitem15" />
        </a-form-item>
        <a-form-item label="dataitem16" name="dataitem1">
          <a-input v-model:value="form.dataitem16" placeholder="Please enter dataitem16" />
        </a-form-item>
        <a-form-item label="dataitem17" name="dataitem17">
          <a-input v-model:value="form.dataitem17" placeholder="Please enter dataitem17" />
        </a-form-item>
        <a-form-item label="dataitem18" name="dataitem18">
          <a-input v-model:value="form.dataitem18" placeholder="Please enter dataitem18" />
        </a-form-item>
        <a-form-item label="dataitem19" name="dataitem19">
          <a-input v-model:value="form.dataitem19" placeholder="Please enter dataitem19" />
        </a-form-item>
        <a-form-item label="dataitem20" name="dataitem20">
          <a-input v-model:value="form.dataitem20" placeholder="Please enter dataitem20" />
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
  import {dataItemApi} from "/@/api/smartWatch/dataItem/dataItem-api";

  const emit = defineEmits('reloadList');

  const formRef = ref();
  const formDefault = {
    id: 0,
    datastamp: undefined,
    timestamp: undefined,
    dataitem1:undefined,
    dataitem2:undefined,
    dataitem3:undefined,
    dataitem4:undefined,
    dataitem5:undefined,
    dataitem6:undefined,
    dataitem7:undefined,
    dataitem8:undefined,
    dataitem9:undefined,
    dataitem10:undefined,
    dataitem11:undefined,
    dataitem12:undefined,
    dataitem13:undefined,
    dataitem14:undefined,
    dataitem15:undefined,
    dataitem16:undefined,
    dataitem17:undefined,
    dataitem18:undefined,
    dataitem19:undefined,
    dataitem20:undefined,
  };
  let form = reactive({ ...formDefault });
  const rules = {
    
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
          await dataItemApi.update(params);
        }else {
          await dataItemApi.create(params);
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
