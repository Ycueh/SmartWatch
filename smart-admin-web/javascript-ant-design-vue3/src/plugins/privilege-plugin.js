//permission plugin
import { useUserStore } from '/@/store/modules/system/user';

const privilege = (value) => {
  // super administrator
  if (useUserStore().administratorFlag) {
    return true;
  }
  // Obtain function point permissions
  let userPointsList = useUserStore().getPointList;
  if (!userPointsList) {
    return false;
  }
  return userPointsList && userPointsList.includes(value);
};

export default {
  install: (app) => {
    app.config.globalProperties.$privilege = privilege;
  },
};
