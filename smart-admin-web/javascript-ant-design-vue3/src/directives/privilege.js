/*
 * privilege
 */

import { useUserStore } from '/@/store/modules/system/user';
import lodash from 'lodash';

export function privilegeDirective(el, binding) {
  // super administrator
  if (useUserStore().administratorFlag) {
    return true;
  }
  // get function point permissions
  let userPointsList = useUserStore().getPointList;
  if (!userPointsList) {
    return false;
  }
  // delete node
  if (!lodash.some(userPointsList,['webPerms',binding.value])) {
    el.parentNode.removeChild(el);
  }
  return true;
}
