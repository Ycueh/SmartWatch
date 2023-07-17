
import { getRequest, postRequest,download } from '/@/lib/axios';

export const codeGeneratorApi = {
  // query table list
  queryTableList: (param) => {
    return postRequest('/support/codeGenerator/table/queryTableList', param);
  },

  // get table columns
  getTableColumns: (table) => {
    return getRequest(`/support/codeGenerator/table/getTableColumns/${table}`);
  },

  // ------------------- Settings -------------------

  // get table's config
  getConfig: (table) => {
    return getRequest(`/support/codeGenerator/table/getConfig/${table}`);
  },

  // update config
  updateConfig: (param) => {
    return postRequest('/support/codeGenerator/table/updateConfig', param);
  },

  // ------------------- generate -------------------

  // preview code
  preview: (param) => {
    return postRequest('/support/codeGenerator/code/preview', param);
  },

  // download code
  downloadCode: (tableName) => {
    return download(`${tableName}.zip`,`/support/codeGenerator/code/download/${tableName}`);
  },
};
