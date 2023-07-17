<!--
  * enterprise Banking drop-down selection box
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
    <a-select-option v-for="item in dataList" :key="item.bankId" :label="item.bankName">
      {{ item.bankName }}({{ starAccountNumber(item.accountNumber) }})
    </a-select-option>
  </a-select>
</template>

<script setup>
  import { onMounted, ref, watch } from 'vue';
  import { bankApi } from '/@/api/business/oa/bank-api';
  import _ from 'lodash';

  const props = defineProps({
    value: [Number, String, Object],
    width: {
      type: String,
      default: '200px',
    },
    placeholder: {
      type: String,
      default: 'Please select',
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
  // Arrow value change
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
    emit('change', value);
  }

  // ------------------------ query data ------------------------
  const dataList = ref([]);
  async function queryData() {
    if (!props.enterpriseId) {
      return;
    }
    let res = await bankApi.queryList(props.enterpriseId);
    dataList.value = res.data;
    if (!props.value && !_.isEmpty(dataList.value)) {
      selectValue.value = res.data[0].invoiceId;
      handleChange(res.data[0].invoiceId);
    }
  }

  // Bank card number, median digit, star processing
  function starAccountNumber(accountNumber) {
    if (accountNumber.length < 7) {
      return accountNumber;
    }
    return accountNumber.substr(0, 3) + '**' + accountNumber.substring(accountNumber.length - 3);
  }

  onMounted(queryData);
</script>
