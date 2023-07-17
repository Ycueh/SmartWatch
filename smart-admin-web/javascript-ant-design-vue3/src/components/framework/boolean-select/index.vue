<!--
  *  Boolean tree selection component
  *
-->
<template>
  <a-select
    v-model:value="selectValue"
    :style="`width: ${width}px`"
    :placeholder="placeholder"
    :showSearch="true"
    :allowClear="true"
    :size="size"
    @change="handleChange"
    @deselect="handleChange"
  >
    <a-select-option v-for="item in $smartEnumPlugin.getValueDescList('FLAG_NUMBER_ENUM')" :key="item.value" :value="item.value">
      {{ item.desc }}
    </a-select-option>
  </a-select>
</template>

<script setup>
  import _ from 'lodash';
  import { ref, watch } from 'vue';

  const props = defineProps({
    value: Number,
    width: {
      type: Number,
      default: 100,
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
  });

  const emit = defineEmits(['update:value', 'change']);

  function convertBoolean2number(value) {
    let result = null;
    if (_.isNaN(value) || _.isNull(value) || _.isUndefined(value)) {
      result = null;
    } else {
      result = value ? 1 : 0;
    }
    return result;
  }
  // arrow value change
  const selectValue = ref(convertBoolean2number(props.value));
  watch(
    () => props.value,
    (newValue) => {
      selectValue.value = convertBoolean2number(newValue);
    }
  );

  const handleChange = (value) => {
    console.log('boolean enum select', value);
    let booleanResult = null;
    if (!_.isUndefined(value)) {
      booleanResult = value === 1 ? true : false;
    }
    emit('update:value', booleanResult);
    emit('change', booleanResult);
  };
</script>
