//enumerate plugins
import _ from 'lodash';
import { FLAG_NUMBER_ENUM } from '/@/constants/common-const';

export default {
  install: (app, smartEnumWrapper) => {
    const smartEnumPlugin = {};
    /**
     * Get description based on enumeration value
     * @param {*} constantName enum name
     * @param {*} value          enumeration value
     * @returns
     */
    smartEnumPlugin.getDescByValue = function (constantName, value) {
      if (!smartEnumWrapper || !Object.prototype.hasOwnProperty.call(smartEnumWrapper, constantName)) {
        return '';
      }
      // The boolean type requires special handling
      if (constantName === 'FLAG_NUMBER_ENUM' && !_.isUndefined(value) && typeof value === 'boolean') {
        value = value ? FLAG_NUMBER_ENUM.TRUE.value : FLAG_NUMBER_ENUM.FALSE.value;
      }

      let smartEnum = smartEnumWrapper[constantName];
      for (let item in smartEnum) {
        if (smartEnum[item].value === value) {
          return smartEnum[item].desc;
        }
      }
      return '';
    };
    /**
     * Obtain the corresponding description key-value pair [{value:desc}] according to the enumeration name
     * @param {*} constantName enum name
     * @returns
     */
    smartEnumPlugin.getValueDescList = function (constantName) {
      if (!Object.prototype.hasOwnProperty.call(smartEnumWrapper, constantName)) {
        return [];
      }
      const result = [];
      let targetSmartEnum = smartEnumWrapper[constantName];
      for (let item in targetSmartEnum) {
        result.push(targetSmartEnum[item]);
      }
      return result;
    };

    /**
     * Obtain the corresponding value description key-value pair {value:desc} according to the enumeration name
     * @param {*} constantName enum name
     * @returns
     */
    smartEnumPlugin.getValueDesc = function (constantName) {
      if (!Object.prototype.hasOwnProperty.call(smartEnumWrapper, constantName)) {
        return {};
      }
      let smartEnum = smartEnumWrapper[constantName];
      let result = {};
      for (let item in smartEnum) {
        let key = smartEnum[item].value + '';
        result[key] = smartEnum[item].desc;
      }
      return result;
    };

    app.config.globalProperties.$smartEnumPlugin = smartEnumPlugin;
    app.provide('smartEnumPlugin', smartEnumPlugin);
  },
};
