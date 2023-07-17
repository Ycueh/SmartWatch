/*
 * home api
 */
import { getRequest } from '/@/lib/axios';

export const homeApi = {
  /**
   * @description: home statistics
   * @param {*}
   * @return {*}
   */
  homeAmountStatistics: () => {
    return getRequest('/home/amount/statistics');
  },
  /**
   * @description: home wait handle information
   * @param {*}
   * @return {*}
   */
  homeWaitHandle: () => {
    return getRequest('home/wait/handle');
  },
};
