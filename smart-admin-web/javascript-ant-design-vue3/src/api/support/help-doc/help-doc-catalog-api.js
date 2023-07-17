
import { postRequest, getRequest } from '/@/lib/axios';

export const helpDocCatalogApi = {
  //get all helpdoc catalog
  getAll: () => {
    return getRequest('/support/helpDoc/helpDocCatalog/getAll');
  },

  //add catalog
  add: (param) => {
    return postRequest('/support/helpDoc/helpDocCatalog/add', param);
  },

  //update catalog
  update: (param) => {
    return postRequest('/support/helpDoc/helpDocCatalog/update', param);
  },

  //delete catalog
  delete: (helpDocCatalogId) => {
    return getRequest(`/support/helpDoc/helpDocCatalog/delete/${helpDocCatalogId}`);
  },
};
