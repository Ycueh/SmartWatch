<!--
  * User select
  * 
-->
<template>
  <a-select
    v-model:value="selectValue"
    :style="`width: ${width}`"
    :placeholder="props.placeholder"
    :showSearch="true"
    :allowClear="true"
    :size="size"
    @change="onChange"
  >
    <a-select-option v-for="item in userList" :key="item.userId" :value="item.userId">
      {{ item.actualName }}
    </a-select-option>
  </a-select>
</template>

<script setup>
  import { onMounted, ref, watch } from 'vue';
  import { userApi } from '/@/api/system/user/user-api';
  import { smartSentry } from '/@/lib/smart-sentry';


  const props = defineProps({
    value: [Number, Array],
    placeholder: {
      type: String,
      default: 'Please choose',
    },
    width: {
      type: String,
      default: '100%',
    },
    size: {
      type: String,
      default: 'default',
    },
    // Role id
    roleId: {
      type: Number,
      default: null,
    },
    // Disabled
    disabledFlag: {
      type: Number,
      default: null,
    },
  });

  const emit = defineEmits(['update:value', 'change']);

  // =========== Quert data =============

  //Role menu data
  const userList = ref([]);
  async function query() {
    try {
      let params = {};
      if (props.roleId) {
        params = { roleId: props.roleId };
      }
      if (null != props.disabledFlag) {
        params.disabledFlag = props.disabledFlag;
      }
      let resp = await userApi.queryAll(params);
      userList.value = resp.data;
    } catch (e) {
      smartSentry.captureError(e);
    }
  }
  onMounted(query);

  
  const selectValue = ref(props.value);
  watch(
    () => props.value,
    (newValue) => {
      selectValue.value = newValue;
    }
  );

  function onChange(value) {
    emit('update:value', value);
    emit('change', value);
  }
</script>
