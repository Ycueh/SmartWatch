import {getRequest, postRequest} from "/@/lib/axios";
import { download } from "/@/lib/axios";

export const fileTransferApi = {

    upload: (param) => {
        return postRequest('/file/upload', param);
    },
    downloadFile: () => {
        return download('/file/download');
    },


}