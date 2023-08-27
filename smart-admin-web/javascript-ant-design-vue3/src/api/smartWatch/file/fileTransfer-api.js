import {getRequest, postRequest} from "/@/lib/axios";
import { download } from "/@/lib/axios";

export const fileTransferApi = {

    upload: (userId, param) => {
        return postRequest(`/file/upload/${userId}`, param);
    },
    downloadFile: () => {
        return download('/file/download');
    },
    resetDatabase: () => {
        return postRequest('/file/reset');
    },
    refreshDatabase: () => {
        return getRequest('/connection/refresh');
    }

}