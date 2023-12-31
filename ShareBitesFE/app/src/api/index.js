//统一管理项目前部的接口
import requests from "./ajax";

export const reqCategoryList = () =>
  requests({ url: "/product/getBaseCategoryList", method: "get" });

export const reqGetSearchInfo = (params) =>
  requests({ url: "/list", method: "post", data: params });

export const reqGoodsInfo = (skuId) =>
  requests({ url: `/item/${skuId}`, method: "get" });

export const reqAddOrUpdateShopCart = (skuId, skuNum) =>
  requests({ url: `/cart/addToCart/${skuId}/${skuNum}`, method: "post" });

export const reqCartList = () =>
  requests({ url: "/cart/cartList", method: "get" });

export const reqGetCode = (phone) =>
  requests({ url: `/user/passport/sendCode/${phone}`, method: "get" });

export const reqUserRegister = (data) =>
  requests({ url: "/user/passport/register", data, method: "post" });

export const reqUserLogin = (data) =>
  requests({ url: "/user/passport/login", data, method: "post" });

export const reqUserLogout = () =>
  requests({ url: "user/passport/logout", method: "get" });

//请求个人信息
export const reqUserInfo = () =>
  requests({ url: "/user/passport/auth/getUserInfo", method: "get" });
