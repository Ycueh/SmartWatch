<!--
  * invoice information drop-down selection box
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
    <a-select-option v-for="item in dataList" :key="item.invoiceId" :label="item.invoiceHeads">
      {{ item.invoiceHeads }}
    </a-select-option>
  </a-select>
</template>

<script setup>
  import { onMounted, ref, watch } from 'vue';
  import { invoiceApi } from '/@/api/business/oa/invoice-api';
  import _ from 'lodash';

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
    enterpriseId: {
      type: Number,
    },
  });

  // ------------------------ select event ------------------------

  const emit = defineEmits(['update:value', 'change']);

  const selectValue = ref(props.value);

  // arrow value change
  watch(
    () => props.value,
    (newValue) => {
      selectValue.value = newValue;
    }
  );

  // id change
  watch(
    () => props.enterpriseId,
    (newValue) => {
      queryData();
    }
  );

  function handleChange(value) {
    emit('update:value', value);
    emit(
      'change',
      value,
      dataList.value.find((e) => e.invoiceId == value)
    );
  }

  // ------------------------ query data ------------------------

  const dataList = ref([]);
  async function queryData() {
    if (!props.enterpriseId) {
      return;
    }
    let res = await invoiceApi.queryList(props.enterpriseId);
    dataList.value = res.data;
    if (!props.value && !_.isEmpty(dataList.value)) {
      selectValue.value = res.data[0].invoiceId;
      handleChange(res.data[0].invoiceId);
    }
  }

  onMounted(queryData);
</script>
