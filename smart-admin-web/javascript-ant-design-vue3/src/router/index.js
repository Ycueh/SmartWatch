//router
import nProgress from 'nprogress';
import 'nprogress/nprogress.css';
import { nextTick } from 'vue';
import { createRouter, createWebHashHistory } from 'vue-router';
import { routerArray } from './routers';
import { PAGE_PATH_404, PAGE_PATH_LOGIN } from '/@/constants/common-const';
import { HOME_PAGE_NAME } from '/@/constants/system/home-const';
import SmartLayout from '/@/layout/smart-layout.vue';
import { useUserStore } from '/@/store/modules/system/user';
import { clearAllCoolies, getTokenFromCookie } from '/@/utils/cookie-util';
import { localClear } from '/@/utils/local-util';
import lodash from 'lodash';

export const router = createRouter({
  history: createWebHashHistory(),
  routes: routerArray,
  strict: true,
  scrollBehavior: () => ({ left: 0, top: 0 }),
});

// ----------------------- Before Route Load -----------------------
router.beforeEach(async (to, from, next) => {
  // Start progress bar
  nProgress.start();

  // Public pages, can be accessed anytime
  if (to.path === PAGE_PATH_404 || to.path === PAGE_PATH_LOGIN) {
    next();
    return;
  }

  // Validate login
  const token = getTokenFromCookie();
  if (!token) {
    clearAllCoolies();
    localClear();
    next({ path: PAGE_PATH_LOGIN });
    return;
  }

  // Home page (requires login, no permission check)
  if (to.path == HOME_PAGE_NAME) {
    next();
    return;
  }

  // Load the page component corresponding to the route and modify its Name if not modified already
  let toRouterInfo = routerMap.get(to.name);
  if (toRouterInfo && lodash.isFunction(toRouterInfo.component) && toRouterInfo.meta.renameComponentFlag === false) {
    // Since the component is lazy-loaded and is a function, we can directly execute the component() function
    toRouterInfo.component().then((val) => {
      // Modify the component's name
      val.default.name = to.meta.componentName;
      // Record that the component's name has been modified
      toRouterInfo.meta.renameComponentFlag = true;
    });
  }

  // Check if cache should be refreshed
  // Refresh cache if the current route exists in the tagNav and keepAlive is not passed
  let findTag = (useUserStore().tagNav || []).find((e) => e.menuName == to.name);
  let reloadKeepAlive = findTag && !to.params.keepAlive;

  // Set tagNav
  useUserStore().setTagNav(to, from);

  // Set keepAlive or remove KeepAlive
  if (to.meta.keepAlive) {
    if (reloadKeepAlive) {
      useUserStore().deleteKeepAliveIncludes(to.meta.componentName);
    }
    nextTick(() => {
      useUserStore().pushKeepAliveIncludes(to.meta.componentName);
    });
  }
  next();
});

// ----------------------- After Route Load -----------------------
router.afterEach(() => {
  nProgress.done();
});

// ----------------------- Build Router Object -----------------------
const routerMap = new Map();

export function buildRoutes(menuRouterList) {
  let menuList = menuRouterList ? menuRouterList : useUserStore().getMenuRouterList || [];
  
  /**
   * 1. Build the entire route information
   * 2. Add it to the router
   */
  const routerList = [];
  
  // Get all vue component import paths for building routes
  const modules = import.meta.glob('../views/**/**.vue');
  
  // Get all vue components for injecting the 'name' property, which is used for keep-alive
  // ...

  // 1. Build the entire route information
  for (const e of menuList) {
    if (!e.menuId) {
      continue;
    }
    if (!e.path) {
      continue;
    }
    if (e.deletedFlag && e.deletedFlag === true) {
      continue;
    }
    let componentName = e.menuId.toString();
    if (e.component) {
      let lastIndex = e.component.lastIndexOf('/');
      let fileName = lodash.camelCase(e.component.substring(lastIndex + 1, e.component.length));
      componentName = lodash.camelCase(fileName) + componentName;
      componentName = lodash.upperFirst(componentName);
    }

    let route = {
      path: e.path.startsWith('/') ? e.path : `/${e.path}`,
      // Use the combination of 'component filename + menuId' as the unique identifier for 'name'
      name: e.menuId.toString(),
      meta: {
        // Database menu (page) id
        id: e.menuId.toString(),
        // Component name
        componentName: componentName,
        // Menu display
        title: e.menuName,
        // Menu icon display
        icon: e.icon,
        // Whether hidden in menu
        hideInMenu: !e.visibleFlag,
        // Page keep-alive caching
        keepAlive: e.cacheFlag,
        // Whether it's an external link
        frameFlag: e.frameFlag,
        // External link URL
        frameUrl: e.frameUrl,
        // Whether the component name has been renamed
        renameComponentFlag: false,
      },
    };

    if (e.frameFlag) {
      route.component = () => import('../components/framework/iframe/iframe-index.vue');
      
    } else {
      let componentPath = e.component && e.component.startsWith('/') ? e.component : '/' + e.component;
      let relativePath = `../views${componentPath}`;
      route.component = modules[relativePath];
    }
    routerList.push(route);
    routerMap.set(e.menuId.toString(), route);
  }

  // 2. Add it to the router
  router.addRoute({
    path: '/',
    meta: {},
    component: SmartLayout,
    children: routerList,
  });
}

