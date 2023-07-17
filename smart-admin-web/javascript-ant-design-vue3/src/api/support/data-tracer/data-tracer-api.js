
import { postRequest } from '/@/lib/axios';

export const dataTracerApi = {
  // Query business operation logs by page
  queryList: (param) => {
    return postRequest('/support/dataTracer/query', param);
  },
  
};
