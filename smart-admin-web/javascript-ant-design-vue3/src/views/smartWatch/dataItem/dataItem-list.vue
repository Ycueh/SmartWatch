<template>
  <a-form class="smart-query-form" v-privilege="'dataItem:query'">
    <a-row class="smart-query-form-row" v-privilege="'dataItem:query'">
        <a-form-item label="dataItem" class="smart-query-form-item">
          <a-input style="width: 200px" v-model:value="queryForm.searchDataItem" placeholder="dataItem" />
        </a-form-item>
  
        <a-form-item class="smart-query-form-item">
          <a-button type="primary" @click="queryData" v-privilege="'dataItem:query'">
            <template #icon>
              <ReloadOutlined />
            </template>
            Search
          </a-button>
          <a-button @click="resetQuery" class="smart-margin-left10" v-privilege="'dataItem:query'">
            <template #icon>
              <SearchOutlined />
            </template>
            Reset
          </a-button>
        </a-form-item>
      </a-row>
  </a-form>

  <a-card size="small" :bordered="false" :hoverable="true">
    <a-row class="smart-table-btn-block">
      <div class="smart-table-operate-block">
        <a-button @click="addDataItem" type="primary" size="small" v-privilege="'dataItem:add'">
          <template #icon>
            <PlusOutlined />
          </template>
          Add dataItem
        </a-button>
      </div>

    </a-row>

    <a-table
        size="small"
        :dataSource="tableData"
        :columns="columns"
        row-key="id"
        bordered
        :pagination="false"
    >
        <template #bodyCell="{ record, column}">
          <template v-if="column.dataIndex === 'action'">
            <div class="smart-table-operate">
              <a-button @click="addDataItem(record)" type="link" v-privilege="'dataItem:update'">Edit</a-button>
              <a-button @click="deleteDataItem(record)" danger type="link" v-privilege="'dataItem:delete'">Delete</a-button>
            </div>
          </template>
        </template>
    </a-table>

    <div class="smart-query-table-page">
      <a-pagination
          show-size-changer
          show-quick-jumper
          show-less-items
          :page-size-options="PAGE_SIZE_OPTIONS"
          :default-page-size="queryForm.pageSize"
          v-model:current="queryForm.pageNum"
          v-model:pageSize="queryForm.pageSize"
          :total="total"
          @change="queryData"
          @show-size-change="queryData"
          :show-total="(total) => `${total} items in total`"
      />

    </div>
    <dataItemFormModal ref="formModal" @reloadList="queryData" />
  </a-card>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {dataItemApi} from "/@/api/smartWatch/dataItem/dataItem-api";
import {smartSentry} from "/@/lib/smart-sentry";
import {message, Modal} from "ant-design-vue";
import {SmartLoading} from "/@/components/framework/smart-loading";
import {PAGE_SIZE_OPTIONS} from "/@/constants/common-const";
import dataItemFormModal from "./dataItem-form-modal.vue";

const columns = ref([
  {
      title: 'ID',
      dataIndex: 'id',
    },
    {
      title: 'datestamp',
      dataIndex: 'datestamp',
    },
    {
      title: 'timestamp',
      dataIndex: 'timestamp',
    },
    {
      title: 'dataitem1',
      dataIndex: 'dataitem1',
    },
    {
      title: 'dataitem2',
      dataIndex: 'dataitem2',
    },{
      title: 'dataitem3',
      dataIndex: 'dataitem3',
    },
  {
    title: 'Action',
    dataIndex: 'action',
    fixed: 'right',
    width: 100,
  },
]);

const queryFormState = {
  searchDataItemId: '',
  searchDataItem: undefined,
  pageNum: 1,
  pageSize: 10,
};

const queryForm = reactive({ ...queryFormState });
const tableLoading = ref(false);
const tableData = ref([]);
const total = ref(0);

function resetQuery() {
  let pageSize = queryForm.pageSize;
  Object.assign(queryForm, queryFormState);
  queryForm.pageSize = pageSize;
  queryData();
}

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

onMounted(queryData);

const formModal = ref();

function addDataItem(dataItemData) {
  formModal.value.showDrawer(dataItemData);
}

function deleteDataItem(dataItemData) {
  Modal.confirm({
    title: 'Notice',
    content: 'Are you sure you want to delete [' + dataItemData.id + ']?',
    okText: 'Delete',
    okType: 'danger',
    onOk() {
      singleDelete(dataItemData);
    },
    cancelText: 'Cancel',
    onCancel() {},
  });
}

async function singleDelete(dataItemData) {
  try {
    SmartLoading.show();
    await dataItemApi.delete(dataItemData.id);
    message.success('Deleted successfully');
    queryData();
  } catch (e) {
    smartSentry.captureError(e);
  } finally {
    SmartLoading.hide();
  }
}
</script>