//login user
import _ from 'lodash';
import { defineStore } from 'pinia';
import localKey from '/@/constants/local-storage-key-const';
import { HOME_PAGE_NAME } from '/@/constants/system/home-const';
import { MENU_TYPE_ENUM } from '/@/constants/system/menu-const';
import { getTokenFromCookie } from '/@/utils/cookie-util';
import { localClear, localRead, localSave } from '/@/utils/local-util';

export const useUserStore = defineStore({
  id: 'userStore',
  state: () => ({
    token: '',
    //user Id
    userId: '',
    //Account
    loginName: '',
    //Actual name
    actualName: '',
    //Phone number
    phone: '',
    //Admin flag
    administratorFlag: true,
    //Last Login ip
    lastLoginIp: '',
    //Last Login user agent
    lastLoginUserAgent: '',
    //Last login time
    lastLoginTime: '',
    //Menu
    menuTree: [],
    //Menu Router List
    menuRouterList: [],
    //Menu router init
    menuRouterInitFlag: false,
    //Menu parentId
    menuParentIdListMap: new Map(),
    pointsList: [],
    tagNav: [],
    keepAliveIncludes: [],
  }),
  getters: {
    getToken(state) {
      if (state.token) {
        return state.token;
      }
      return getTokenFromCookie();
    },
    // Whether the menu router has been initialized
    getMenuRouterInitFlag(state) {
      return state.menuRouterInitFlag;
    },
    // Menu tree
    getMenuTree(state) {
      return state.menuTree;
    },
    // Menu routers
    getMenuRouterList(state) {
      return state.menuRouterList;
    },
    // Menu parent IDs
    getMenuParentIdListMap(state) {
      return state.menuParentIdListMap;
    },
    // Permission points
    getPointList(state) {
      if (_.isEmpty(state.pointsList)) {
        let localUserPoints = localRead(localKey.USER_POINTS) || '';
        state.pointsList = localUserPoints ? JSON.parse(localUserPoints) : [];
      }
      return state.pointsList;
    },
    // Tag navigation
    getTagNav(state) {
      if (_.isEmpty(state.tagNav)) {
        let localTagNav = localRead(localKey.USER_TAG_NAV) || '';
        state.tagNav = localTagNav ? JSON.parse(localTagNav) : [];
      }
      let tagNavList = _.cloneDeep(state.tagNav) || [];
      tagNavList.unshift({
        menuName: HOME_PAGE_NAME,
        menuTitle: 'Home',
      });
      return tagNavList;
    },
  },

  actions: {
    logout() {
      this.token = '';
      this.menuList = [];
      this.tagNav = [];
      this.userInfo = {};
      localClear();
    },
    // Set user login information
    setUserLoginInfo(data) {
      // User basic information
      this.token = data.token;
      this.userId = data.userId;
      this.loginName = data.loginName;
      this.actualName = data.actualName;
      this.phone = data.phone;
      this.departmentId = data.departmentId;
      this.departmentName = data.departmentName;
      this.administratorFlag = data.administratorFlag;
      this.lastLoginIp = data.lastLoginIp;
      this.lastLoginUserAgent = data.lastLoginUserAgent;
      this.lastLoginTime = data.lastLoginTime;

      // Menu permissions
      this.menuTree = buildMenuTree(data.menuList);

      // Routers with menus
      this.menuRouterList = data.menuList.filter((e) => e.path || e.frameUrl);

      // Parent menu collection
      this.menuParentIdListMap = buildMenuParentIdListMap(this.menuTree);

      // Permission points
      this.pointsList = data.menuList.filter((menu) => menu.menuType === MENU_TYPE_ENUM.POINTS.value && menu.visibleFlag && !menu.disabledFlag);
    },
    setToken(token) {
      this.token = token;
    },
    // Set tag navigation
    setTagNav(route, from) {
      if (_.isEmpty(this.getTagNav)) this.tagNav = [];
      // Unique identifier 'name'
      let name = route.name;
      if (!name || name == HOME_PAGE_NAME) {
        return;
      }
      let findTag = (this.tagNav || []).find((e) => e.menuName == name);
      if (findTag) {
        // @ts-ignore
        findTag.fromMenuName = from.name;
        findTag.fromMenuQuery = from.query;
      } else {
        // @ts-ignore
        this.tagNav.push({
          // @ts-ignore
          menuName: name,
          // @ts-ignore
          menuTitle: route.meta.title,
          menuQuery: route.query,
          // @ts-ignore
          fromMenuName: from.name,
          fromMenuQuery: from.query,
        });
      }
      localSave(localKey.USER_TAG_NAV, JSON.stringify(this.tagNav));
    },
    // Close tag navigation
    closeTagNav(menuName, closeAll) {
      if (_.isEmpty(this.getTagNav)) return;
      if (closeAll && !menuName) {
        this.tagNav = [];
        this.clearKeepAliveIncludes();
      } else {
        let findIndex = (this.tagNav || []).findIndex((e) => e.menuName == menuName);
        if (closeAll) {
          if (findIndex == -1) {
            this.tagNav = [];
            this.clearKeepAliveIncludes();
          } else {
            let tagNavElement = (this.tagNav || [])[findIndex];
            this.tagNav = [tagNavElement];
            this.clearKeepAliveIncludes(tagNavElement.menuName);
          }
        } else {
          (this.tagNav || []).splice(findIndex, 1);
          this.deleteKeepAliveIncludes(menuName);
        }
      }
      localSave(localKey.USER_TAG_NAV, JSON.stringify(this.tagNav));
    },
    // Close page
    closePage(route, router, path) {
      if (!this.getTagNav || _.isEmpty(this.getTagNav)) return;
      if (path) {
        router.push({ path });
      } else {
        // Find tagNav
        let index = this.getTagNav.findIndex((e) => e.menuName == route.name);
        if (index == -1) {
          router.push({ name: HOME_PAGE_NAME });
        } else {
          let tagNav = this.getTagNav[index];
          if (tagNav.fromMenuName && this.getTagNav.some((e) => e.menuName == tagNav.fromMenuName)) {
            router.push({ name: tagNav.fromMenuName, query: tagNav.fromMenuQuery });
          } else {
            // Query left tag
            let leftTagNav = this.getTagNav[index - 1];
            router.push({ name: leftTagNav.menuName, query: leftTagNav.menuQuery });
          }
        }
      }
      this.closeTagNav(route.name, false);
    },
    // Add to cache
    pushKeepAliveIncludes(val) {
      if (!val) {
        return;
      }
      if (!this.keepAliveIncludes) {
        this.keepAliveIncludes = [];
      }
      if (this.keepAliveIncludes.length < 30) {
        let number = this.keepAliveIncludes.findIndex((e) => e === val);
        if (number === -1) {
          this.keepAliveIncludes.push(val);
        }
      }
    },
    // Delete from cache
    deleteKeepAliveIncludes(val) {
      if (!this.keepAliveIncludes || !val) {
        return;
      }
      let number = this.keepAliveIncludes.findIndex((e) => e === val);
      if (number !== -1) {
        this.keepAliveIncludes.splice(number, 1);
      }
    },
    // Clear cache
    clearKeepAliveIncludes(val) {
      if (!val || !this.keepAliveIncludes.includes(val)) {
        this.keepAliveIncludes = [];
        return;
      }
      this.keepAliveIncludes = [val];
    },
  },
});

