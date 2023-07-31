import { download } from '/@/lib/axios';

export const fileApi = {
   // pagination query
   downloadFile : () => {
    return download('/file/download');
  },
};