<template>
  <a-drawer :title="form.id ? 'Update' : 'Add'" :width="500" :visible="visible" :body-style="{ paddingBottom: '80px' }" @close="onClose">
    <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
      <a-form-item label="eventdate" name="eventdate">
        <a-input v-model:value="form.eventdate" placeholder="Please enter eventdate" />
      </a-form-item>
      <a-form-item label="eventtime" name="eventtime">
        <a-input v-model:value="form.eventtime" placeholder="Please enter eventtime" />
      </a-form-item>
      <a-form-item label="eventdesc" name="eventdesc">
        <a-input v-model:value="form.eventdesc" placeholder="Please enter eventdesc" />
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
  import {eventApi} from "/@/api/smartWatch/event/event-api";

  const emit = defineEmits('reloadList');

  const formRef = ref();
  const formDefault = {
    id: 0,
    eventdate:undefined,
    eventtime:undefined,
    eventdesc:undefined,
    
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
          await eventApi.update(params);
        }else {
          await eventApi.add(params);
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
