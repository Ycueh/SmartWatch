<template>
  <!---------- Search Form Begin ----------->
  <a-form class="smart-query-form">
    <a-row class="smart-query-form-row">
      <a-form-item label="Keyword" class="smart-query-form-item">
        <a-input style="width: 200px" v-model:value="queryForm.keyword" placeholder="Question,Response" />
      </a-form-item>
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
  <!---------- Search Form End ----------->

  <a-card size="small" :bordered="false" :hoverable="true">
    <!---------- Table Operation Row Begin ----------->
    <a-row class="smart-table-btn-block">
      <div class="smart-table-operate-block">
        <a-button @click="confirmBatchDelete" type="danger" size="small" :disabled="selectedRowKeyList.length == 0" v-privilege="'smartWatch:response:batchDelete'">
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
            <a-button @click="onDelete(record)" danger type="link" v-privilege="'smartWatch:response:delete'">Delete</a-button>
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

    

  </a-card>
</template>
<script setup>
  import { reactive, ref, onMounted } from 'vue';
  import { message, Modal } from 'ant-design-vue';
  import { SmartLoading } from '/@/components/framework/smart-loading';
  import { responseApi } from '/@/api/smartWatch/response/response-api';
  import { PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
  import { smartSentry } from '/@/lib/smart-sentry';
  
  // ---------------------------- Table Columns ----------------------------

  const columns = ref([

    {
      title: 'Date',
      dataIndex: 'date',
      ellipsis: true,
    },
    {
      title: 'Time',
      dataIndex: 'time',
      ellipsis: true,
    },
    {
      title: 'Response Time',
      dataIndex: 'responseTime',
      ellipsis: true,
    },
    {
      title: 'Question ID',
      dataIndex: 'questionID',
      ellipsis: true,
    },
    {
      title: 'Answer ID',
      dataIndex: 'answerID',
      ellipsis: true,
    },
    {
      title: 'Question',
      dataIndex: 'question',
      ellipsis: true,
    },
    {
      title: 'Response',
      dataIndex: 'response',
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
    answerid: undefined,
    keyword: undefined,
    date: undefined,
    questionid: undefined,
    pageNum: 1,
    pageSize: 10,
  };
  // Query form
  const queryForm = reactive({ ...queryFormState });
  // Table loading
  const tableLoading = ref(false);
  // Table data
  const tableData = ref([]);
  // Total count
  const total = ref(0);

  // Reset query conditions
  function resetQuery() {
    let pageSize = queryForm.pageSize;
    Object.assign(queryForm, queryFormState);
    queryForm.pageSize = pageSize;
    queryData();
  }

  // Query data
  async function queryData() {
    tableLoading.value = true;
    try {
      let queryResult = await responseApi.queryPage(queryForm);
      console.log(queryResult);
      tableData.value = queryResult.data.list;
      total.value = queryResult.data.total;
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      tableLoading.value = false;
    }
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
  // Confirm delete
  function onDelete(data) {
    Modal.confirm({
      title: 'Hint',
      content: 'Are you sure you want to delete item [' + data.id + ']?',
      okText: 'Delete',
      okType: 'danger',
      onOk() {
        requestDelete(data);
      },
      cancelText: 'Cancel',
      onCancel() {},
    });
  }

  // Request delete
  async function requestDelete(data) {
    SmartLoading.show();
    try {
      await responseApi.delete(data.id);
      message.success('Delete successful');
      queryData();
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      SmartLoading.hide();
    }
  }

  // ---------------------------- Batch Delete ----------------------------

  // Selected table rows
  const selectedRowKeyList = ref([]);

  function onSelectChange(selectedRowKeys) {
    selectedRowKeyList.value = selectedRowKeys;
  }

  // Confirm batch delete
  function confirmBatchDelete() {
    Modal.confirm({
      title: 'Hint',
      content: 'Are you sure you want to delete these data?',
      okText: 'Delete',
      okType: 'danger',
      onOk() {
        requestBatchDelete();
      },
      cancelText: 'Cancel',
      onCancel() {},
    });
  }

  // Request batch delete
  async function requestBatchDelete() {
    try {
      SmartLoading.show();
      await responseApi.batchDelete(selectedRowKeyList.value);
      message.success('Delete successful');
      queryData();
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      SmartLoading.hide();
    }
  }
</script>
