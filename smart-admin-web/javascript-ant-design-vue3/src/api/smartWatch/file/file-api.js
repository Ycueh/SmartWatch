import { postRequest, getRequest } from '/@/lib/axios';

export const fileApi = {
   // pagination query
   download : () => {
    return getRequest('file/download');
  },
};