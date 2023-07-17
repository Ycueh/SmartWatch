/*
 * menu
 */
import { getRequest, postRequest } from '/@/lib/axios';

export const menuApi = {
  /**
   * add menu
   */
  addMenu: (param) => {
    return postRequest('/menu/add', param);
  },

  /**
   * update menu
   */
  updateMenu: (param) => {
    return postRequest('/menu/update', param);
  },

  /**
   * batch delete menu
   */
  batchDeleteMenu: (menuIdList) => {
    return getRequest(`/menu/batchDelete?menuIdList=${menuIdList}`);
  },

  /**
   * query all menu list
   */
  queryMenu: () => {
    return getRequest('/menu/query');
  },

  /**
   * query menu tree
   */
  queryMenuTree: (onlyMenu) => {
    return getRequest(`/menu/tree?onlyMenu=${onlyMenu}`);
  },

  /**
   * get request url
   */
  getAuthUrl: () => {
    return getRequest('/menu/auth/url');
  },
};
