import { postRequest, getRequest } from '/@/lib/axios';

export const configApi = {
  // pagination query
  queryList: (param) => {
    return postRequest('/support/config/query', param);
  },
  // add config parameter
  addConfig: (param) => {
    return postRequest('/support/config/add', param);
  },
  // update config parameter
  updateConfig: (param) => {
    return postRequest('/support/config/update', param);
  },
  // query config details
  queryByKey: (param) => {
    return getRequest(`/support/config/queryByKey?configKey=${param}`);
  },
};
