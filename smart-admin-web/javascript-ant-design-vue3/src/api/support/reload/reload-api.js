/*
 * reload (Memory hot loading, hooks, etc.)
 */
import { postRequest, getRequest } from '/@/lib/axios';

export const reloadApi = {
  // query reload list
  queryList: () => {
    return getRequest('/support/reload/query');
  },
  // get reload result
  queryReloadResult: (tag) => {
    return getRequest(`/support/reload/result/${tag}`);
  },
  // reload
  reload: (reloadForm) => {
    return postRequest('/support/reload/update', reloadForm);
  },
};
