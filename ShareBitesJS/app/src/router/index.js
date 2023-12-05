import Vue from "vue";
import VueRouter from "vue-router";
import routes from "./routes";
import {
  storeUserInfo,
  removeToken,
} from "@/utils/token";

Vue.use(VueRouter);

let originPush = VueRouter.prototype.push;
let originReplace = VueRouter.prototype.replace;

VueRouter.prototype.push = function (location, resolve, reject) {
  if (resolve && reject) {
    originPush.call(this, location, resolve, reject);
  } else {
    originPush.call(
      this,
      location,
      () => { },
      () => { }
    );
  }
};
VueRouter.prototype.replace = function (location, resolve, reject) {
  if (resolve && reject) {
    originReplace.call(this, location, resolve, reject);
  } else {
    originReplace.call(
      this,
      location,
      () => { },
      () => { }
    );
  }
};

let router = new VueRouter({
  routes,
  scrollBehavior(to, from, savedPosition) {
    return { y: 0 };
  },
});

router.beforeEach((to, from, next) => {
  /*   var userInfo = storeUserInfo();
    var time = new Date();
    if (userInfo.exp * 1000 < time.getTime()) {
      removeToken();
    }
    if (to.meta.requireAuth) {
      if (userInfo.phone) next("/home");
    } */
  next();
});

export default router;
