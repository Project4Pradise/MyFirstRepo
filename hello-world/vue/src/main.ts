import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/gloable.css'

// @ts-ignore
import request from "@/utils/request.js";

// @ts-ignore
import store from './store'




Vue.config.productionTip = false

Vue.use(ElementUI, { size: "mini" });
Vue.prototype.request=request


// @ts-ignore
new Vue({
  router,

  render: h => h(App)
}).$mount('#app')