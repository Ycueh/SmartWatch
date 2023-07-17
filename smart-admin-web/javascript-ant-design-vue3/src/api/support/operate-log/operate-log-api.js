
import { postRequest, getRequest } from '/@/lib/axios';

export const operateLogApi = {
  // page query
  queryList: (param) => {
    return postRequest('/support/operateLog/page/query', param);
  },
  // details
  detail: (id) => {
    return getRequest(`/support/operateLog/detail/${id}`);
  },
};
