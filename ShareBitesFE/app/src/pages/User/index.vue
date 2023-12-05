<template>
  <div class="body_container">
    <div class="title">Individual center</div>
    <div class="middle">
      <div class="middleleft">
        <div class="avatarUrl">
          <img :src="img" alt="">
        </div>
        <h2>user {{ form.nickname }}，hello！</h2>
        <div class="withdraw"> Change account<a href="javascript:void(0);" @click="exitUser">Exit</a></div>
        <div class="middletext">
          <span class="texthead">nickName:</span>
          <span>{{ form.nickname }}</span>
        </div>
        <div class="middletext">
          <span class="texthead">account:</span>
          <span>{{ form.account }}</span>
        </div>
        <button class="shelf" @click="edit = true">Shelf goods</button>
      </div>
    </div>
    <div class="bottom">
      <div class="bottomtitle">
        <div class="titlepro" :class="{ active: isLike }" @click="handleGoods">My goods</div>
        <div class="titlepro" :class="{ active: !isLike }" @click="handleHistory">Inventory history</div>
      </div>
      <div class="collectzero" v-if="Goods.length == 0 && isLike">
        <div class="zeroimg">
          <img src="https://static.web.ikea.cn/static/images/search/alien1.svg" alt="">
        </div>
        <div class="zerotext">You don't have any merchandise yet</div>
        <div class="zerobutton" href>
          <a href="javascript:void(0);" @click="$router.push('/home')">Go and stroll</a>
        </div>
      </div>
      <div class="collectzero" v-if="History.length == 0 && !isLike">
        <div class="zeroimg">
          <img src="https://static.web.ikea.cn/static/images/search/alien1.svg" alt="">
        </div>
        <div class="zerotext">You don't have an inventory history</div>
        <div class="zerobutton" href>
          <a href="javascript:void(0);" @click="$router.push('/home')">Go and stroll</a>
        </div>
      </div>

      <template v-if="Goods.length > 0 && isLike">
        <ul class="list">
          <li v-for="item in Goods" :key="item.id">
            <img :src="item.image" alt="" @click="handleDetail(item.id)">
            <div class="info">
              <div class="name">name:{{ item.name }}</div>
              <div class="date">date:{{ item.date }}</div>
              <el-button @click="handleDelist(item.id)">End Listing</el-button>
            </div>
          </li>
        </ul>
      </template>

      <template v-if="History.length > 0 && !isLike">
        <ul class="history">
          <li v-for="item in History" :key="item.id">
            <img :src="item.image" alt="">
            <div class="info">
              <div class="name">name:{{ item.name }}</div>
              <div class="date">date:{{ item.date }}</div>
            </div>
          </li>
        </ul>
      </template>
    </div>
    <div class="shelf_Container" v-show="edit">
      <label for="">name:</label>
      <el-input type="text" placeholder="goods name" v-model="name"></el-input>
      <label for="">picture:</label>
      <div class="photo_container">
        <div class="add_photo">
          <input type="file" accept="image/*" @change="uploadFile" />
          +
        </div>
        <img :src="image" v-if="image" alt="">
      </div>
      <div class="buttons">
        <el-button type="primary" @click="uploadFood">Confirm</el-button>
        <el-button type="danger" @click="edit = false">Cancel</el-button>
      </div>

    </div>
  </div>
</template>

