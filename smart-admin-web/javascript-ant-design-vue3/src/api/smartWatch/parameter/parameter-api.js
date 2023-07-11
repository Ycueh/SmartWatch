import { postRequest, getRequest} from "/@/lib/axios";

export const parameterApi = {
    // create: (param)  => {
    //     return postRequest('/question/add', param);
    // },
    // delete: (questionId) => {
    //     return getRequest(`/question/delete/${questionId}`);
    // },
    pageQuery: (param) => {
        return postRequest('/parameter/queryPage', param);
    },
    update: (param) => {
        return postRequest('/parameter/update', param);
    },
    // parameterQuery: (itemId) => {
    //     return getRequest(`/parameter/${itemId}`);
    // },
};