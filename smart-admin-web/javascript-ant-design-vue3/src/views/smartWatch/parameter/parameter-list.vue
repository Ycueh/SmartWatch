<template>
  <a-form class="smart-query-form">
    <a-row class="smart-query-form-row">

      <a-form-item label="Keyword" class="smart-query-form-item">
        <a-input style="width: 200px" v-model:value="queryForm.keyword" placeholder="parameter" />
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

  <a-card size="small" :bordered="false" :hoverable="true">
<!--    <a-row class="smart-table-btn-block">-->
<!--      <div class="smart-table-operate-block">-->
<!--        <a-button @click="addParameter" type="primary" size="small" v-privilege="'smartWatch:parameter:add'">-->
<!--          <template #icon>-->
<!--            <PlusOutlined />-->
<!--          </template>-->
<!--          Add parameter-->
<!--        </a-button>-->
<!--      </div>-->
<!--      <div class="smart-table-setting-block">-->
<!--        <TableOperator v-model="columns" :tableId="null" :refresh="queryData" />-->
<!--      </div>-->
<!--    </a-row>-->

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
            <a-button @click="addParameter(record)" type="link" v-privilege="'smartWatch:parameter:edit'">Edit</a-button>
<!--            <a-button @click="deleteParameter(record)" danger type="link" v-privilege="'smartWatch:parameter:delete'">Delete</a-button>-->
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
    <ParameterFormModal ref="formModal" @reloadList="queryData" />
  </a-card>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {parameterApi} from "/@/api/smartWatch/parameter/parameter-api";
import {smartSentry} from "/@/lib/smart-sentry";
import {message, Modal} from "ant-design-vue";
import {SmartLoading} from "/@/components/framework/smart-loading";
import {PAGE_SIZE_OPTIONS} from "/@/constants/common-const";
import ParameterFormModal from "./parameter-form-modal.vue";

const columns = ref([
  {
    title: 'ID',
    dataIndex: 'id',
  },
  {
    title: 'paramName',
    dataIndex: 'paramName',
  },
  {
    title: 'paramValue',
    dataIndex: 'paramValue',
  },
  {
    title: 'Action',
    dataIndex: 'action',
    fixed: 'right',
    width: 100,
  },
]);

const queryFormState = {
  keyword: '',
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
    let queryResult = await parameterApi.pageQuery(queryForm);
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

function addParameter(parameterData) {
  formModal.value.showDrawer(parameterData);
}

function deleteParameter(parameterData) {
  Modal.confirm({
    title: 'Notice',
    content: 'Are you sure you want to delete [' + parameterData.id + ']?',
    okText: 'Delete',
    okType: 'danger',
    onOk() {
      singleDelete(parameterData);
    },
    cancelText: 'Cancel',
    onCancel() {},
  });
}

async function singleDelete(parameterData) {
  try {
    SmartLoading.show();
    await parameterApi.delete(parameterData.id);
    message.success('Deleted successfully');
    queryData();
  } catch (e) {
    smartSentry.captureError(e);
  } finally {
    SmartLoading.hide();
  }
}
</script>