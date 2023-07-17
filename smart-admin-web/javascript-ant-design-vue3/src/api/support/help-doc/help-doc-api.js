
import { postRequest, getRequest } from '/@/lib/axios';

export const helpDocApi = {
  // page query
  query: (param) => {
    return postRequest('/support/helpDoc/query', param);
  },

  // update
  update: (param) => {
    return postRequest('/support/helpDoc/update', param);
  },

  // add
  add: (param) => {
    return postRequest('/support/helpDoc/add', param);
  },

  // delete
  delete: (helpDocId) => {
    return getRequest(`/support/helpDoc/delete/${helpDocId}`);
  },

  // get details
  getDetail: (helpDocId) => {
    return getRequest(`/support/helpDoc/getDetail/${helpDocId}`);
  },

  // query by relation id
  queryHelpDocByRelationId: (relationId) => {
    return getRequest(`/support/helpDoc/queryHelpDocByRelationId/${relationId}`);
  },

  //----------------------- User --------------------------------

  //query all lists
  getAllHelpDocList() {
    return getRequest('/support/helpDoc/user/queryAllHelpDocList');
  },

  // get view
  view(helpDocId) {
    return getRequest(`/support/helpDoc/user/view/${helpDocId}`);
  },

  // query view record
  queryViewRecord(param) {
    return postRequest('/support/helpDoc/user/queryViewRecord', param);
  },
};
