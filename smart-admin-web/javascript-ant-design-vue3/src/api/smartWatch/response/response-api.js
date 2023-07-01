import { postRequest, getRequest } from '/@/lib/axios';

export const responseApi = {
  /**
   * 分页查询  @author  卓大
   */
   queryPage : (param) => {
    return postRequest('/response/queryPage', param);
  },

  /**
   * 增加  @author  卓大
   */
  add: (param) => {
    return postRequest('/response/add', param);
  },

  /**
   * 修改  @author  卓大
   */
  update: (param) => {
    return postRequest('/response/update', param);
  },

  /**
   * 删除  @author  卓大
   */
  delete: (id) => {
    return getRequest(`/response/delete/${id}`);
  },

  /**
   * 批量删除  @author  卓大
   */
  batchDelete: (idList) => {
    return postRequest('/response/batchDelete', idList);
  },
};