/**
 * Build menu parent ID collection
 */
function buildMenuParentIdListMap(menuTree) {
  let menuParentIdListMap = new Map();
  recursiveBuildMenuParentIdListMap(menuTree, [], menuParentIdListMap);
  return menuParentIdListMap;
}

function recursiveBuildMenuParentIdListMap(menuList, parentMenuList, menuParentIdListMap) {
  for (const e of menuList) {
    // Clear top-level parentMenuList
    if (e.parentId == 0) {
      parentMenuList = [];
    }
    let menuIdStr = e.menuId.toString();
    let cloneParentMenuList = _.cloneDeep(parentMenuList);
    if (!_.isEmpty(e.children) && e.menuName) {
      // Recursion
      cloneParentMenuList.push({ name: menuIdStr, title: e.menuName });
      recursiveBuildMenuParentIdListMap(e.children, cloneParentMenuList, menuParentIdListMap);
    } else {
      menuParentIdListMap.set(menuIdStr, cloneParentMenuList);
    }
  }
}

/**
 * Build menu tree
 *
 * @param  menuList
 * @returns
 */
function buildMenuTree(menuList) {
  // 1 Get all valid directories and menus
  let catalogAndMenuList = menuList.filter((menu) => menu.menuType !== MENU_TYPE_ENUM.POINTS.value && menu.visibleFlag && !menu.disabledFlag);

  // 2 Get top-level directories
  let topCatalogList = catalogAndMenuList.filter((menu) => menu.parentId === 0);
  for (const topCatalog of topCatalogList) {
    buildMenuChildren(topCatalog, catalogAndMenuList);
  }
  return topCatalogList;
}

function buildMenuChildren(menu, allMenuList) {
  let children = allMenuList.filter((e) => e.parentId === menu.menuId);
  if (children.length === 0) {
    return;
  }
  menu.children = children;
  for (const item of children) {
    buildMenuChildren(item, allMenuList);
  }
}
