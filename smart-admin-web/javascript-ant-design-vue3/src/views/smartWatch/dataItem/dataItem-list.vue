<template>
  <!---------- Query Form Begin ----------->
  <a-form class="smart-query-form">
    <a-row class="smart-query-form-row">
<!--      <a-form-item label="datestamp" class="smart-query-form-item">-->
<!--        <SmartEnumSelect width="200px" v-model:value="queryForm.datestamp" placeholder="date" />-->
<!--      </a-form-item>-->
      <a-form-item label="Keyword" class="smart-query-form-item">
        <a-input style="width: 200px" v-model:value="queryForm.keyword" placeholder="dataitem" />
      </a-form-item>
<!--      <a-form-item label="timestamp" class="smart-query-form-item">-->
<!--        <a-input style="width: 200px" v-model:value="queryForm.timestamp" placeholder="timestamp" />-->
<!--      </a-form-item>-->
<!--      <a-form-item label="dataItem1" class="smart-query-form-item">-->
<!--        <a-input style="width: 200px" v-model:value="queryForm.dataItem1" placeholder="dataItem1" />-->
<!--      </a-form-item>-->
<!--      <a-form-item label="dataItem2" class="smart-query-form-item">-->
<!--        <a-input style="width: 200px" v-model:value="queryForm.dataItem2" placeholder="dataItem2" />-->
<!--      </a-form-item>-->
<!--      <a-form-item label="dataItem3" class="smart-query-form-item">-->
<!--        <a-input style="width: 200px" v-model:value="queryForm.dataItem3" placeholder="dataItem3" />-->
<!--      </a-form-item>-->
      <a-form-item class="smart-query-form-item">
        <a-button type="primary" @click="queryData">
          <template #icon>
            <SearchOutlined />
          </template>
          Search
        </a-button>
        <a-button @click="resetQuery" class="smart-margin-left10">
          <template #icon>
            <ReloadOutlined />
          </template>
          Reset
        </a-button>
      </a-form-item>
    </a-row>
  </a-form>
  <!---------- Query Form End ----------->

  <a-card size="small" :bordered="false" :hoverable="true">
    <!---------- Table Operation Row Begin ----------->
    <a-row class="smart-table-btn-block">
      <div class="smart-table-operate-block">
        <a-button @click="confirmBatchDelete" type="danger" size="small" :disabled="selectedRowKeyList.length == 0" v-privilege="'smartWatch:dataItem:batchDelete'">
          <template #icon>
            <DeleteOutlined />
          </template>
          Batch Delete
        </a-button>
      </div>
    </a-row>
    <!---------- Table Operation Row End ----------->

    <!---------- Table Begin ----------->
    <a-table
      size="small"
      :dataSource="tableData"
      :columns="columns"
      rowKey="id"
      bordered
      :pagination="false"
      :row-selection="{ selectedRowKeys: selectedRowKeyList, onChange: onSelectChange }"
    >
      <template #bodyCell="{ text, record, column }">
        <template v-if="column.dataIndex === 'action'">
          <div class="smart-table-operate">
            <a-button @click="onDelete(record)" danger type="link" v-privilege="'smartWatch:dataItem:delete'">Delete</a-button>
          </div>
        </template>
      </template>
    </a-table>
    <!---------- Table End ----------->

    <div class="smart-query-table-page">
      <a-pagination
        showSizeChanger
        showQuickJumper
        show-less-items
        :pageSizeOptions="PAGE_SIZE_OPTIONS"
        :defaultPageSize="queryForm.pageSize"
        v-model:current="queryForm.pageNum"
        v-model:pageSize="queryForm.pageSize"
        :total="total"
        @change="queryData"
        @showSizeChange="queryData"
        :show-total="(total) => `${total} in total`"
      />
    </div>

    <dataItemForm ref="formRef" @reloadList="queryData" />

    <dataItemModal ref="modalRef" />
  </a-card>
