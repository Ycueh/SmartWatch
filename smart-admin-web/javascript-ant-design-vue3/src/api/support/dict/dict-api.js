
import { postRequest, getRequest } from '/@/lib/axios';

export const dictApi = {
  // Page query data dictionary KEY
  keyQuery: (param) => {
    return postRequest('/support/dict/key/query', param);
  },
  // query all dictionary key
  queryAllKey: () => {
    return getRequest('/support/dict/key/queryAll');
  },
  /**
   * Page query data dictionary value
   */ 
  valueQuery: (param) => {
    return postRequest('/support/dict/value/query', param);
  },
  // add key
  keyAdd: (param) => {
    return postRequest('/support/dict/key/add', param);
  },
  // add value
  valueAdd: (param) => {
    return postRequest('/support/dict/value/add', param);
  },
  // update key
  keyEdit: (param) => {
    return postRequest('/support/dict/key/edit', param);
  },
  // update value
  valueEdit: (param) => {
    return postRequest('/support/dict/value/edit', param);
  },
  // delete key
  keyDelete: (keyIdList) => {
    return postRequest('/support/dict/key/delete', keyIdList);
  },
  // delete value
  valueDelete: (valueIdList) => {
    return postRequest('/support/dict/value/delete', valueIdList);
  },
  // refresh cache
  cacheRefresh: () => {
    return getRequest('/support/dict/cache/refresh');
  },
  // value list
  valueList: (keyCode) => {
    return getRequest(`/support/dict/value/list/${keyCode}`);
  },
};
