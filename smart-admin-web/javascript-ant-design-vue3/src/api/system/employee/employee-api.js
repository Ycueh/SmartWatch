/*
 *  employee
 */

import { getRequest, postRequest } from '/@/lib/axios';

export const employeeApi = {
  /**
   * @description: query employee
   * @param {*}
   * @return {*}
   */
  queryAll: () => {
    return getRequest('/employee/queryAll');
  },
  /**
   * @description: query employee management
   * @param {*}
   * @return {*}
   */
  queryEmployee: (params) => {
    return postRequest('/employee/query', params);
  },
  /**
   * @description: add employee
   * @param {EmployeeAddDto} params
   * @return {*}
   */
  addEmployee: (params) => {
    return postRequest('/employee/add', params);
  },
  /**
   * @description: update employee
   * @param {EmployeeUpdateDto} params
   * @return {*}
   */
  updateEmployee: (params) => {
    return postRequest('/employee/update', params);
  },
  /**
   * @description: delete employee
   * @param {number} employeeId
   * @return {*}
   */
  deleteEmployee: (employeeId) => {
    return getRequest(`/employee/delete/${employeeId}`);
  },
  /**
   * @description: batch delete employee
   * @param {number} employeeIdList
   * @return {*}
   */
  batchDeleteEmployee: (employeeIdList) => {
    return postRequest('/employee/update/batch/delete', employeeIdList);
  },
  /**
   * @description: batch update employee
   * @return {*}
   */
  batchUpdateDepartmentEmployee: (updateParam) => {
    return postRequest('/employee/update/batch/department', updateParam);
  },
  /**
   * @description: reset password
   * @param {number} employeeId
   * @return {*}
   */
  resetPassword: (employeeId) => {
    return getRequest(`/employee/update/password/reset/${employeeId}`);
  },
  /**
   * @description: update password
   * @param {number} employeeId
   * @return {*}
   */
  updateEmployeePassword: (param) => {
    return postRequest('/employee/update/password',param);
  },
  /**
   * @description: update disabled
   * @param {number} employeeId
   * @return {*}
   */
  updateDisabled: (employeeId) => {
    return getRequest(`/employee/update/disabled/${employeeId}`);
  },

  // query employee by id
  queryEmployeeByDeptId: (departmentId) => {
    return getRequest(`/employee/query/dept/${departmentId}`);
  },
};
