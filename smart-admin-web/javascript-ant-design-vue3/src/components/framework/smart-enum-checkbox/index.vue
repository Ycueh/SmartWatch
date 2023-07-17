<!--
  * enum checkbox
  *
-->
<template>
  <a-checkbox-group :style="`width: ${width}`" v-model:value="selectValue" :options="optionList" @change="handleChange" />
</template>

<script setup>
  import { ref, watch, getCurrentInstance, onMounted } from 'vue';

  const props = defineProps({
    enumName: String,
    value: Array,
    width: {
      type: String,
      default: '200px',
    },
  });

  // ------------ Enumerate data load and build ------------

  const optionList = ref([]);
  function buildOptionList() {
    const internalInstance = getCurrentInstance(); // effective, global
    const smartEnumPlugin = internalInstance.appContext.config.globalProperties.$smartEnumPlugin;
    const valueList = smartEnumPlugin.getValueDescList(props.enumName);
    optionList.value = valueList.map((e) => Object.assign({}, { value: e.value, label: e.desc }));
  }

  onMounted(buildOptionList);

  // ------------ Data selection, event ------------

  const selectValue = ref(props.value);

  watch(
    () => props.value,
    (newValue) => {
      selectValue.value = newValue;
    }
  );

  const emit = defineEmits(['update:value', 'change']);
  function handleChange(value) {
    emit('update:value', value);
    emit('change', value);
  }
</script>
