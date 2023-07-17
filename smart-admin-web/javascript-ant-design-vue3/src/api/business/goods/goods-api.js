import { postRequest, getRequest } from '/@/lib/axios';

export const goodsApi = {
  // add goods
  addGoods: (param) => {
    return postRequest('/goods/add', param);
  },
  // delete
  deleteGoods: (goodsId) => {
    return getRequest(`/goods/delete/${goodsId}`);
  },
  // batch
  batchDelete: (goodsIdList) => {
    return postRequest('/goods/batchDelete', goodsIdList);
  },
  // pagination query
  queryGoodsList: (param) => {
    return postRequest('/goods/query', param);
  },
  // update goods
  updateGoods: (param) => {
    return postRequest('/goods/update', param);
  },
};