</template>
<script setup>
  import { reactive, ref, onMounted } from 'vue';
  import { message, Modal } from 'ant-design-vue';
  import { SmartLoading } from '/@/components/framework/smart-loading';
  import { dataItemApi } from '/@/api/smartWatch/dataItem/dataItem-api';
  import { PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
  import { smartSentry } from '/@/lib/smart-sentry';
  import SmartEnumSelect from '/@/components/framework/smart-enum-select/index.vue';
  import dataItemForm from './dataItem-form-modal.vue';
  // ---------------------------- Table Columns ----------------------------

  const columns = ref([

    {
      title: 'Datestamp',
      dataIndex: 'datestamp',
      ellipsis: true,
    },
    {
      title: 'Timestamp',
      dataIndex: 'timestamp',
      ellipsis: true,
    },
    {
      title: 'dataItem1',
      dataIndex: 'dataItem1',
      ellipsis: true,
    },
    {
      title: 'dataItem2',
      dataIndex: 'dataItem2',
      ellipsis: true,
    },
    {
      title: 'dataItem3',
      dataIndex: 'dataItem3',
      ellipsis: true,
    },
    {
      title: 'Action',
      dataIndex: 'action',
      fixed: 'right',
      width: 90,
    },
  ]);
    

  // ---------------------------- Query Data Form and Methods ----------------------------

  const queryFormState = {
    datestamp: undefined,
    timestamp: undefined,
    dataItem1: undefined,
    dataItem2: undefined,
    dataItem3: undefined,
    keyword: undefined,
    pageNum: 1,
    pageSize: 10,
  };
  // Query Form
  const queryForm = reactive({ ...queryFormState });
  // Table Loading
  const tableLoading = ref(false);
  // Table Data
  const tableData = ref([]);
  // Total Count
  const total = ref(0);

  // Reset Query Conditions
  function resetQuery() {
    let pageSize = queryForm.pageSize;
    Object.assign(queryForm, queryFormState);
    queryForm.pageSize = pageSize;
    queryData();
  }

  // Query Data
  async function queryData() {
    tableLoading.value = true;
    try {
      let queryResult = await dataItemApi.pageQuery(queryForm);
      console.log(queryResult);
      tableData.value = queryResult.data.list;
      total.value = queryResult.data.total;
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      tableLoading.value = false;
    }
  }

  function onChangePublicDate(dates, dateStrings) {
    queryForm.publicDateBegin = dateStrings[0];
    queryForm.publicDateEnd = dateStrings[1];
  }

  onMounted(queryData);

  // ---------------------------- View ----------------------------
  const modalRef = ref();

  function showModal(data) {
    modalRef.value.show(data);
  }

  // ---------------------------- Add/Edit ----------------------------
  const formRef = ref();

  function showForm(data) {
    formRef.value.show(data);
  }

  // ---------------------------- Single Delete ----------------------------
  // Confirm Delete
  function onDelete(data) {
    Modal.confirm({
      title: 'Hint',
      content: 'Are you sure to delete item [' + data.id + ']?',
      okText: 'Delete',
      okType: 'danger',
      onOk() {
        requestDelete(data);
      },
      cancelText: 'Cancel',
      onCancel() {},
    });
  }

  // Request Delete
  async function requestDelete(data) {
    SmartLoading.show();
    try {
      let deleteForm = {
        goodsIdList: selectedRowKeyList.value,
      };
      await dataItemApi.delete(data.id);
      message.success('Delete successfully');
      queryData();
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      SmartLoading.hide();
    }
  }

  // ---------------------------- Batch Delete ----------------------------

  // Select Table Rows
  const selectedRowKeyList = ref([]);

  function onSelectChange(selectedRowKeys) {
    selectedRowKeyList.value = selectedRowKeys;
  }

  // Confirm Batch Delete
  function confirmBatchDelete() {
    Modal.confirm({
      title: 'Hint',
      content: 'Are you sure to delete these data',
      okText: 'Delete',
      okType: 'danger',
      onOk() {
        requestBatchDelete();
      },
      cancelText: 'Cancel',
      onCancel() {},
    });
  }

  // Request Batch Delete
  async function requestBatchDelete() {
    try {
      SmartLoading.show();
      await dataItemApi.batchDelete(selectedRowKeyList.value);
      message.success('Delete successfully');
      queryData();
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      SmartLoading.hide();
    }
  }
</script>

