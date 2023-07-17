/*
 * department
 */
import { getRequest, postRequest } from '/@/lib/axios';

export const departmentApi = {
  /**
   * @description: query department list
   * @param {*}
   * @return {*}
   */
  queryAllDepartment: () => {
    return getRequest('/department/listAll');
  },

  /**
   * @description: query tree list
   * @param {*}
   * @return {*}
   */
   queryDepartmentTree: () => {
    return getRequest('/department/treeList');
  },

  /**
   * @description: add
   * @param {*}
   * @return {*}
   */
  addDepartment: (param) => {
    return postRequest('/department/add', param);
  },
  /**
   * @description: update
   * @param {*}
   * @return {*}
   */
  updateDepartment: (param) => {
    return postRequest('/department/update', param);
  },
  /**
   * @description: delete
   * @param {*}
   * @return {*}
   */
  deleteDepartment: (departmentId) => {
    return getRequest(`/department/delete/${departmentId}`);
  },
};
