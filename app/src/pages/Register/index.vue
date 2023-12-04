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
        <el-input type="text" placeholder="nickName" v-model="form.nickname" />
      </div>
      <div class="inputbox">
        <el-input type="text" placeholder="account" v-model="form.account" maxlength="11" />
      </div>
      <div class="inputbox">
        <el-input type="password" placeholder="password" v-model="form.password" />
      </div>
      <button @click="finishRegister">Complete registration</button>
      <div class="login_tip">
        <span @click="$router.push(`/login`)">go to Login</span>
      </div>
    </div>
  </div>
</template>

<script>
import requests from "@/api/ajax";
import { registerCheck } from "@/utils/InfoCheck";
import { nanoid } from "nanoid";
import { formatTime } from '@/utils/formatTime'
export default {
  data: function () {
    return {
      form: {
        account: "",
        password: "",
        nickname: ""
      },
      img: "https://www.zihuaa.com/wp-content/uploads/2018/07/152d12964fcbb6f.jpg",
    };
  },
  methods: {
    finishRegister() {
      let { form } = this;
      if (registerCheck(form)) {
        requests.post('/register', { ...form, id: nanoid(), reportedTimes: formatTime(Date.now()) }).then(res => {
          if (res.status == 0) {
            this.$message({
              message: "Registration is successful,you will be redirected to the login page",
              type: "success"
            })
            setTimeout(() => {
              this.$router.push('/login');
            }, 1500)
          } else {
            this.$message.warning(res.message);
          }
        })
      }
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
    padding: 6% 20% 0% 12%;
    box-sizing: border-box;
    width: 64%;
    float: left;

    h2 {
      width: 100%;
      font-size: 28px;
      font-weight: 400;
      margin-bottom: 60px;

      em {
        color: #005a83;
        font-weight: 600;
      }
    }

    .inputbox {
      margin-top: 20px;
      width: 100%;
      line-height: 60px;

      input {
        font-size: 16px;
        width: 100%;
        height: 45px;
        box-sizing: border-box;
        border-bottom: 1px solid #111;
        padding: 0 10px;
      }

      .el-select {
        display: block;
        margin-bottom: 20px;
        width: 100%;
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