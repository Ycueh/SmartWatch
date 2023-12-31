<template>
  <a-form class="smart-query-form">
    <a-row class="smart-query-form-row">

      <a-form-item label="Keyword" class="smart-query-form-item">
        <a-input style="width: 200px" v-model:value="queryForm.keyword" placeholder="question,answer" />
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
    <a-row class="smart-table-btn-block">
      <div class="smart-table-operate-block">
        <a-button @click="addQuestion" type="primary" size="small" v-privilege="'smartWatch:question:add'">
          <template #icon>
            <PlusOutlined />
          </template>
          Add question
        </a-button>
        <a-button @click="confirmBatchDelete" type="danger" size="small" :disabled="selectedRowKeyList.length == 0" v-privilege="'smartWatch:question:batchDelete'">
          <template #icon>
            <DeleteOutlined />
          </template>
          Batch Delete
        </a-button>
      </div>
<!--      <div class="smart-table-setting-block">-->
<!--        <TableOperator v-model="columns" :tableId="null" :refresh="queryData" />-->
<!--      </div>-->
    </a-row>

    <a-table
        size="small"
        :dataSource="tableData"
        :columns="columns"
        row-key="id"
        bordered
        :pagination="false"
        :row-selection="{ selectedRowKeys: selectedRowKeyList, onChange: onSelectChange }"
    >
      <template #bodyCell="{ record, column}">
        <template v-if="column.dataIndex === 'action'">
          <div class="smart-table-operate">
            <a-button @click="addQuestion(record)" type="link" v-privilege="'smartWatch:question:edit'">Edit</a-button>
            <a-button @click="deleteQuestion(record)" danger type="link" v-privilege="'smartWatch:question:delete'">Delete</a-button>
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
    <QuestionFormModal ref="formModal" @reloadList="queryData" />
  </a-card>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {questionApi} from "/@/api/smartWatch/question/question-api";
import {smartSentry} from "/@/lib/smart-sentry";
import {message, Modal} from "ant-design-vue";
import {SmartLoading} from "/@/components/framework/smart-loading";
import {PAGE_SIZE_OPTIONS} from "/@/constants/common-const";
import QuestionFormModal from "./question-form-modal.vue";

const columns = ref([
  {
    title: 'ID',
    dataIndex: 'id',
  },
  {
    title: 'questionID',
    dataIndex: 'questionId',
  },
  {
    title: 'question',
    dataIndex: 'question',
  },
  {
    title: 'answer1ID',
    dataIndex: 'answer1Id',
  },
  {
    title: 'answer1',
    dataIndex: 'answer1',
  },
  {
    title: 'answer2ID',
    dataIndex: 'answer2Id',
  },
  {
    title: 'answer2',
    dataIndex: 'answer2',
  },
  {
    title: 'answer3ID',
    dataIndex: 'answer3Id',
  },
  {
    title: 'answer3',
    dataIndex: 'answer3',
  },
  {
    title: 'answer4ID',
    dataIndex: 'answer4Id',
  },
  {
    title: 'answer4',
    dataIndex: 'answer4',
  },
  {
    title: 'Action',
    dataIndex: 'action',
    fixed: 'right',
    width: 100,
  },
]);

const queryFormState = {
  keyword: undefined,
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
    let queryResult = await questionApi.pageQuery(queryForm);
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

function addQuestion(questionData) {
  formModal.value.showDrawer(questionData);
}

function deleteQuestion(questionData) {
  Modal.confirm({
    title: 'Notice',
    content: 'Are you sure you want to delete [' + questionData.id + ']?',
    okText: 'Delete',
    okType: 'danger',
    onOk() {
      singleDelete(questionData);
    },
    cancelText: 'Cancel',
    onCancel() {},
  });
}

async function singleDelete(questionData) {
  try {
    SmartLoading.show();
    await questionApi.delete(questionData.id);
    message.success('Deleted successfully');
    queryData();
  } catch (e) {
    smartSentry.captureError(e);
  } finally {
    SmartLoading.hide();
  }
}

// batch delete
const selectedRowKeyList = ref([]);

function onSelectChange(selectedRowKeys) {
  selectedRowKeyList.value = selectedRowKeys;
}
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
async function requestBatchDelete() {
  try {
    SmartLoading.show();
    await questionApi.batchDelete(selectedRowKeyList.value);
    message.success('Delete successfully');
    queryData();
  } catch (e) {
    smartSentry.captureError(e);
  } finally {
    SmartLoading.hide();
  }
}
</script>