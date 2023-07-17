/*
 * log in
 */
import { getRequest, postRequest } from '/@/lib/axios';

export const loginApi = {
  /**
   * log in
   * @param param
   */
  login: (param) => {
    return postRequest('/login', param);
  },

  /**
   * log out
   * @param param
   */
  logout: () => {
    return getRequest('/login/logout');
  },

  /**
   * get captcha
   * @param param
   */
  getCaptcha: () => {
    return getRequest('/login/getCaptcha');
  },

  /**
   * get login information
   * @param param
   */
  getLoginInfo: () => {
    return getRequest('/login/getLoginInfo');
  },

  /**
   * refresh user information
   */
  refresh: () => {
    return getRequest('/login/refresh');
  },
};
