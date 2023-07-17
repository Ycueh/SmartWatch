
import { postRequest } from '/@/lib/axios';

export const heartBeatApi = {
  // page query
  queryList: (param) => {
    return postRequest('/support/heartBeat/query', param);
  },
};
