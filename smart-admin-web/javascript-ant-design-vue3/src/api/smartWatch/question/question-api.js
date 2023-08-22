import { postRequest, getRequest} from "/@/lib/axios";

export const questionApi = {
    create: (param)  => {
        return postRequest('/question/add', param);
    },
    delete: (questionId) => {
        return getRequest(`/question/delete/${questionId}`);
    },
    pageQuery: (param) => {
        return postRequest('/question/queryPage', param);
    },
    update: (param) => {
        return postRequest('/question/update', param);
    },
    // questionQuery: (itemId) => {
    //     return getRequest(`question/${itemId}`);
    // },
    // batch delete
    batchDelete: (idList) => {
        return postRequest('/question/batchDelete', idList);
    },
};