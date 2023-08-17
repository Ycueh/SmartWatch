import {postRequest} from "/@/lib/axios";

export const fileuploadApi = {

    upload: (param) => {
        return postRequest('/file/upload', param);
    },

};