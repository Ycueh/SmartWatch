<template>
  <a-form class="smart-query-form" v-privilege="'user:query'">
    <a-row class="smart-query-form-row">

<!--      <a-form-item label="userId" class="smart-query-form-item">-->
<!--        <a-input style="width: 200px" v-model:value="queryForm.searchuserId" placeholder="userID" />-->
<!--      </a-form-item>-->
      <a-form-item label="keyword" class="smart-query-form-item">
        <a-input style="width: 200px" v-model:value="queryForm.keyword" placeholder="user" />
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
        <a-button @click="adduser" type="primary" size="small" v-privilege="'user:add'">
          <template #icon>
            <PlusOutlined />
          </template>
          Add user
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
        row-key="userId"
        bordered
        :pagination="false"
    >
      <template #bodyCell="{ record, column}">
        <template v-if="column.dataIndex === 'action'">
          <div class="smart-table-operate">
            <a-button @click="adduser(record)" type="link" v-privilege="'user:update'">Edit</a-button>
            <a-button @click="deleteuser(record)" danger type="link" v-privilege="'user:delete'">Delete</a-button>
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
    <userFormModal ref="formModal" @reloadList="queryData" />
  </a-card>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {userApi} from "/@/api/system/user/user-api";
import {smartSentry} from "/@/lib/smart-sentry";
import {message, Modal} from "ant-design-vue";
import {SmartLoading} from "/@/components/framework/smart-loading";
import {PAGE_SIZE_OPTIONS} from "/@/constants/common-const";
import userFormModal from "./user-form-modal.vue";

const columns = ref([
  // {
  //   title: 'ID',
  //   dataIndex: 'id',
  // },
  {
    title: 'userID',
    dataIndex: 'userId',
  },
  {
    title: 'loginName',
    dataIndex: 'loginName',
  },
  {
    title: 'gender',
    dataIndex: 'gender',
  },
  {
    title: 'actualName',
    dataIndex: 'actualName',
  },
  {
    title: 'phone',
    dataIndex: 'phone',
  },
  {
    title: 'disabledFlag',
    dataIndex: 'disabledFlag',
  },
  {
    title: 'administratorFlag',
    dataIndex: 'administratorFlag',
  },
  {
    title: 'createTime',
    dataIndex: 'createTime',
  },
  {
    title: 'roleIdList',
    dataIndex: 'roleIdList',
  },
  {
    title: 'roleNameList',
    dataIndex: 'roleNameList',
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
    let queryResult = await userApi.queryUser(queryForm);
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

function adduser(userData) {
  formModal.value.showDrawer(userData);
}

function deleteuser(userData) {
  Modal.confirm({
    title: 'Notice',
    content: 'Are you sure you want to delete [' + userData.userId + ']?',
    okText: 'Delete',
    okType: 'danger',
    onOk() {
      singleDelete(userData);
    },
    cancelText: 'Cancel',
    onCancel() {},
  });
}

async function singleDelete(userData) {
  try {
    SmartLoading.show();
    await userApi.delete(userData.userId);
    message.success('Deleted successfully');
    queryData();
  } catch (e) {
    smartSentry.captureError(e);
  } finally {
    SmartLoading.hide();
  }
}
</script>