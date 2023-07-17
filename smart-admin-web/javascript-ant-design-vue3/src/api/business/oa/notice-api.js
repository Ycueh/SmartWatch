
import { postRequest, getRequest } from '/@/lib/axios';

export const noticeApi = {
  // ---------------- Notice Type -----------------------

  // get all notice
  getAllNoticeTypeList() {
    return getRequest('/oa/noticeType/getAll');
  },

  // add notice
  addNoticeType(name) {
    return getRequest(`/oa/noticeType/add/${name}`);
  },

  // update notice
  updateNoticeType(noticeTypeId, name) {
    return getRequest(`/oa/noticeType/update/${noticeTypeId}/${name}`);
  },
  // delete notice
  deleteNoticeType(noticeTypeId) {
    return getRequest(`/oa/noticeType/delete/${noticeTypeId}`);
  },

  // ---------------- notice management -----------------------

  // pagination query
  queryNotice(param) {
    return postRequest('/oa/notice/query', param);
  },

  // add notice
  addNotice(param) {
    return postRequest('/oa/notice/add', param);
  },

  // update notice
  updateNotice(param) {
    return postRequest('/oa/notice/update', param);
  },

  // delete
  deleteNotice(noticeId) {
    return getRequest(`/oa/notice/delete/${noticeId}`);
  },

  // update details
  getUpdateNoticeInfo(noticeId) {
    return getRequest(`/oa/notice/getUpdateVO/${noticeId}`);
  },

  // --------------------- employee view-------------------------

  // get employee details
  view(noticeId) {
    return getRequest(`/oa/notice/employee/view/${noticeId}`);
  },

  // query employee
  queryEmployeeNotice(param) {
    return postRequest('/oa/notice/employee/query', param);
  },

  // query view record
  queryViewRecord(param) {
    return postRequest('/oa/notice/employee/queryViewRecord', param);
  },
};
