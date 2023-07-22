<!--
  * 系统更新日志
  *
  * @Author:    卓大
  * @Date:      2022-09-26 14:53:50
  * @Copyright  1024创新实验室
-->
<template>
  <!---------- 查询表单form begin ----------->
  <a-form class="smart-query-form" v-privilege="'response:query'">
    <a-row class="smart-query-form-row">
      <a-form-item label="Date" class="smart-query-form-item">
        <SmartEnumSelect width="200px" v-model:value="queryForm.date"  placeholder="date" />
      </a-form-item>
      <a-form-item label="Keyword" class="smart-query-form-item">
        <a-input style="width: 200px" v-model:value="queryForm.keyword" placeholder="Keyword" />
      </a-form-item>
      <a-form-item label="Time" class="smart-query-form-item">
        <a-input style="width: 200px" v-model:value="queryForm.time" placeholder="time" />
      </a-form-item>
      <a-form-item label="questionID" class="smart-query-form-item">
        <a-input style="width: 200px" v-model:value="queryForm.questionid" placeholder="questionid" />
      </a-form-item>
      <a-form-item label="responseID" class="smart-query-form-item">
        <a-input style="width: 200px" v-model:value="queryForm.responseid" placeholder="responseid" />
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
  <!---------- 查询表单form end ----------->

  <a-card size="small" :bordered="false" :hoverable="true">
    <!---------- 表格操作行 begin ----------->
    <a-row class="smart-table-btn-block">
      <div class="smart-table-operate-block">
        <a-button @click="showForm" type="primary" size="small" v-privilege="'response:add'">
          <template #icon>
            <PlusOutlined />
          </template>
          New
        </a-button>
        <a-button @click="confirmBatchDelete" type="danger" size="small" :disabled="selectedRowKeyList.length == 0" v-privilege="'response:batchDelete'">
          <template #icon>
            <DeleteOutlined />
          </template>
          batchDelete
        </a-button>
      </div>
<!--      <div class="smart-table-setting-block">-->
<!--        <TableOperator v-model="columns" :tableId="null" :refresh="queryData" />-->
<!--      </div>-->
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
            <a-button @click="onDelete(record)" danger type="link" v-privilege="'response:delete'">Delete</a-button>
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

    <responseForm ref="formRef" @reloadList="queryData" />

    <responseModal ref="modalRef" />
  </a-card>
</template>
<script setup>
  import { reactive, ref, onMounted } from 'vue';
  import { message, Modal } from 'ant-design-vue';
  import { SmartLoading } from '/@/components/framework/smart-loading';
  import { responseApi } from '/@/api/smartWatch/response/response-api';
  import { PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
  import { smartSentry } from '/@/lib/smart-sentry';
  import SmartEnumSelect from '/@/components/framework/smart-enum-select/index.vue';
  import responseForm from './reponse-form.vue';
  // ---------------------------- 表格列 ----------------------------

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
      title: 'ResponseTime',
      dataIndex: 'responseTime',
      ellipsis: true,
    },
    {
      title: 'QuestionID',
      dataIndex: 'questionID',
      ellipsis: true,
    },
    {
      title: 'AnswerID',
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
    

  // ---------------------------- 查询数据表单和方法 ----------------------------

  const queryFormState = {
    answerid: undefined, //更新类型:[1:特大版本功能更新;2:功能更新;3:bug修复]
    keyword: undefined, //关键字
    date: undefined, //发布日期 开始
    questionid: undefined, //发布日期 结束
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
      await responseApi.delete(data.id);
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
      await responseApi.batchDelete(selectedRowKeyList.value);
      message.success('Delete successfully');
      queryData();
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      SmartLoading.hide();
    }
  }
</script>
