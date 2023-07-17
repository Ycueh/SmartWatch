/*
 * role
 */
import { getRequest, postRequest } from '/@/lib/axios';

export const roleApi = {
  /**
   * @description: get all roles
   * @param {*}
   * @return {*}
   */
  queryAll: () => {
    return getRequest('/role/getAll');
  },
  /**
   * @description:add role
   * @param {*}
   * @return {*}
   */
  addRole: (data) => {
    return postRequest('/role/add', data);
  },
  /**
   * @description:update role
   * @param {*}
   * @return {*}
   */
  updateRole: (data) => {
    return postRequest('/role/update', data);
  },
  /**
   * @description: delete role
   * @param {number} roleId
   * @return {*}
   */
  deleteRole: (roleId) => {
    return getRequest(`/role/delete/${roleId}`);
  },
  /**
   * @description: batch set data scope
   * @param {DataScopeBatchSetRoleDto} data
   * @return {*}
   */
  updateDataScope: (data) => {
    return postRequest('/role/dataScope/updateRoleDataScopeList', data);
  },
  /**
   * @description: get all data scope
   * @param {*}
   * @return {*}
   */
  getDataScopeList: () => {
    return getRequest('/dataScope/list');
  },
  /**
   * @description: get all data scope by role id
   * @param {number} roleId
   * @return {*}
   */
  getDataScopeByRoleId: (roleId) => {
    return getRequest(`/role/dataScope/getRoleDataScopeList/${roleId}`);
  },
  /**
   * @description: get employee list
   * @param {*}
   * @return {*}
   */
  queryRoleEmployee: (params) => {
    return postRequest('/role/employee/queryEmployee', params);
  },
  /**
   * @description: delete employee
   * @param {number} employeeId
   * @param {number} roleId
   * @return {*}
   */
  deleteEmployeeRole: (employeeId, roleId) => {
    return getRequest('/role/employee/removeEmployee?employeeId=' + employeeId + '&roleId=' + roleId);
  },
  /**
   * @description: batch delete employee
   * @param {RoleEmployeeBatchDto} data
   * @return {*}
   */
  batchRemoveRoleEmployee: (data) => {
    return postRequest('/role/employee/batchRemoveRoleEmployee', data);
  },
  /**
   * @description: get employee by role id
   * @param {*}
   * @return {*}
   */
  getRoleAllEmployee: (roleId) => {
    return getRequest(`/role/employee/getAllEmployeeByRoleId/${roleId}`);
  },
  /**
   * @description: batch add employee
   * @param  data
   * @return {*}
   */
  batchAddRoleEmployee: (data) => {
    return postRequest('/role/employee/batchAddRoleEmployee', data);
  },
};
