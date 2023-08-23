<!--
  * Role User list
  *
-->
<template>
  <div>
    <div class="header">
      <div>
        KeyWord：
        <a-input style="width: 250px" v-model:value="queryForm.keywords" placeholder="Name/Phone/Account" />
        <a-button class="button-style" v-if="selectRoleId" type="primary" @click="queryRoleUser">Search</a-button>
        <a-button class="button-style" v-if="selectRoleId" type="default" @click="resetQueryRoleUser">Reset</a-button>
      </div>

      <div>
        <a-button class="button-style" v-if="selectRoleId" type="primary" @click="addRoleUser" v-privilege="'system:role:user:add'"
          >Add User</a-button
        >
        <a-button class="button-style" v-if="selectRoleId" type="primary" danger @click="batchDelete" v-privilege="'system:role:user:batch:delete'"
          >Batch Delete</a-button
        >
      </div>
    </div>
 
    <a-table
      :loading="tableLoading"
      :dataSource="tableData"
      :columns="columns"
      :pagination="false"
      :scroll="{ y: 400 }"
      rowKey="userId"
      :row-selection="{ selectedRowKeys: selectedRowKeyList, onChange: onSelectChange }"
      size="small"
      bordered
    >
      <template #bodyCell="{ text, record, column }">
        <template v-if="column.dataIndex === 'disabledFlag'">
          <a-tag :color="text ? 'error' : 'processing'">{{ text ? 'Disable' : 'Able' }}</a-tag>
        </template>
<!--        <template v-else-if="column.dataIndex === 'gender'">-->
<!--          <span>{{ $smartEnumPlugin.getDescByValue('GENDER_ENUM', text) }}</span>-->
<!--        </template>-->
        <template v-if="column.dataIndex === 'operate'">
          <a @click="deleteUserRole(record.userId)" v-privilege="'system:role:user:delete'">Delete</a>
        </template>
      </template>
    </a-table>
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
        @change="queryRoleUser"
        @showSizeChange="queryRoleUser"
        :show-total="showTableTotal"
      />
    </div>
    <UserTableSelectModal ref="selectUserModal" @selectData="selectData" />
  </div>
</template>
<script setup>
  import { message, Modal } from 'ant-design-vue';
  import _ from 'lodash';
  import { computed, inject, onMounted, reactive, ref, watch } from 'vue';
  import { roleApi } from '/@/api/system/role/role-api';
  import { PAGE_SIZE, showTableTotal, PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
  import { SmartLoading } from '/@/components/framework/smart-loading';
  import UserTableSelectModal from '/@/components/system/user-table-select-modal/index.vue';
  import { smartSentry } from '/@/lib/smart-sentry';

  // -----------------------  emits props ---------------------
  let selectRoleId = inject('selectRoleId');

  // ----------------------- User list: query ------------------------
  watch(
    () => selectRoleId.value,
    () => queryRoleUser()
  );

  onMounted(queryRoleUser);

  const defaultQueryForm = {
    pageNum: 1,
    pageSize: PAGE_SIZE,
    roleId: undefined,
    keywords: undefined,
  };
  // 查询表单
  const queryForm = reactive({ ...defaultQueryForm });
  // 总数
  const total = ref(0);
  // 表格数据
  const tableData = ref([]);
  // 表格loading效果
  const tableLoading = ref(false);

  function resetQueryRoleUser() {
    queryForm.keywords = '';
    queryRoleUser();
  }

  async function queryRoleUser() {
    try {
      tableLoading.value = true;
      queryForm.roleId = selectRoleId.value;
      let res = await roleApi.queryRoleUser(queryForm);
      tableData.value = res.data.list;
      total.value = res.data.total;
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      tableLoading.value = false;
    }
  }

  const columns = reactive([
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
      title: 'Disabled',
      dataIndex: 'disabledFlag',
    },
    {
      title: 'Action',
      dataIndex: 'operate',
      width: 60,
    },
  ]);

  // ----------------------- Add role user ---------------------------------
  const selectUserModal = ref();

  async function addRoleUser() {
    let res = await roleApi.getRoleAllUser(selectRoleId.value);
    let selectedIdList = res.data.map((e) => e.roleId) || [];
    selectUserModal.value.showModal(selectedIdList);
  }

  async function selectData(list) {
    if (_.isEmpty(list)) {
      message.warning('Please choose role user');
      return;
    }
    SmartLoading.show();
    try {
      let params = {
        userIdList: list,
        roleId: selectRoleId.value,
      };
      await roleApi.batchAddRoleUser(params);
      message.success('Add successfully');
      await queryRoleUser();
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      SmartLoading.hide();
    }
  }

  // ----------------------- Remove user ---------------------------------
  async function deleteUserRole(userId) {
    Modal.confirm({
      title: 'Hint',
      content: 'Are you sure to remove this user?',
      okText: 'Yes',
      okType: 'danger',
      async onOk() {
        SmartLoading.show();
        try {
          await roleApi.deleteUserRole(userId, selectRoleId.value);
          message.success('Remove successfully');
          await queryRoleUser();
        } catch (e) {
          smartSentry.captureError(e);
        } finally {
          SmartLoading.hide();
        }
      },
      cancelText: 'Cancel',
      onCancel() {},
    });
  }

  // ----------------------- Bacth delete ---------------------------------

  const selectedRowKeyList = ref([]);
  const hasSelected = computed(() => selectedRowKeyList.value.length > 0);

  function onSelectChange(selectedRowKeys) {
    selectedRowKeyList.value = selectedRowKeys;
  }

  // Batch delete
  function batchDelete() {
    if (!hasSelected.value) {
      message.warning('Please choose users.');
      return;
    }
    Modal.confirm({
      title: 'Hint',
      content: 'Are you sure to delete these users?',
      okText: 'Yes',
      okType: 'danger',
      async onOk() {
        SmartLoading.show();
        try {
          let params = {
            userIdList: selectedRowKeyList.value,
            roleId: selectRoleId.value,
          };
          await roleApi.batchRemoveRoleUser(params);
          message.success('Delete successfully');
          selectedRowKeyList.value = [];
          await queryRoleUser();
        } catch (e) {
          smartSentry.captureError(e);
        } finally {
          SmartLoading.hide();
        }
      },
      cancelText: 'Cancel',
      onCancel() {},
    });
  }
</script>

<style scoped lang="less">
  .header {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin: 20px 0;
  }
  .button-style {
    margin: 0 10px;
  }
</style>
