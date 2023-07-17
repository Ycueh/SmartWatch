import { postRequest, getRequest } from '/@/lib/axios';

export const enterpriseApi = {
  // create enterprise
  create: (param) => {
    return postRequest('/oa/enterprise/create', param);
  },

  // delete enterprise
  delete: (enterpriseId) => {
    return getRequest(`/oa/enterprise/delete/${enterpriseId}`);
  },

  // query enterprise information
  detail: (enterpriseId) => {
    return getRequest(`/oa/enterprise/get/${enterpriseId}`);
  },

  // pagination query enterprise information
  pageQuery: (param) => {
    return postRequest('/oa/enterprise/page/query', param);
  },

  // Enterprise List Query
  queryList: (type) => {
    let query = '';
    if (type) {
      query = `?type=${type}`;
    }
    return getRequest(`/oa/enterprise/query/list${query}`);
  },

  // edit enterprise
  update: (param) => {
    return postRequest('/oa/enterprise/update', param);
  },
  // Enterprise employee list
  employeeList: (param) => {
    return postRequest('/oa/enterprise/employee/list', param);
  },
  // pagination query enterprise employee list
  queryPageEmployeeList: (param) => {
    return postRequest('/oa/enterprise/employee/queryPage', param);
  },
  // add employee
  addEmployee: (param) => {
    return postRequest('/oa/enterprise/employee/add', param);
  },

  // delete employee
  deleteEmployee: (param) => {
    return postRequest('/oa/enterprise/employee/delete', param);
  },

};
