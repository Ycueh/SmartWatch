import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import locale from 'element-ui/lib/locale/lang/en';
import axios from "axios";
Vue.config.productionTip = false
Vue.prototype.$axios = axios;
Vue.prototype.$httpUrl = 'http://localhost:8080'
Vue.use(ElementUI, { locale });

new Vue({
  render: h => h(App),
}).$mount('#app')
