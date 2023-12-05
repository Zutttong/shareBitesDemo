import Vue from "vue";
import App from "./App.vue";
//注册三级联动组件--全局组件
import router from "@/router";
Vue.config.productionTip = false;

import ElementUI from "element-ui";
import { Message, Loading } from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
Vue.use(ElementUI);

new Vue({
  render: (h) => h(App),
  beforeCreate() {
    Vue.prototype.$bus = this;
    Vue.prototype.$message = Message;
    Vue.prototype.$loding = Loading;
  },
  router: router,
}).$mount("#app");
