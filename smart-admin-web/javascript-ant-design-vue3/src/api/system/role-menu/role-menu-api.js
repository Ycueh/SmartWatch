/*
 * Role Menu
 *
 */
import { getRequest, postRequest } from '/@/lib/axios';
export const roleMenuApi = {
  /**
   * @description: Acquire role selected menu
   * @param {*}
   * @return {*}
   */
  getRoleSelectedMenu: (roleId) => {
    return getRequest(`role/menu/getRoleSelectedMenu/${roleId}`);
  },
  /**
   * @description: Update role authorities
   * @param {*}
   * @return {*}
   */
  updateRoleMenu: (data) => {
    return postRequest('role/menu/updateRoleMenu', data);
  },
};
