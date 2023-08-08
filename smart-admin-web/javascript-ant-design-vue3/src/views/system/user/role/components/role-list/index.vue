<!--
  * Role list
  *
  *
-->
<template>
  <a-card title="Role list" class="role-container" style="padding: 0">
    <template #extra>
      <a-button type="primary" size="small" @click="showRoleFormModal" v-privilege="'system:role:add'">Add</a-button>
    </template>
    <a-menu mode="vertical" v-model:selectedKeys="selectedKeys">
      <a-menu-item v-for="item in roleList" :key="item.roleId">
        <a-popover placement="right">
          <template #content>
            <div style="display: flex; flex-direction: column">
              <a-button type="text" @click="deleteRole(item.roleId)" v-privilege="'system:role:delete'">Delete</a-button>
              <a-button type="text" @click="showRoleFormModal(item)" v-privilege="'system:role:update'">Edit</a-button>
            </div>
          </template>
          {{ item.roleName }}
        </a-popover>
      </a-menu-item>
    </a-menu>
  </a-card>
  <RoleFormModal ref="roleFormModal" @refresh="queryAllRole" />
</template>
<script setup>
  import { message, Modal } from 'ant-design-vue';
  import _ from 'lodash';
  import { computed, onMounted, ref } from 'vue';
  import { roleApi } from '/@/api/system/role/role-api';
  import { SmartLoading } from '/@/components/framework/smart-loading';
  import RoleFormModal from '../role-form-modal/index.vue';
  import { smartSentry } from '/@/lib/smart-sentry';

  // ----------------------- Role list info ---------------------
  const roleList = ref([]);

  onMounted(queryAllRole);

  // Query Lisr
  async function queryAllRole() {
    let res = await roleApi.queryAll();
    roleList.value = res.data;
    if (!_.isEmpty(res.data) && res.data[0].roleId) {
      selectedKeys.value = [res.data[0].roleId];
    }
  }

  let selectedKeys = ref([]);
  const selectRoleId = computed(() => {
    if (!selectedKeys.value && _.isEmpty(selectedKeys.value)) {
      return null;
    }
    return selectedKeys.value[0];
  });
  // ----------------------- Add,Update,Delete ---------------------------------
  const roleFormModal = ref();

  function showRoleFormModal(role) {
    roleFormModal.value.showModal(role);
  }

  // Remove role
  function deleteRole(roleId) {
    if (!roleId) {
      return;
    }
    Modal.confirm({
      title: 'Hint',
      content: 'Are you sure to delete this role?',
      okText: 'Yes',
      okType: 'danger',
      async onOk() {
        SmartLoading.show();
        try {
          await roleApi.deleteRole(roleId);
          message.info('Delete successfully');
          queryAllRole();
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

  defineExpose({
    selectRoleId,
  });
</script>
<style scoped lang="less">
  .role-container {
    height: 100%;
    overflow-y: auto;

    :deep(.ant-card-body) {
      padding: 5px;
    }
  }
  .ant-menu-inline,
  .ant-menu-vertical,
  .ant-menu-vertical-left {
    border-right: none;
  }
</style>
