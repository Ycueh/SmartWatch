
import { postRequest, getRequest } from '/@/lib/axios';

export const cacheApi = {
  // Get all keys of a cache
  getKeys: (cacheName) => {
    return getRequest(`/support/cache/keys/${cacheName}`);
  },
  // remove a cache
  remove: (cacheName) => {
    return getRequest(`/support/cache/remove/${cacheName}`);
  },
  // get all caches
  getAllCacheNames: () => {
    return getRequest('/support/cache/names');
  },
};
