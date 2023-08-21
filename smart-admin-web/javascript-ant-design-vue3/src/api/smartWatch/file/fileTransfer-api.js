import {getRequest, postRequest} from "/@/lib/axios";

export const fileTransferApi = {

    upload: (param) => {
        return postRequest('/file/upload', param);
    },
    download: () => {
        return getRequest('/file/download');
    },


}