<script>
import { removeToken, getToken } from "@/utils/token";
import requests from "@/api/ajax";
import { getImage } from '@/utils/media.js'
import { nanoid } from "nanoid";
import { formatTime } from "@/utils/formatTime"
export default {
  data() {
    return {
      form: {
        account: "",
        nickname: ""
      },
      img:
        "https://ts1.cn.mm.bing.net/th/id/R-C.67649a22b0cb0dc061ff6d01ae7d0fa0?rik=KXcrLH1EArLL4Q&riu=http%3a%2f%2fguangyuanol.cn%2fuploads%2fallimg%2f220927%2f23230aM3-10.jpg&ehk=lOvFsB5EyLU4OknbfzJOo6gMzZ%2f2ozpTcxDD09O0PH8%3d&risl=&pid=ImgRaw&r=0",
      edit: false,
      isLike: true,
      History: [],
      Goods: [],
      image: "",
      name: "",
      currentNum: 0,
      page: 1,
      size: 6,
      count: 0,
      position: {
        latitude: "",
        longitude: ""
      },
      token: getToken()
    };
  },
  mounted() {
    if (!this.token) {
      this.$router.push('/login');
    } else {
      this.form = { account: this.token.account, nickname: this.token.nickname }
    }
    this.getCurrentInfo();
    this.getUserFood('listing')
  },
  methods: {
    // logout
    exitUser() {
      if (confirm("Confirm logout?")) {
        removeToken();
        this.$message({
          type: "success",
          duration: 1000,
          message: "Exit successfully",
        });
        this.$router.replace("/login");
      }
    },
    handleDetail(id) {
      this.$router.push("/detail/" + id);
    },
    uploadFile(e) {
      let file = e.target.files[0];
      URL.createObjectURL(file)
      const loading = this.$loading({
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
        target: document.querySelector("body"),
      })
      getImage(file).then(res => {
        this.image = res;
        loading.close();
      })
    },
    uploadFood() {
      let { name, image, position } = this;
      if (!name || !image || !position.latitude || !position.latitude) {
        this.$message.warning('Missing pictures, text, latitude and longitude information')
        return;
      }
      let owner = this.token.id;
      let postTime = Date.now();
      let expirationTime = postTime + 1000 * 60 * 60 * 24 * 2;
      requests.post('/addFood', { name, image, ...position, owner, postTime, id: nanoid(), expirationTime }).then(res => {
        if (res.status == 0) {
          this.$message.success('Publishing success');
          this.name = "";
          this.image = "";
          this.edit = false;
        } else {
          this.$message.warning('Publishing failure');
        }
      })
    },
    getCurrentInfo() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(this.success, () => { }, { enableHighAccuracy: true, })
      } else {
        alert("Browser doesn't support Geolocation")
      }

    },
    success(pos) {
      var crd = pos.coords;
      let { longitude, latitude } = crd;
      this.position = { longitude, latitude };
    },
    // get user food
    getUserFood(status) {
      requests(`/get/user/food?status=${status}&owner=${this.token.id}`).then(res => {
        res.data.forEach(v => {
          v.date = formatTime(v.post_time);
        })
        if (status == 'listing') {
          this.Goods = res.data;
        } else {
          this.History = res.data;
        }
      })
    },
    handleGoods() {
      this.isLike = true;
      this.getUserFood('listing')
    },
    handleHistory() {
      this.isLike = false;
      this.getUserFood('delist')
    },
    // delist
    handleDelist(id) {
      if (!confirm(`Are you sure it's off the shelves`)) return;
      requests.put(`/update/food/status?status=delist&id=${id}`).then(res => {
        if (res.status == 0) {
          this.$message.success('Removed successfully');
          this.getUserFood('listing');
        }
      })
    }
  }
};
</script>

<style scoped lang="less">
.active {
  border-bottom: 3px solid var(--themeColor--);
}

.avatarUrl {
  img {
    height: 80px;
    width: 80px;
    box-shadow: 0 0 5px rgba(0, 0, 0, .3);
    border-radius: 50%;
  }
}

