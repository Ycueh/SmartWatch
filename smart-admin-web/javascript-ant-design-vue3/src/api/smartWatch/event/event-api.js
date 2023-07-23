import { postRequest, getRequest } from '/@/lib/axios';

export const eventApi = {


  pageQuery: (param) => {
    return postRequest('/event/queryPage', param);
  },

  // add
  add: (param) => {
    return postRequest('/event/add', param);
  },

  // update
  update: (param) => {
    return postRequest('/event/update', param);
  },

  // delete
  delete: (id) => {
    return getRequest(`/event/delete/${id}`);
  },

  // batch delete
  batchDelete: (idList) => {
    return postRequest('/event/batchDelete', idList);
  },

};