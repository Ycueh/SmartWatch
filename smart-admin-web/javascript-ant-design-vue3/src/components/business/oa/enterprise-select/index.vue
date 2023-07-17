<!--
  * enterprise drop-down selection box
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
    @change="handleChange"
    :disabled="disabled"
    :mode="multiple ? 'multiple' : ''"
    optionFilterProp="label"
  >
    <a-select-option v-for="item in dataList" :key="item.enterpriseId" :label="item.enterpriseName">
      {{ item.enterpriseName }}
    </a-select-option>
  </a-select>
</template>

<script setup>
  import { onMounted, ref, watch } from 'vue';
  import { enterpriseApi } from '/@/api/business/oa/enterprise-api';

  const props = defineProps({
    value: [Number, String, Object],
    width: {
      type: String,
      default: '200px',
    },
    placeholder: {
      type: String,
      default: 'please select',
    },
    size: {
      type: String,
      default: 'default',
    },
    disabled: {
      type: Boolean,
      default: false,
    },
    multiple: {
      type: Boolean,
      default: false,
    },
    // type ENTERPRISE_TYPE_ENUM
    type: {
      type: Number,
    },
  });
  const emit = defineEmits(['update:value', 'change']);

  const selectValue = ref(props.value);

  // arrow value change
  watch(
    () => props.value,
    (newValue) => {
      selectValue.value = newValue;
    }
  );

  function handleChange(value) {
    emit('update:value', value);
    emit('change', value);
  }

  const dataList = ref([]);

  async function queryData() {
    let res = await enterpriseApi.queryList(props.type);
    dataList.value = res.data;
  }
  onMounted(queryData);
</script>
