/*
 * Document serial number
 */
import { postRequest, getRequest } from '/@/lib/axios';

export const serialNumberApi = {
  // create serial number
  generate: (generateForm) => {
    return postRequest('/support/serialNumber/generate', generateForm);
  },
  // get all serial number
  getAll: () => {
    return getRequest('/support/serialNumber/all');
  },
  // get creation record
  queryRecord: (form) => {
    return postRequest('/support/serialNumber/queryRecord', form);
  },
};
