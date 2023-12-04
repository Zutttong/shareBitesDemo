const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  //代理跨域
  devServer: {
    proxy: {
      "/api": {
        target: "http://127.0.0.1:3007/api",
        pathRewrite: { "^/api": "" },
      },
      "/uploadImage": {
        target: "https://sm.ms/api/v2/",
        pathRewrite: { "^/uploadImage": "" },
      }
    },
  },
});
