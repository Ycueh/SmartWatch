import { getRequest, postRequest } from '/@/lib/axios';

export const categoryApi = {
  // add category
  addCategory: (param) => {
    return postRequest('/category/add', param);
  },
  // GET
  // delete category
  deleteCategoryById: (categoryId) => {
    return getRequest(`/category/delete/${categoryId}`);
  },
  // Query category hierarchy tree
  queryCategoryTree: (param) => {
    return postRequest('/category/tree', param);
  },
  // update category
  updateCategory: (param) => {
    return postRequest('/category/update', param);
  },
  // Query category details
  getCategory: (categoryId) => {
    return getRequest(`/category/${categoryId}`);
  },
};
