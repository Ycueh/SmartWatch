
import { postRequest } from '/@/lib/axios';

export const feedbackApi = {
  // add feedback
  addFeedback: (params) => {
    return postRequest('/support/feedback/add', params);
  },
  // page query
  queryFeedback: (params) => {
    return postRequest('/support/feedback/query', params);
  },
};
