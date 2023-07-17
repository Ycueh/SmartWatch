import { postRequest, getRequest } from '/@/lib/axios';

export const responseApi = {
   // pagination query
   queryPage : (param) => {
    return postRequest('/response/queryPage', param);
  },

  // add
  add: (param) => {
    return postRequest('/response/add', param);
  },

  // update
  update: (param) => {
    return postRequest('/response/update', param);
  },

  // delete
  delete: (id) => {
    return getRequest(`/response/delete/${id}`);
  },

  // batch delete
  batchDelete: (idList) => {
    return postRequest('/response/batchDelete', idList);
  },
};