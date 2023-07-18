/*
 * Internationalization entry file
 */

import en_US from './lang/en-US/index';
import zh_CN from './lang/zh-CN/index';
import { createI18n } from 'vue-i18n';
import { getInitializedLanguage } from '/@/store/modules/system/app-config';

// language selection array
export const i18nList = [
  {
    text: '简体中文',
    value: 'zh_CN',
  },
  {
    text: 'English',
    value: 'en_US',
  },
];

export const messages = {
  en_US: en_US,
  zh_CN: zh_CN,

};

const i18n = createI18n({
  fallbackLocale: 'en_US', //default locale
  globalInjection: true,
  legacy: false, //
  locale: getInitializedLanguage(), //default-initialized language
  messages, //

});

export default i18n;
