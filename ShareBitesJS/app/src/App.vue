<template>
  <div>
    <Header v-if="$route.meta.isShow"></Header>
    <router-view></router-view>
  </div>
</template>

<script>
import Header from "./components/Header";
var timer = null;
export default {
  name: "App",
  components: {
    Header
  },
};
</script>

<style>
:root {
  --themeColor--: #005a83
}
</style>
