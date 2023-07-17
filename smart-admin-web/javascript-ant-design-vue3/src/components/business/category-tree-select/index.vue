<!--
  *  category tree selection component
  *
-->
<template>
  <a-tree-select
    v-model:value="selectValue"
    :style="`width:${width}`"
    :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
    :tree-data="categoryTreeData"
    :placeholder="placeholder"
    tree-default-expand-all
    @change="onChange"
  />
</template>

<script setup>
  import { ref, watch, onMounted } from 'vue';
  import { categoryApi } from '/@/api/business/category/category-api';
  import { smartSentry } from '/@/lib/smart-sentry';

  const props = defineProps({
    value: Number,
    placeholder: {
      type: String,
      default: 'Please select',
    },
    categoryType: Number,
    width: {
      type: String,
      default: '100%',
    },
  });

  const emit = defineEmits(['update:value', 'change']);

  // -----------------  query category date -----------------
  const categoryTreeData = ref([]);
  async function queryCategoryTree() {
    if (!props.categoryType) {
      categoryTreeData.value = [];
      return;
    }
    try {
      let param = {
        categoryType: props.categoryType,
      };
      let resp = await categoryApi.queryCategoryTree(param);
      categoryTreeData.value = resp.data;
    } catch (e) {
      smartSentry.captureError(e);
    }
  }

  // -----------------  Select related monitors and events -----------------
  const selectValue = ref(props.value);
  // Arrow value change
  watch(
    () => props.value,
    (newValue) => {
      selectValue.value = newValue;
    }
  );

  // Monitor type changes
  watch(
    () => props.categoryType,
    () => {
      queryCategoryTree();
    }
  );

  function onChange(value) {
    emit('update:value', value);
    emit('change', value);
  }

  onMounted(queryCategoryTree);
</script>
