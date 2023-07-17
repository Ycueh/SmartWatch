
import { postRequest, getRequest, download } from '/@/lib/axios';

export const fileApi = {
  // upload files
  uploadUrl: '/support/file/upload',
  uploadFile: (param, folder) => {
    return postRequest(`/support/file/upload?folder=${folder}`, param);
  },

  /**
   * page query
   */
  queryPage: (param) => {
    return postRequest('/support/file/queryPage', param);
  },
  /**
   * get URL
   */
  getUrl: (fileKey) => {
    return getRequest(`/support/file/getFileUrl?fileKey=${fileKey}`);
  },

  /**
   * download file
   */
  downLoadFile: (fileName, fileKey) => {
    return download(fileName, '/support/file/downLoad', { fileKey });
  },
};
