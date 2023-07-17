
import { postRequest, getRequest } from '/@/lib/axios';

export const loginLogApi = {
  // page query
  queryList: (param) => {
    return postRequest('/support/loginLog/page/query', param);
  },
};
