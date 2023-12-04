<template>
  <div class="layout">
    <div class="Img">
      <img :src="img" alt="">
    </div>
    <div class="right_part">
      <h2 class="title">Welcome to
        <em>ShareBites</em>
        , I'm glad you're here
      </h2>
      <div class="inputbox">
        <input type="text" v-model="form.account" placeholder="account" maxlength="11">
      </div>
      <div class="inputbox">
        <input type="password" v-model="form.password" placeholder="password">
      </div>
      <button @click="handleLogin">Log in</button>
      <div class="login_tip">
        <span @click="$router.push(`/register`)">go to register</span>
      </div>
    </div>
  </div>
</template>

<script>
import requests from '@/api/ajax';
import { setToken } from '@/utils/token';
export default {
  data: function () {
    return {
      form: {
        account: "",
        password: ""
      },
      img: "https://ts1.cn.mm.bing.net/th/id/R-C.6e6452fc5e2b9e285664ae519adf3305?rik=EbhSb6c4E0KQQA&riu=http%3a%2f%2fimg.mp.itc.cn%2fupload%2f20160424%2f9ddd4a39921b4ba1b5df194e2f24115b_th.jpg&ehk=Mn5V9DBjnzTtR9K7W6OT0928D12aiOhq5bZsfP3rAjw%3d&risl=&pid=ImgRaw&r=0"
    };
  },
  methods: {
    handleLogin() {
      var { account, password } = this.form;
      if (!account || !password) {
        this.$message({
          message: "The put cannot be empty",
          type: "warning"
        })
        return;
      }
      requests.post('/login', this.form).then(res => {
        if (res.status === 0) {
          this.$message({
            message: res.message,
            duration: 1500,
            type: 'success'
          })
          setToken(res.data);
          this.$router.replace("/home")
        }
        else {
          this.$message({
            message: res.message,
            type: 'error',
            duration: 2000
          })
        }
      })
    }
  },
};
</script>

<style scoped lang="less">
.layout {
  width: 100%;
  height: calc(100vh - 20px);
  min-width: 1000px;

  .Img {
    width: 36%;
    height: 100vh;
    float: left;
    overflow: hidden;

    img {
      width: 100%;
      height: 100%;
    }
  }

  .right_part {
    padding: 10% 20% 0 10%;
    box-sizing: border-box;
    width: 64%;
    float: left;

    h2 {
      width: 100%;
      font-size: 28px;
      font-weight: 400;

      em {
        color: #005a83;
        font-weight: 600;
      }
    }

    .inputbox {
      margin-top: 40px;
      width: 100%;
      height: 60px;
      line-height: 60px;

      input {
        font-size: 16px;
        width: 100%;
        height: 45px;
        box-sizing: border-box;
        border-bottom: 1px solid #111;
        padding: 0 10px;
      }
    }

    button {
      margin: 40px 0;
      width: 100%;
      height: 50px;
      font-size: 18px;
      border-radius: 20px;
      line-height: 50px;
      text-align: center;
      background-color: #005a83;
      color: #fff;
    }

    .login_tip {
      width: 100%;
      display: flex;

      span {
        cursor: pointer;
        flex: 1;
        text-decoration: underline;
        padding: 0 10px;
        color: #333232;
        font-weight: 600;

        &:last-child {
          text-align: right;
        }
      }
    }
  }
}
</style>