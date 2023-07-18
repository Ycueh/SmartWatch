/*
 * menu type
 */
export const MENU_TYPE_ENUM = {
  CATALOG: {
    value: 1,
    desc: 'catalog',
  },
  MENU: {
    value: 2,
    desc: 'menu',
  },
  POINTS: {
    value: 3,
    desc: 'button',
  },
};

/**
 * permission type
 */
export const MENU_PERMS_TYPE_ENUM = {
  SPRING_SECURITY: {
    value: 1,
    desc: 'Security mode',
  },
  URL: {
    value: 2,
    desc: 'URL node',
  },
};

/**
 * default top menu's id is 0
 */
export const MENU_DEFAULT_PARENT_ID = 0;

export default {
  MENU_TYPE_ENUM,
  MENU_PERMS_TYPE_ENUM
};
