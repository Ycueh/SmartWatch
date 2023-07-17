/*
 * Table Custom Columns
 */
import { postRequest, getRequest } from '/@/lib/axios';

export const tableColumnApi = {
  // update columns
  updateTableColumn: (param) => {
    return postRequest('/support/tableColumn/update', param);
  },

  // query columns
  getColumns: (tableId) => {
    return getRequest(`/support/tableColumn/getColumns/${tableId}`);
  },

  // delete columns
  deleteColumns: (tableId) => {
    return getRequest(`/support/tableColumn/delete/${tableId}`);
  },
};
