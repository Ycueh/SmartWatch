/*
 * common constants
 */

export const PAGE_SIZE = 10;

export const PAGE_SIZE_OPTIONS = ['10', '15', '20', '30', '40', '50', '75', '100', '150', '200', '300', '500'];

//login page name
export const PAGE_PATH_LOGIN = '/login';

//404 page name
export const PAGE_PATH_404 = '/404';

export const showTableTotal = function (total) {
  return `A total of ${total}`;
};

export const FLAG_NUMBER_ENUM = {
  TRUE: {
    value: 1,
    desc: 'Yes',
  },
  FALSE: {
    value: 0,
    desc: 'No',
  },
};


export const USER_TYPE_ENUM = {
  ADMIN_EMPLOYEE: {
    value: 1,
    desc: 'employee',
  },
  
};

