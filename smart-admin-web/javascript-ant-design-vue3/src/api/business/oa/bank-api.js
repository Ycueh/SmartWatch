import { postRequest, getRequest } from '/@/lib/axios';

export const bankApi = {
  // Create new bank information
  create: (param) => {
    return postRequest('/oa/bank/create', param);
  },

  // delete bank information
  delete: (bankId) => {
    return getRequest(`/oa/bank/delete/${bankId}`);
  },

  // query bank information
  detail: (bankId) => {
    return getRequest(`/oa/bank/get/${bankId}`);
  },

  // pagination query bank information
  pageQuery: (param) => {
    return postRequest('/oa/bank/page/query', param);
  },

  // edit bank information
  update: (param) => {
    return postRequest('/oa/bank/update', param);
  },

  // Query the non-paged bank list according to the enterprise ID
  queryList: (enterpriseId) => {
    return getRequest(`/oa/bank/query/list/${enterpriseId}`);
  },
};
