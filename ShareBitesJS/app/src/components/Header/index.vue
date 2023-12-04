<template>
  <div class="header">
    <h1 class="logo">
      <img src="./images/logo.jpg" alt="">
      ShareBites
    </h1>
    <div class="nav-list">
      <div class="navmiddle">
        <span class="navitem" :class="{ active: index == currentIndex }" v-for="(item, index) in category" :key="item.id"
          @click="$router.push(`${item.path}`)">{{
            item.navName }}</span>
      </div>
    </div>
    <div class="navsearch">
      <div class="searchbox">
        <span class="iconfont icon-fangdajing"></span>
        <input type="text" placeholder="what">
      </div>
      <button>search</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      category: [
        {
          id: 1,
          navName: "HOME",
          path: "/home"
        },
        {
          id: 2,
          navName: "USER",
          path: "/user"
        }
      ],
      currentIndex: 0
    }
  },
  methods: {
  },
  mounted() {
  },
  watch: {
    "$route.path": {
      handler(newValue) {
        switch (newValue) {
          case '/home':
            this.currentIndex = 0;
            break;
          case '/user':
            this.currentIndex = 1;
            break;
        }
      },
      immediate: true
    }
  }
}
</script>

<style scoped lang='less'>
input:-webkit-autofill {
  -webkit-box-shadow: 0 0 0px 1000px #e2e0e0 inset
}

.active {
  border-bottom: 4px solid #ffffff;
}

.header {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 65px;
  min-width: 1000px;
  background-color: rgba(0, 0, 0, .7);
  border: none !important;
  width: 100%;

  .logo {
    display: flex;
    align-items: center;
    justify-content: flex-end;
    flex: 1 1;
    font-size: 20px;
    color: aliceblue;
    font-family: 'mFont';
    text-decoration: none;
    white-space: nowrap;

    img {
      width: 50px;
      cursor: pointer;
      height: 50px;
      margin-right: 10px;
    }
  }
}

.nav-list {
  flex-basis: 300px;
  height: 65px;
  margin-left: 50px;

  .navmiddle {
    width: 100%;
    height: 100%;
    display: flex;

    .navitem {
      cursor: pointer;
      flex-grow: 1;
      width: 10%;
      height: 100%;
      text-align: center;
      line-height: 370%;
      color: white;
      float: left;
      box-sizing: border-box;
      font-size: 20px;
      font-family: 黑体;
      font-weight: 600;
      text-decoration: none;

      &:hover {
        border-bottom: 4px solid #ffffff;
      }
    }

  }
}

.navsearch {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  flex: 1.5 1;
  height: 50px;
  float: right;
  padding: 10px 0 5px 0;
  box-sizing: border-box;

  .searchbox {
    position: relative;
    display: flex;
    width: 60%;
    height: 100%;
    float: left;

    span {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 20%;
      height: 100%;
      line-height: 40px;
      font-size: 20px;
      text-align: center;
      background: #fff;
      border-radius: 10px 0 0 10px;
    }

    input {
      float: right;
      width: 80%;
      background: #fff;
      height: 100%;
      border-radius: 0 10px 10px 0;
    }
  }

  button {
    margin-left: 10px;
    width: 60px;
    line-height: 35px;
    height: 35px;
    border-radius: 6px;
    background-color: #924c1a;
    color: #fff;
  }
}
</style>