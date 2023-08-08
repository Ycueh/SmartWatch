/*
 *Role
 */
import { getRequest, postRequest } from '/@/lib/axios';

export const roleApi = {
  /**
   * @description: Acquire all roles
   * @param {*}
   * @return {*}
   */
  queryAll: () => {
    return getRequest('/role/getAll');
  },
  /**
   * @description:Add role
   * @param {*}
   * @return {*}
   */
  addRole: (data) => {
    return postRequest('/role/add', data);
  },
  /**
   * @description:Update role
   * @param {*}
   * @return {*}
   */
  updateRole: (data) => {
    return postRequest('/role/update', data);
  },
  /**
   * @description: Delete role
   * @param {number} roleId
   * @return {*}
   */
  deleteRole: (roleId) => {
    return getRequest(`/role/delete/${roleId}`);
  },
  /**
   * @description: Acquire Role's users
   * @param {*}
   * @return {*}
   */
  queryRoleUser: (params) => {
    return postRequest('/role/user/queryUser', params);
  },
  /**
   * @description: Remove users from role
   * @param {number} userId
   * @param {number} roleId
   * @return {*}
   */
  deleteUserRole: (userId, roleId) => {
    return getRequest('/role/user/removeUser?userId=' + userId + '&roleId=' + roleId);
  },
  /**
   * @description: Batch delete user from role
   * @param {RoleUserBatchDto} data
   * @return {*}
   */
  batchRemoveRoleUser: (data) => {
    return postRequest('/role/user/batchRemoveRoleUser', data);
  },
  /**
   * @description: Acquire user by role id
   * @param {*}
   * @return {*}
   */
  getRoleAllUser: (roleId) => {
    return getRequest(`/role/user/getAllUserByRoleId/${roleId}`);
  },
  /**
   * @description: Batch add users
   * @param  data
   * @return {*}
   */
  batchAddRoleUser: (data) => {
    return postRequest('/role/user/batchAddRoleUser', data);
  },
};
