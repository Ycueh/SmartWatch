<!--
  * setting component
-->

<template>
  <a-drawer :title="$t('setting.title')" placement="right" :visible="visible" @close="close">
    <a-form layout="horizontal" :label-col="{ span: 8 }">
      <a-form-item label="Language">
        <a-select v-model:value="formState.language" @change="changeLanguage" style="width: 120px">
          <a-select-option v-for="item in i18nList" :key="item.value" :value="item.value">{{ item.text }}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item :label="$t('setting.menu.layout')">
        <a-radio-group @change="changeLayout" button-style="solid" v-model:value="formState.layout">
          <a-radio-button v-for="item in $smartEnumPlugin.getValueDescList('LAYOUT_ENUM')" :key="item.value" :value="item.value">
            {{ item.desc }}
          </a-radio-button>
        </a-radio-group>
      </a-form-item>
      <a-form-item :label="$t('setting.menu.width')" v-if="formState.layout === LAYOUT_ENUM.SIDE.value">
        <a-input-number @change="changeSideMenuWidth" v-model:value="formState.sideMenuWidth" :min="1" />
        （px）
      </a-form-item>
      <a-form-item :label="$t('setting.menu.theme')">
        <a-radio-group v-model:value="formState.sideMenuTheme" button-style="solid" @change="changeMenuTheme">
          <a-radio-button value="dark">Dark</a-radio-button>
          <a-radio-button value="light">Light</a-radio-button>
        </a-radio-group>
      </a-form-item>
      <a-form-item :label="$t('setting.bread')">
        <a-switch @change="changeBreadCrumbFlag" v-model:checked="formState.breadCrumbFlag" checked-children="show" un-checked-children="hide" />
      </a-form-item>
      <a-form-item :label="$t('setting.pagetag')">
        <a-switch @change="changePageTagFlag" v-model:checked="formState.pageTagFlag" checked-children="show" un-checked-children="hide" />
      </a-form-item>
      <a-form-item :label="$t('setting.footer')">
        <a-switch @change="changeFooterFlag" v-model:checked="formState.footerFlag" checked-children="show" un-checked-children="hide" />
      </a-form-item>
      <a-form-item :label="$t('setting.helpdoc')">
        <a-switch @change="changeHelpDocFlag" v-model:checked="formState.helpDocFlag" checked-children="show" un-checked-children="hide" />
      </a-form-item>
    </a-form>
    <div class="footer">
      <a-button style="margin-right: 8px" type="primary" @click="copy">Copy configuration</a-button>
      <a-button type="block" danger @click="reset">Restore configuration </a-button>
    </div>
  </a-drawer>
</template>
<script setup>
  import { ref, reactive, h } from 'vue';
  import { i18nList } from '/@/i18n/index';
  import { useI18n } from 'vue-i18n';
  import localStorageKeyConst from '/@/constants/local-storage-key-const';
  import { LAYOUT_ENUM } from '/@/constants/layout-const';
  import { localRead, localSave } from '/@/utils/local-util';
  import { useAppConfigStore } from '/@/store/modules/system/app-config';
  import { Modal } from 'ant-design-vue';
  import { appDefaultConfig } from '/@/config/app-config';

  // ----------------- modal show and hide -----------------

  const visible = ref(false);
  defineExpose({
    show,
  });

  function close() {
    visible.value = false;
  }

  function show() {
    visible.value = true;
  }

  // ----------------- Configuration Information Operation -----------------
  function copy() {
    let content = JSON.stringify(formState, null, 2);
    // Create elements for copying
    const aux = document.createElement('input');
    // set element content
    aux.setAttribute('value', content);
    // Insert the element into the page to call
    document.body.appendChild(aux);
    // copy content
    aux.select();
    // copy content to clipboard
    document.execCommand('copy');
    // delete element
    document.body.removeChild(aux);

    Modal.success({
      title: 'copy successfully',
      content: h('div', {}, [h('p', 'can modify directly /src/config/app-config.js to save the config')]),
    });
  }

  function reset() {
    for (const k in appDefaultConfig) {
      formState[k] = appDefaultConfig[k];
    }
    appConfigStore.reset();
  }

  // ----------------- save form data to localstorage -----------------

  const appConfigStore = useAppConfigStore();
  useAppConfigStore().$subscribe((mutation, state) => {
    localSave(localStorageKeyConst.APP_CONFIG, JSON.stringify(state));
  });

  // ----------------- form -----------------

  let formValue = {
    // i18n language selection
    language: appConfigStore.language,
    // layout: side or side-expand
    layout: appConfigStore.layout,
    // side menu width
    sideMenuWidth: appConfigStore.sideMenuWidth,
    // menu theme
    sideMenuTheme: appConfigStore.sideMenuTheme,
    // page tag
    pageTagFlag: appConfigStore.pageTagFlag,
    // bread crumb
    breadCrumbFlag: appConfigStore.breadCrumbFlag,
    // footer
    footerFlag: appConfigStore.footerFlag,
    // help doc
    helpDocFlag: appConfigStore.helpDocFlag,
  };

  let formState = reactive({ ...formValue });

  const { locale } = useI18n();
  function changeLanguage(languageValue) {
    locale.value = languageValue;
    appConfigStore.$patch({
      language: languageValue,
    });
  }

  function changeLayout(e) {
    appConfigStore.$patch({
      layout: e.target.value,
    });
  }

  function changeSideMenuWidth(value) {
    appConfigStore.$patch({
      sideMenuWidth: value,
    });
  }
  function changeMenuTheme(e) {
    appConfigStore.$patch({
      sideMenuTheme: e.target.value,
    });
  }

  function changeBreadCrumbFlag(e) {
    appConfigStore.$patch({
      breadCrumbFlag: e,
    });
  }

  function changePageTagFlag(e) {
    appConfigStore.$patch({
      pageTagFlag: e,
    });
  }

  function changeFooterFlag(e) {
    appConfigStore.$patch({
      footerFlag: e,
    });
  }

  function changeHelpDocFlag(e) {
    appConfigStore.$patch({
      helpDocFlag: e,
    });
  }
</script>
<style lang="less" scoped>
  .footer {
    position: absolute;
    right: 0;
    bottom: 0;
    width: 100%;
    border-top: 1px solid #e9e9e9;
    padding: 10px 16px;
    background: #fff;
    text-align: left;
    z-index: 1;
  }
</style>
