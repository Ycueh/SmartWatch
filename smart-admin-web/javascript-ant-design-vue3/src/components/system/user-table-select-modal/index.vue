<!--
  * User select window
  * 
-->
<template>
  <a-modal v-model:visible="visible" :width="900" title="Choose User" @cancel="closeModal" @ok="onSelectUser">
    <a-form class="smart-query-form">
      <a-row class="smart-query-form-row">
        <a-form-item label="Keyword" class="smart-query-form-item">
          <a-input style="width: 150px" v-model:value="params.keyword" placeholder="Keyword" />
        </a-form-item>
        <a-form-item label="Disabled" class="smart-query-form-item">
          <a-select style="width: 120px" v-model:value="params.disabledFlag" placeholder="Please choose status" allowClear>
            <a-select-option :key="1"> Disable </a-select-option>
            <a-select-option :key="0"> Enable </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item class="smart-query-form-item smart-margin-left10">
          <a-button type="primary" @click="queryUser">
            <template #icon>
              <SearchOutlined />
            </template>
            Search
          </a-button>
          <a-button @click="reset" class="smart-margin-left10">
            <template #icon>
              <ReloadOutlined />
            </template>
            Reset
          </a-button>
        </a-form-item>
      </a-row>
    </a-form>
    <a-table
      :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
      :loading="tableLoading"
      size="small"
      :columns="columns"
      :data-source="tableData"
      :pagination="false"
      bordered
      rowKey="userId"
      :scroll="{ y: 300 }"
    >
      <template #bodyCell="{ text, column }">
        <template v-if="column.dataIndex === 'disabledFlag'">
          <a-tag :color="text ? 'error' : 'processing'">{{ text ? 'Disable' : 'Enable' }}</a-tag>
        </template>

        <template v-if="column.dataIndex === 'gender'">
          <span>{{ $smartEnumPlugin.getDescByValue('GENDER_ENUM', text) }}</span>
        </template>
      </template>
    </a-table>
    <div class="smart-query-table-page">
      <a-pagination
        showSizeChanger
        showQuickJumper
        show-less-items
        :pageSizeOptions="PAGE_SIZE_OPTIONS"
        :defaultPageSize="params.pageSize"
        v-model:current="params.pageNum"
        v-model:pageSize="params.pageSize"
        :total="total"
        @change="queryUser"
        @showSizeChange="queryUser"
        :show-total="(total) => `${total} items in total`"
      />
    </div>
  </a-modal>
</template>
<script setup>
  import { message } from 'ant-design-vue';
  import { computed, reactive, ref } from 'vue';
  import { userApi } from '/@/api/system/user/user-api';
  import { PAGE_SIZE, PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
  import { smartSentry } from '/@/lib/smart-sentry';
  import { watch } from 'vue';

  
  // -----------------------  emits props ---------------------
  const emits = defineEmits('selectData');
  defineExpose({
    showModal,
  });

  // ----------------------- modal  show and hide ---------------------

  const visible = ref(false);
  async function showModal(selectUserId) {
    selectedRowKeys.value = selectUserId || [];
    visible.value = true;
    queryUser();
  }
  function closeModal() {
    Object.assign(params, defaultParams);
    selectedRowKeys.value = [];
    visible.value = false;
  }
  // ----------------------- users query and form ---------------------
  const tableLoading = ref(false);
  const total = ref();

  let defaultParams = {
    departmentId: undefined,
    disabledFlag: undefined,
    userIdList: undefined,
    keyword: undefined,
    searchCount: undefined,
    pageNum: 1,
    pageSize: PAGE_SIZE,
    sortItemList: undefined,
  };
  const params = reactive({ ...defaultParams });
  function reset() {
    Object.assign(params, defaultParams);
    queryUser();
  }
  async function queryUser() {
    tableLoading.value = true;
    try {
      let res = await userApi.queryUser(params);
      tableData.value = res.data.list;
      total.value = res.data.total;
      selectedRowKeys.value = [];
    } catch (error) {
      smartSentry.captureError(error);
    } finally {
      tableLoading.value = false;
    }
  }

  // ----------------------- User form ---------------------
  let selectedRowKeys = ref([]);
  // Selected rows
  const hasSelected = computed(() => selectedRowKeys.value.length > 0);
  function onSelectChange(keyArray) {
    selectedRowKeys.value = keyArray;
  }
  function onSelectUser() {
    if (!hasSelected.value) {
      message.warning('Please choose user');
      return;
    }
    emits('selectData', selectedRowKeys.value);
    closeModal();
  }

  // ----------------------- User form ---------------------
  const tableData = ref([]);
  //字段
  const columns = [
    {
      title: 'Name',
      dataIndex: 'actualName',
    },
    {
      title: 'Phone number',
      dataIndex: 'phone',
    },
    {
      title: 'Account',
      dataIndex: 'loginName',
    },
    {
      title: 'Status',
      dataIndex: 'disabledFlag',
    },
  ];
</script>
