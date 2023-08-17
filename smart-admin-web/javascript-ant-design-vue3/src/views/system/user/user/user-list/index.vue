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
        <a-button @click="batchDelete" type="primary" size="small" v-privilege="'user:add'">
          <template #icon>
          </template>
          Batch Delete
        </a-button>
        <a-radio-group v-model:value="queryForm.disabledFlag" style="margin: 8px; flex-shrink: 0" @change="queryUserByKeyword(false)">
          <a-radio-button :value="undefined">All</a-radio-button>
          <a-radio-button :value="false">Abled</a-radio-button>
          <a-radio-button :value="true">Disabled</a-radio-button>
        </a-radio-group>
      </div>
<!--      <div class="smart-table-setting-block">-->
<!--        <TableOperator v-model="columns" :tableId="null" :refresh="queryData" />-->
<!--      </div>-->
    </a-row>

    <a-table
    :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
        size="small"
        :dataSource="tableData"
        :columns="columns"
        row-key="userId"
        :pagination="false"
        :loading="tableLoading"
        bordered
        
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
    <UserPasswordDialog ref="userPasswordDialog" />
  </a-card>
</template>

<script setup>
import {onMounted, reactive, ref,watch,computed, createVNode,} from "vue";
import _ from 'lodash';
import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
import {userApi} from "/@/api/system/user/user-api";
import {smartSentry} from "/@/lib/smart-sentry";
import {message, Modal} from "ant-design-vue";
import UserPasswordDialog from '../user-password-dialog/index.vue';
import {SmartLoading} from "/@/components/framework/smart-loading";
import {PAGE_SIZE_OPTIONS} from "/@/constants/common-const";
import userFormModal from "../user-form-modal.vue";
 // ----------------------- 以下是字段定义 emits props ---------------------


//-------------Acccount info---------
let userPasswordDialog = ref();
  function showAccount(accountName, passWord) {
    userPasswordDialog.value.showModal(accountName, passWord);
  }

//-------------Form----------
const columns = ref([
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
 // Query by keyword
 async function queryUserByKeyword() {
    tableLoading.value = true;
    try {
      queryForm.pageNum = 1;
      let res = await userApi.queryUser(queryForm);
      tableData.value = res.data.list;
      total.value = res.data.total;
      // Clear selected
      selectedRowKeys.value = [];
      selectedRows.value = [];
    } catch (error) {
      smartSentry.captureError(error);
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
// ------------------Multi choices function--------------------
let selectedRowKeys = ref([]);
  let selectedRows = ref([]);
  // Selected rows
  const hasSelected = computed(() => selectedRowKeys.value.length > 0);
  function onSelectChange(keyArray, selectRows) {
    selectedRowKeys.value = keyArray;
    selectedRows.value = selectRows;
  }
  // Batch Delte User
  function batchDelete() {
    if (!hasSelected.value) {
      message.warning('Please choose the users you want to delete');
      return;
    }
    const actualNameArray = selectedRows.value.map((e) => e.actualName);
    const userIdArray = selectedRows.value.map((e) => e.userId);
    Modal.confirm({
      title: 'Are you sure to remove these users',
      icon: createVNode(ExclamationCircleOutlined),
      content: _.join(actualNameArray, ','),
      okText: 'Delete',
      okType: 'danger',
      async onOk() {
        SmartLoading.show();
        try {
          await userApi.batchDeleteUser(userIdArray);
          message.success('Delete Successfully');
          queryData();
          selectedRowKeys.value = [];
          selectedRows.value = [];
        } catch (error) {
          smartSentry.captureError(error);
        } finally {
          SmartLoading.hide();
        }
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