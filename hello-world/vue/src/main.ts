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
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
//use
Vue.use(mavonEditor)



Vue.config.productionTip = false

Vue.use(ElementUI, { size: "mini" });
Vue.prototype.request=request


// @ts-ignore
new Vue({
  router,
  data(){
    return{
      user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},

    }
  },
  created() {
    // 添加全局 beforeunload 事件监听器
    window.addEventListener('beforeunload', this.handleBeforeUnload);
  },
  methods:{

    handleBeforeUnload(event){
      this.request.post("/user/logout", this.user).then(res => {
        localStorage.removeItem("user")
        this.$message.success("退出成功")
      })
    }
  },
  render: h => h(App)
}).$mount('#app')