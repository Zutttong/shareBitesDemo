import Home from "@/pages/Home";
import Login from "@/pages/Login";
import Register from "@/pages/Register";
import Detail from "@/pages/Detail";
import User from "@/pages/User";

export default [
  {
    path: "/home",
    component: Home,
    meta: {
      isShow: true
    }
  },
  {
    path: "/login",
    component: Login,
    meta: { isShow: false, requireAuth: true },
  },
  {
    path: "/register",
    component: Register,
    meta: { isShow: false },
  },
  {
    path: "/detail/:id",
    component: Detail,
    meta: { isShow: true },
  },
  {
    path: "/user",
    component: User,
    meta: { isShow: true },
  },
  //重定向，项目跑起来的时候立马访问首页
  {
    path: "*",
    redirect: "/home",
  },
];
