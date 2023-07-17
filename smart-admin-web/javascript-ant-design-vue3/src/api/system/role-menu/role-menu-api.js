/*
 * role menu
 */
import { getRequest, postRequest } from '/@/lib/axios';
export const roleMenuApi = {
  /**
   * @description: get role menu permission
   * @param {*}
   * @return {*}
   */
  getRoleSelectedMenu: (roleId) => {
    return getRequest(`role/menu/getRoleSelectedMenu/${roleId}`);
  },
  /**
   * @description: update role menu permission
   * @param {*}
   * @return {*}
   */
  updateRoleMenu: (data) => {
    return postRequest('role/menu/updateRoleMenu', data);
  },
};
