//log in page
export const loginRouters = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('/@/views/system/login/login.vue'),
    meta: {
      title: 'log in',
      hideInMenu: true,
    },
  },
];
