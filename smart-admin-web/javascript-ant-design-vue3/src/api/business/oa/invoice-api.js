
import { postRequest, getRequest } from '/@/lib/axios';

export const invoiceApi = {

  // create invoice information
  create: (param) => {
    return postRequest('/oa/invoice/create', param);
  },

  // delete invoice information
  delete: (bankId) => {
    return getRequest(`/oa/invoice/delete/${bankId}`);
  },

  // query invoice information
  detail: (bankId) => {
    return getRequest(`//oa/invoice/get/${bankId}`);
  },

  // pagination query invoice information
  pageQuery: (param) => {
    return postRequest('/oa/invoice/page/query', param);
  },

  // update invoice information
  update: (param) => {
    return postRequest('/oa/invoice/update', param);
  },

  // query invoice list
  queryList: (enterpriseId) => {
    return getRequest(`/oa/invoice/query/list/${enterpriseId}`);
  },

};
