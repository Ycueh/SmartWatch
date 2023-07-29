import { postRequest, getRequest} from "/@/lib/axios";

export const dataItemApi= {
    pageQuery: (param) => {
        return postRequest('/dataItem/queryPage', param);
      },
    
      // add
      add: (param) => {
        return postRequest('/dataItem/add', param);
      },
    
      // update
      update: (param) => {
        return postRequest('/dataItem/update', param);
      },
    
      // delete
      delete: (id) => {
        return getRequest(`/dataItem/delete/${id}`);
      },
    
      // batch delete
      batchDelete: (idList) => {
        return postRequest('/dataItem/batchDelete', idList);
      },
}