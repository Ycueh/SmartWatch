//axios

import { message } from 'ant-design-vue';
import axios from 'axios';
import { clearAllCoolies, getTokenFromCookie } from '/@/utils/cookie-util';
import { localClear } from '/@/utils/local-util';

// token message header
const TOKEN_HEADER = 'x-access-token';

// Create an axios object
const smartAxios = axios.create({
  baseURL: import.meta.env.VITE_APP_API_URL,
});

// ================================= request interceptor =================================

smartAxios.interceptors.request.use(
  (config) => {
    // Add token to the message header before sending the request
    const token = getTokenFromCookie();
    if (token) {
      config.headers[TOKEN_HEADER] = token;
    } else {
      delete config.headers[TOKEN_HEADER];
    }
    return config;
  },
  (error) => {
    // Do something about request errors
    return Promise.reject(error);
  }
);

// ================================= response interceptor =================================

// Add response interceptor
smartAxios.interceptors.response.use(
  (response) => {
    // Do something with the response data
    const res = response.data;
    if (res.code && res.code !== 1) {
      // `token` has expired or the account has been logged in elsewhere
      if (res.code === 30007 || res.code === 30008) {
        message.error('You are not logged in, please log in again');
        clearAllCoolies();
        localClear();
        //Jump to the login page and directly use the page refresh strategy
        setTimeout(() => {
          location.href = '/';
        }, 300);
        return Promise.reject(response);
      }
      message.error(res.msg);
      return Promise.reject(response);
    } else {
      return Promise.resolve(res);
    }
  },
  (error) => {
    // Do something with response errors
    if (error.message.indexOf('timeout') != -1) {
      message.error('network timeout');
    } else if (error.message == 'Network Error') {
      message.error('network connection error');
    }else if (error.message.indexOf('Request') != -1) {
      message.error('network error');
    }
    return Promise.reject(error);
  }
);

// ================================= Provide external request methods: general request, get, post, download, etc.=================================

/**
 * Generic request encapsulation
 * @param config
 */
export const request = (config) => {
  return smartAxios.request(config);
};

/**
 * post request
 */
export const postRequest = (url, data) => {
  return request({ data, url, method: 'post' });
};

/**
 * get request
 */
export const getRequest = (url, params) => {
  return request({ url, method: 'get', params });
};

/**
 * Download Document
 */
export const download = function (url) {
  request({
    method: 'get',
    url: url,
    responseType: 'blob',
  })
    .then((data) => {
      if (!data) {
        return;
      }
      let url = window.URL.createObjectURL(new Blob([data]));
      let link = document.createElement('a');
      link.style.display = 'none';
      link.href = url;
      link.setAttribute('download', 'EMADATA.db');
      document.body.appendChild(link);
      link.click();
    })
    .catch((error) => {
      throw error;
    });
};
