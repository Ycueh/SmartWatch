<!-- 
  * area selection box
  *
-->

<template>
  <a-cascader
    :style="`width:${width}`"
    v-model:value="areaValue"
    :show-search="{ filter }"
    :options="areaOptionData"
    :placeholder="placeholder"
    :size="size"
    @change="handleChange"
  />
</template>

<script setup>
  import { PROVINCE_CITY_DISTRICT } from "./province-city-district";
  import { PROVINCE_CITY } from "./province-city";
  import { ref, toRaw, watch } from "vue";

  // ============ component property ============

  const TYPE_PROVINCE_CITY_DISTRICT = "province_city_district";
  const TYPE_PROVINCE_CITY = "province_city";

  const props = defineProps({
    type: String,
    value: [Number, Array],
    width: {
      type: String,
      default: '200px',
    },
    placeholder: {
      type: String,
      default: 'select area',
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

  // ============ Component business ============
  const areaOptionData =
    props.type === TYPE_PROVINCE_CITY_DISTRICT ? PROVINCE_CITY_DISTRICT : PROVINCE_CITY;

  // Bind area data
  const areaValue = ref([]);
  // Monitor value changes
  watch(
      () => props.value,
      (newValue) => {
        if (newValue) {
          let array = [];
          for (let index = 0; index < 3; index++) {
            if (newValue[index]) {
              array.push(newValue[index].value);
            }
          }
        areaValue.value = array;
      } else {
        areaValue.value = [];
      }
    }
  );

  function handleChange(value, selectedOptions){
    emit("update:value", toRaw(selectedOptions));
    emit("change", value, toRaw(selectedOptions));
  }

  const filter = (inputValue, path) => {
    return path.some(
      (option) => option.label.toLowerCase().indexOf(inputValue.toLowerCase()) > -1
    );
  };
</script>
