<template>
  <!---------- 查询表单form begin ----------->
  <a-form class="smart-query-form">
    <a-row class="smart-query-form-row">
<!--      <a-form-item label="eventdate" class="smart-query-form-item">-->
<!--        <SmartEnumSelect width="200px" v-model:value="queryForm.eventdate"  placeholder="eventdate" />-->
<!--      </a-form-item>-->
      <a-form-item label="Keyword" class="smart-query-form-item">
        <a-input style="width: 200px" v-model:value="queryForm.keyword" placeholder="event" />
      </a-form-item>
<!--      <a-form-item label="eventtime" class="smart-query-form-item">-->
<!--        <a-input style="width: 200px" v-model:value="queryForm.eventtime" placeholder="eventtime" />-->
<!--      </a-form-item>-->
<!--      <a-form-item label="eventdesc" class="smart-query-form-item">-->
<!--        <a-input style="width: 200px" v-model:value="queryForm.eventdesc" placeholder="eventdesc" />-->
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
  <!---------- 查询表单form end ----------->

  <a-card size="small" :bordered="false" :hoverable="true">
    <!---------- 表格操作行 begin ----------->
    <a-row class="smart-table-btn-block">
      <div class="smart-table-operate-block">
        <a-button @click="confirmBatchDelete" type="danger" size="small" :disabled="selectedRowKeyList.length == 0" v-privilege="'smartWatch:event:batchDelete'">
          <template #icon>
            <DeleteOutlined />
          </template>
          Batch Delete
        </a-button>
      </div>
    </a-row>
    <!---------- 表格操作行 end ----------->

    <!---------- 表格 begin ----------->
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
            <a-button @click="onDelete(record)" danger type="link" v-privilege="'smartWatch:event:delete'">Delete</a-button>
          </div>
        </template>
      </template>
    </a-table>
    <!---------- 表格 end ----------->

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

    <eventForm ref="formRef" @reloadList="queryData" />

    <eventModal ref="modalRef" />
  </a-card>
</template>
<script setup>
  import { reactive, ref, onMounted } from 'vue';
  import { message, Modal } from 'ant-design-vue';
  import { SmartLoading } from '/@/components/framework/smart-loading';
  import { eventApi } from '/@/api/smartWatch/event/event-api';
  import { PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
  import { smartSentry } from '/@/lib/smart-sentry';
  import SmartEnumSelect from '/@/components/framework/smart-enum-select/index.vue';
  import eventForm from './event-form-modal.vue';
  // ---------------------------- 表格列 ----------------------------

  const columns = ref([

    {
      title: 'Eventdate',
      dataIndex: 'eventdate',
      ellipsis: true,
    },
    {
      title: 'Eventtime',
      dataIndex: 'eventtime',
      ellipsis: true,
    },
    {
      title: 'Eventdesc',
      dataIndex: 'eventdesc',
      ellipsis: true,
    },
    {
      title: 'Action',
      dataIndex: 'action',
      fixed: 'right',
      width: 90,
    },
  ]);
    

  // ---------------------------- 查询数据表单和方法 ----------------------------

  const queryFormState = {
    eventdate:undefined,
    eventtime:undefined,
    eventdesc:undefined,
    keyword: undefined, //关键字
    pageNum: 1,
    pageSize: 10,
  };
  // 查询表单form
  const queryForm = reactive({ ...queryFormState });
  // 表格加载loading
  const tableLoading = ref(false);
  // 表格数据
  const tableData = ref([]);
  // 总数
  const total = ref(0);

  // 重置查询条件
  function resetQuery() {
    let pageSize = queryForm.pageSize;
    Object.assign(queryForm, queryFormState);
    queryForm.pageSize = pageSize;
    queryData();
  }

  // 查询数据
  async function queryData() {
    tableLoading.value = true;
    try {
      let queryResult = await eventApi.queryPage(queryForm);
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

  // ---------------------------- 查看 ----------------------------
  const modalRef = ref();

  function showModal(data) {
    modalRef.value.show(data);
  }

  // ---------------------------- 添加/修改 ----------------------------
  const formRef = ref();

  function showForm(data) {
    formRef.value.show(data);
  }

  // ---------------------------- 单个删除 ----------------------------
  //确认删除
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

  //请求删除
  async function requestDelete(data) {
    SmartLoading.show();
    try {
      let deleteForm = {
        goodsIdList: selectedRowKeyList.value,
      };
      await eventApi.delete(data.id);
      message.success('Delete successfully');
      queryData();
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      SmartLoading.hide();
    }
  }

  // ---------------------------- 批量删除 ----------------------------

  // 选择表格行
  const selectedRowKeyList = ref([]);

  function onSelectChange(selectedRowKeys) {
    selectedRowKeyList.value = selectedRowKeys;
  }

  // 批量删除
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

  //请求批量删除
  async function requestBatchDelete() {
    try {
      SmartLoading.show();
      await eventApi.batchDelete(selectedRowKeyList.value);
      message.success('Delete successfully');
      queryData();
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      SmartLoading.hide();
    }
  }
</script>
