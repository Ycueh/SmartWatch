/*
 * Project startup entry method
 *
 */
import * as antIcons from '@ant-design/icons-vue';
import Antd, { message } from 'ant-design-vue';
import lodash from 'lodash';
import { createApp } from 'vue';
import JsonViewer from 'vue3-json-viewer';
import 'vue3-json-viewer/dist/index.css';
import App from './App.vue';
import { smartSentry } from '/@/lib/smart-sentry';
import { loginApi } from '/@/api/system/login/login-api';
import constantsInfo from '/@/constants/index';
import { privilegeDirective } from '/@/directives/privilege';
import i18n from '/@/i18n/index';
import privilegePlugin from '/@/plugins/privilege-plugin';
import smartEnumPlugin from '/@/plugins/smart-enums-plugin';
import { buildRoutes, router } from '/@/router/index';
import { store } from '/@/store/index';
import { useUserStore } from '/@/store/modules/system/user';
import '/@/theme/index.less';
import { getTokenFromCookie } from '/@/utils/cookie-util';

/*
 * -------------------- ※ Focus on explaining the initialization logic of main.js begin ※ --------------------
 *
 * 1. In main.js, many frameworks directly call the initialization Vue method, create Vue instances, and mount routers, stores, etc. However, there is a problem with the router part;
 * 2. Because most routes are returned from the backend interface, if you directly initialize and mount the routes, the frontend has not yet requested the route data from the backend, so it can only be written in the router interceptor, which is very confusing and unclear;
 *    The correct process should be:
 *      2.1) If login information exists, first AJAX request all routes for the user, and then load them. Then create a Vue instance and mount the routes.
 *      2.2) If there is no route information, create a Vue instance and mount the routes (at this time, the routes should only include the login page, because the user knows which routes they have only after logging in).
 * 3. The two methods in main.js, getLoginInfo for getting login information and user permissions corresponding to the routes, and initVue for initializing Vue, are explained in the if block at the bottom.
 * 
 * -------------------- ※ Focus on explaining the initialization logic of main.js end ※ --------------------
 */

/**
 * Get user information and user permissions corresponding to routes, build dynamic routes
 */
async function getLoginInfo() {
  try {
    // Get login user information
    const res = await loginApi.getLoginInfo();
    // Build the system's routes
    let menuRouterList = res.data.menuList.filter((e) => e.path || e.frameUrl);
    buildRoutes(menuRouterList);
    initVue();
    // Update user information in pinia
    useUserStore().setUserLoginInfo(res.data);
  } catch (e) {
    message.error(e);
    smartSentry.captureError(e);
    initVue();
  }
}



function initVue() {
  let vueApp = createApp(App);
  let app = vueApp.use(router).use(store).use(i18n).use(Antd).use(smartEnumPlugin, constantsInfo).use(privilegePlugin).use(JsonViewer);
  // Inject privilege directive
  app.directive('privilege', {
    mounted(el, binding) {
      privilegeDirective(el, binding);
    },
  });
  // Register icon components
  Object.keys(antIcons).forEach((key) => {
    app.component(key, antIcons[key]);
  });
  // Global properties
  app.config.globalProperties.$antIcons = antIcons;
  app.config.globalProperties.$lodash = lodash;
  // Mount the app
  app.mount('#app');
}

// If no need to fetch user information, user menu, and dynamic user menu routes, directly initialize Vue
let token = getTokenFromCookie();
if (!token) {
  initVue();
} else {
  getLoginInfo();
}

