/*
 *  user
 */

import { getRequest, postRequest } from '/@/lib/axios';

export const userApi = {
  /**
   * @description: query user
   * @param {*}
   * @return {*}
   */
  queryAll: () => {
    return getRequest('/user/queryAll');
  },
  /**
   * @description: query user management
   * @param {*}
   * @return {*}
   */
  queryUser: (params) => {
    return postRequest('/user/query', params);
  },
  /**
   * @description: add user
   * @param {UserAddDto} params
   * @return {*}
   */
  addUser: (params) => {
    return postRequest('/user/add', params);
  },
  /**
   * @description: update user
   * @param {UserUpdateDto} params
   * @return {*}
   */
  updateUser: (params) => {
    return postRequest('/user/update', params);
  },
  /**
   * @description: delete user
   * @param {number} userId
   * @return {*}
   */
  deleteUser: (userId) => {
    return getRequest(`/user/delete/${userId}`);
  },
  /**
   * @description: batch delete user
   * @param {number} userIdList
   * @return {*}
   */
  batchDeleteUser: (userIdList) => {
    return postRequest('/user/update/batch/delete', userIdList);
  },
  /**
   * @description: reset password
   * @param {number} userId
   * @return {*}
   */
  resetPassword: (userId) => {
    return getRequest(`/user/update/password/reset/${userId}`);
  },
  /**
   * @description: update password
   * @param {number} userId
   * @return {*}
   */
  updateUserPassword: (param) => {
    return postRequest('/user/update/password',param);
  },
  /**
   * @description: update disabled
   * @param {number} userId
   * @return {*}
   */
  updateDisabled: (userId) => {
    return getRequest(`/user/update/disabled/${userId}`);
  },

  updateDatabase: (userId) => {
    return postRequest(`/multiUser/updateFile/${userId}`);
  },

};
