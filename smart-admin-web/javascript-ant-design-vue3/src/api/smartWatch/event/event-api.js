import { postRequest, getRequest } from '/@/lib/axios';

export const eventApi = {


  // add
  add: (param) => {
    return postRequest('/event/add', param);
  },

  // update
  update: (param) => {
    return postRequest('/event/update', param);
  },

};