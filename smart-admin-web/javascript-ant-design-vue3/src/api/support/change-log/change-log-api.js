
import { postRequest, getRequest } from '/@/lib/axios';

export const changeLogApi = {
  /**
   * pagination query
   */
   queryPage : (param) => {
    return postRequest('/changeLog/queryPage', param);
  },

  /**
   * add
   */
  add: (param) => {
    return postRequest('/changeLog/add', param);
  },

  /**
   * update
   */
  update: (param) => {
    return postRequest('/changeLog/update', param);
  },

  /**
   * delete
   */
  delete: (id) => {
    return getRequest(`/changeLog/delete/${id}`);
  },

  /**
   * batch delete
   */
  batchDelete: (idList) => {
    return postRequest('/changeLog/batchDelete', idList);
  },
};
