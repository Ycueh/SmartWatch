<template>
  <a-form class="smart-query-form">
    <a-row class="smart-query-form-row" v-privilege="'event:query'">

      <a-form-item label="event" class="smart-query-form-item">
        <a-input style="width: 200px" v-model:value="queryForm.searchEvent" placeholder="event" />
      </a-form-item>

      <a-form-item class="smart-query-form-item">
        <a-button type="primary" @click="queryData" v-privilege="'event:query'">
          <template #icon>
            <ReloadOutlined />
          </template>
          Search
        </a-button>
        <a-button @click="resetQuery" class="smart-margin-left10" v-privilege="'event:query'">
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
        <a-button @click="addevent" type="primary" size="small" v-privilege="'event:add'">
          <template #icon>
            <PlusOutlined />
          </template>
          Add event
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
            <a-button @click="addevent(record)" type="link" v-privilege="'event:update'">Edit</a-button>
            <a-button @click="deleteevent(record)" danger type="link" v-privilege="'event:delete'">Delete</a-button>
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
    <eventFormModal ref="formModal" @reloadList="queryData" />
  </a-card>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {eventApi} from "/@/api/smartWatch/event/event-api";
import {smartSentry} from "/@/lib/smart-sentry";
import {message, Modal} from "ant-design-vue";
import {SmartLoading} from "/@/components/framework/smart-loading";
import {PAGE_SIZE_OPTIONS} from "/@/constants/common-const";
import eventFormModal from "./event-form-modal.vue";

const columns = ref([
  {
    title: 'ID',
    dataIndex: 'id',
  },
  {
    title: 'eventdate',
    dataIndex: 'eventdate',
  },
  {
    title: 'eventtime',
    dataIndex: 'eventtime',
  },
  {
    title: 'eventdesc',
    dataIndex: 'eventdesc',
  },
 
  {
    title: 'Action',
    dataIndex: 'action',
    fixed: 'right',
    width: 100,
  },
]);

const queryFormState = {
  searchEventId: '',
  searchEvent: undefined,
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
    let queryResult = await eventApi.pageQuery(queryForm);
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

function addevent(eventData) {
  formModal.value.showDrawer(eventData);
}

function deleteevent(eventData) {
  Modal.confirm({
    title: 'Notice',
    content: 'Are you sure you want to delete [' + eventData.id + ']?',
    okText: 'Delete',
    okType: 'danger',
    onOk() {
      singleDelete(eventData);
    },
    cancelText: 'Cancel',
    onCancel() {},
  });
}

async function singleDelete(eventData) {
  try {
    SmartLoading.show();
    await eventApi.delete(eventData.id);
    message.success('Deleted successfully');
    queryData();
  } catch (e) {
    smartSentry.captureError(e);
  } finally {
    SmartLoading.hide();
  }
}
</script>