.body_container {
  min-width: 1000px;
  width: 70%;
  margin: auto;

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

  .middle {
    width: 70%;
    min-width: 1000px;
    margin: 0 auto;
    overflow: hidden;

    .middleleft {
      width: 50%;
      float: left;

      h2 {
        margin-bottom: 10px;

      }

      .withdraw {
        font-size: 14px;
        margin-bottom: 20px;
        margin-left: 16%;

        a {
          color: black;
          margin-left: 5px;

          &:hover {
            color: rgb(88, 88, 88);
          }
        }
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

    .shelf {
      padding: 10px 30px;
      color: #000;
      font-weight: 600;
      border-radius: 5px;
    }
  }

  .bottom {
    width: 70%;
    min-width: 1000px;
    margin: 0 auto;
    overflow: hidden;

    .bottomtitle {
      width: 100%;
      height: 50px;
      margin-bottom: 20px;

      // titlepro is checked, titlepro1 is checked.
      .titlepro {
        cursor: pointer;
        width: 50%;
        height: 50px;
        line-height: 50px;
        float: left;
        box-sizing: border-box;
        text-align: center;
        font-size: 15px;
        font-weight: 600;
      }
    }

    // my collection
    .bottomlist {
      width: 32%;
      margin-right: 2%;
      float: left;

      &:nth-child(3n) {
        margin-right: 0;
      }


      .middlepro {
        cursor: pointer;
        width: 100%;
        float: left;
        margin-bottom: 4%;
        border: 1px solid #f3e6e6;
        background-color: white;
        transition: 0.5s;

        &:hover {
          box-shadow: 0px 2px 9px 1px #aaa;
          transform: translateY(-5px);
        }

        .proimg {
          width: 100%;
          max-height: 50%;

          img {
            width: 100%;
          }
        }

        .protext-area {
          width: 100%;
          height: 200px;
          box-sizing: border-box;
          padding: 8% 11%;

          h2 {
            text-decoration: none;
            color: black;
            font-size: 28px;
            margin-top: 0px;
            margin-bottom: 3%;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
          }

          .protext {
            font-size: 14px;
            margin-bottom: 1%;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
          }
        }
      }
    }

    // my comments
    .dispro {
      position: relative;
      cursor: pointer;
      width: 100%;
      min-width: 1000px;
      margin: 0 auto;
      margin-bottom: 20px;
      background-color: rgb(245, 245, 245);
      box-sizing: border-box;
      padding: 15px 10px;
      border-radius: 15px;
      overflow: hidden;


      .protext {
        width: 86%;
        height: 100%;
        float: left;

        h3 {
          display: inline-block;
          width: 200px;
          margin-top: 0;
          margin-bottom: 15px;
          margin-left: 10px;
        }


        p {
          margin-left: 10px;
          text-indent: 2em;
          font-size: 15px;
        }
      }

      .protime {
        height: 100%;
        position: absolute;
        right: 20px;
        top: 10px;
        text-align: center;
        font-size: 12px;
        color: rgb(88, 88, 88);

        span {
          white-space: nowrap;
        }

        .close {
          display: inline-block;
          width: 26px;
          height: 26px;
          box-sizing: border-box;
          padding: 5px;
          border-radius: 50%;
          margin-left: 10px;
          border: 1px solid #ccc;
          background: var(--themeColor--);
          color: #fff;
          font-size: 12px;

          &:hover {
            background: rgba(0, 0, 0, .3);
          }
        }
      }

    }

    //  collection is empty
    .collectzero {
      width: 70%;
      margin: 0 auto;
      min-width: 1000px;

      .zeroimg {
        width: 130px;
        height: 130px;
        margin: 30px auto;

        &>img {
          width: 100%;
          height: 100%;
        }
      }

      .zerotext {
        width: 200px;
        margin: 0 auto;
        color: grey;
        margin-bottom: 20px;
        text-align: center;
      }

      .zerobutton {
        width: 100px;
        line-height: 40px;
        background-color: var(--themeColor--);
        margin: 0 auto;
        margin-bottom: 50px;
        text-align: center;
        border-radius: 15px;
        box-sizing: border-box;
        cursor: pointer;

        &>a {
          text-decoration: none;
          color: aliceblue;
        }
      }
    }
  }
}

.list,
.history {
  margin: 50px 0;
  display: flex;
  flex-wrap: wrap;

  li {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    box-shadow: 0 0 10px rgba(0, 0, 0, .5);
    padding: 10px;
    box-sizing: border-box;
    width: 30%;
    margin: 20px 1%;
    cursor: pointer;

    img {
      width: 100%;
    }

    .info {
      font-size: 18px;
      width: 100%;

      .name,
      .date {
        line-height: 40px;
        height: 40px;
      }
    }
  }


}

.history li img {
  filter: grayscale(.7);
}

.shelf_Container {
  position: fixed;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  height: 300px;
  width: 500px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, .5);
  padding: 10px 20px;

  label {
    font-size: 16px;
    font-weight: 600;
    line-height: 40px;
    height: 40px;
  }

  .photo_container {
    box-sizing: border-box;
    height: 100px;
    width: 100%;
    border: 1px solid #ddd;
    padding: 10px;
    display: flex;

    .add_photo {
      position: relative;
      margin: 0 10px;
      height: 80px;
      width: 80px;
      border: 1px solid #ddd;
      font-size: 30px;
      color: #ddd;
      font-weight: 600;
      display: flex;
      align-items: center;
      justify-content: center;
      cursor: pointer;

      input {
        cursor: pointer;
        position: absolute;
        left: 0;
        top: 0;
        opacity: 0;
        height: 100%;
        width: 100%;
      }
    }

    img {
      height: 80px;
      width: 80px;
      border-radius: 4px;
      box-shadow: 0 0 5px rgba(0, 0, 0, .5);
    }
  }

  .buttons {
    display: flex;
    align-items: center;
    justify-content: space-evenly;
    margin: 20px 0;
  }
}
</style>