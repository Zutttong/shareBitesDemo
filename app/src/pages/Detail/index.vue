<template>
  <div>
    <div class="top">
      <span @click="$router.push(`/home`)">HOME</span>
      <span>/</span>
      <span>{{ Detail.name }}</span>
    </div>
    <div class="title">{{ Detail.name }}</div>
    <div class="paintmiddle">
      <div class="middleimg">
        <img :src="Detail.image" alt="">
      </div>
      <div class="middleright">
        <h2>Food information</h2>
        <div class="righttext">name：{{ Detail.name }}</div>
        <div class="righttext" style="cursor: pointer;" href="#author">Publisher：{{ Detail.owner }}</div>
        <div class="righttext">Date：{{ Detail.date }}</div>
      </div>
      <button class="want" @click="handleWant" v-if="Detail.status == 'listing'">want</button>
      <button class="want" @click="handleWant" v-else>Have sold</button>
    </div>

    <div id="author">
      <h3>Publisher Information</h3>
      <div class="middle">
        <div class="middleleft">
          <div class="avatarUrl">
            <img :src="img" alt="">
          </div>
          <div class="middletext">
            <span class="texthead">nickName:</span>
            <span>{{ form.nickname }}</span>
          </div>
          <div class="middletext">
            <span class="texthead">Positive rating:</span>
            <span>{{ form.rate }}</span>
          </div>

        </div>
      </div>
    </div>
    <Discuss></Discuss>
  </div>
</template>

<script>
import requests from "@/api/ajax";
import { getToken } from "@/utils/token";
import Discuss from '@/components/Discuss/index.vue'
import formatTime from '@/utils/formatTime';
export default {
  data() {
    return {
      form: {
        nickname: "hals"
      },
      Detail: {
        image: "",
        name: "",
        date: ""
      },
      img:
        "https://ts1.cn.mm.bing.net/th/id/R-C.67649a22b0cb0dc061ff6d01ae7d0fa0?rik=KXcrLH1EArLL4Q&riu=http%3a%2f%2fguangyuanol.cn%2fuploads%2fallimg%2f220927%2f23230aM3-10.jpg&ehk=lOvFsB5EyLU4OknbfzJOo6gMzZ%2f2ozpTcxDD09O0PH8%3d&risl=&pid=ImgRaw&r=0",
      token: getToken(),
      id: this.$route.params.id,
      isbid: false,
    };
  },
  components: { Discuss },
  methods: {
    getDetail() {
      requests({
        url: `get/detail/food?id=${this.id}`
      }).then(res => {
        res.data.date = formatTime(res.data.post_time)
        this.Detail = res.data;
      })
    },
    getPulisher() {
      requests(`/getUser?id=${this.token.id}`).then(res => {
        this.form = { ...res.data, rate: '99%' };
      })
    },
    handleWant() {
      if (!confirm(`Are you sure you want this food`)) return;
      requests.put(`/update/food/status?status=delist&id=${this.id}`).then(res => {
        if (res.status == 0) {
          this.$message.success('Successful purchase');
          this.getDetail();
        }
      })
    }
  },
  mounted() {
    this.getDetail();
    this.getPulisher();
  },
  watch: {
    "$route.params": {
      handler() {
        this.id = this.$route.params.id;
        this.getDetail();
      },
      deep: true
    }
  }
};
</script>

<style scoped lang="less">
.avatarUrl {
  display: flex;

  img {
    margin: auto;
    height: 80px;
    width: 80px;
    box-shadow: 0 0 5px rgba(0, 0, 0, .3);
    border-radius: 50%;
  }
}

.top {
  display: flex;
  width: 70%;
  line-height: 30px;
  min-width: 1000px;
  margin: auto;
  margin-bottom: 20px;
  border-bottom: 1px solid rgb(245, 245, 245);

  span {
    cursor: pointer;

    &:hover {
      text-decoration: underline;
    }
  }

  a {
    text-decoration: none;
    color: rgb(70, 66, 66);
    font-size: 12px;

    &:hover {
      text-decoration: underline;
      font-weight: 600;
    }
  }
}

.title {
  width: 70%;
  height: 100px;
  min-width: 1000px;
  line-height: 100%;
  margin: 0 auto;
  margin-bottom: 20px;
  font-size: 35px;
  font-weight: 700;
  font-family: 黑体;
  box-sizing: border-box;
  padding-top: 30px;
  border-bottom: 1px solid rgb(245, 245, 245);
}

.paintmiddle {
  width: 70%;
  min-width: 1000px;
  margin: 0 auto;
  overflow: hidden;

  .middleimg {
    width: 50%;
    float: left;

    img {
      width: 100%;
    }
  }

  .middleright {
    width: 50%;
    float: left;
    box-sizing: border-box;
    padding: 0 3%;
    border-bottom: 1px solid rgb(245, 245, 245);
    margin-bottom: 30px;

    h2 {
      margin-bottom: 20px;
    }

    .righttext {
      position: relative;
      font-size: 16px;
      margin-bottom: 20px;


      a {
        position: absolute;
        z-index: 100;
        display: inline-block;
        cursor: pointer;
        color: var(--themeColor--);
      }
    }
  }
}

.want {
  height: 50px;
  line-height: 50px;
  width: 200px;
  text-align: center;
  background: var(--themeColor--);
  color: #FFF;
  font-weight: 600;
  margin: 50px;
  font-size: 18px;
  border-radius: 5px;
}

#author {
  position: fixed;
  right: 5px;
  top: 200px;
  border: 1px solid #ddd;
  width: 200px;
  height: 200px;
  box-shadow: 0 0 10px rgba(0, 0, 0, .3);
  background-color: #ffd;

  h3 {
    height: 40px;
    line-height: 40px;
    text-align: center;
  }

  .middle {
    display: flex;

    .middleleft {
      margin: auto;

      h2 {
        margin-bottom: 10px;
      }

      .middletext {
        font-size: 16px;
        margin-bottom: 20px;

        .texthead {
          font-weight: 600;
          margin-right: 10px;
        }
      }
    }
  }
}
</style>