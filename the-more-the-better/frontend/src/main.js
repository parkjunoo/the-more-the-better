import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import naver from 'vue-naver-maps';
import axios from 'axios'
Vue.prototype.$axios = axios; 
Vue.use(BootstrapVue)
Vue.use(Vuetify);
Vue.config.productionTip = false
Vue.use(naver, {
  clientID: '8c5g8vyz8e',
  useGovAPI: true, //공공 클라우드 API 사용 (선택)
  subModules:'' // 서브모듈 (선택)
});
new Vue({
  vuetify: new Vuetify(),
  router,
  store,
  render: h => h(App)
}).$mount('#app')
;
