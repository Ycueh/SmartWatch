import {postRequest} from "/@/lib/axios";

export const fileuploadApi = {

    upload: (param) => {
        return postRequest('/api/upload', param);
    },

};