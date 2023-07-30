<template>
  <a-drawer :title="form.id ? 'Update' : 'Add'" :width="500" :visible="visible" :body-style="{ paddingBottom: '80px' }" @close="onClose">
    <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
      <a-form-item label="datestamp" name="datestamp">
          <a-input v-model:value="form.datestamp" placeholder="Please enter datestamp" />
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
    datestamp: undefined,
    timestamp: undefined,
    dataitem1:undefined,
    dataitem2:undefined,
    dataitem3:undefined,
    
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
          await dataItemApi.add(params);
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
