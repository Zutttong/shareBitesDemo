<template>
    <div>
        <div class="title">{{ topicDetail.title }}</div>
        <div class="introduction">
            <div class="introdimg" v-if="topicDetail.img">
                <img :src="topicDetail.img" alt="">
            </div>
        </div>
        <div class="discussarea">
            <template v-if="comments.length > 0">
                <div class="dispro" v-for="item in comments" :key="item.id">
                    <div class="protext">
                        <h3 v-if="!item.hidden">{{ item.nickname }}</h3>
                        <h3 v-else>Anonymous users</h3>
                        <p>{{ item.content }}</p>
                    </div>
                    <div class="protime">
                        <span>{{ item.create_time }}</span>
                    </div>
                </div>
            </template>
            <div class="Replyzero" v-else>
                <div class="zeroimg">
                    <img src="https://static.web.ikea.cn/static/images/search/alien1.svg" alt="">
                </div>
                <div class="zerotext">No reviews yet, grab the headlines!</div>
                <div class="zerobutton" href>
                    <span href="javascript:void(0);" @click="$router.push('/home')">Reply</span>
                </div>
            </div>
        </div>
        <div class="comment-area">
            <div class="comment">
                <input type="text" v-model="form.content" placeholder="Let's join the discussion~">
                <input type="submit" @click="makeComments(0)" value="submit" class="submit">
            </div>
        </div>
    </div>
</template>

<script>
import requests from '@/api/ajax';
import { getToken } from '@/utils/token';
import { formatTime } from "@/utils/formatTime"

export default {
    data() {
        return {
            id: this.$route.params.id,
            topicDetail: {},
            comments: [
            ],
            page: 1,
            size: 5,
            count: 0,
            token: getToken(),
            form: {
                content: "",
            }
        }
    },
    methods: {
        // 获取评论
        getComments() {
            requests({
                url: `/get/comments?foodId=${this.id}`
            }).then(res => {
                res.data.forEach(v => {
                    v.create_time = formatTime(v.createTime);
                })
                this.comments = res.data;
                this.count = res.count;
            })
        },
        // 发表评论
        makeComments() {
            let { content } = this.form;
            if (!this.token) {
                this.$message.info("登录后才可以发表哦");
                return;
            }
            if (content == false) {
                this.$message.warning("输入不能为空");
                return;
            }
            let { nickname } = this.token;
            requests.post('/add/comments', { content, nickname, foodId: this.id, userId: this.token.id }).then(res => {
                if (res.status == 0) {
                    this.$message.success(res.message);
                    this.form.content = "";
                    this.page = 1;
                    this.getComments();
                }
            })
        },
    },
    mounted() {
        this.getComments();
    }
}
</script>

<style scoped lang='less'>
.title {
    width: 70%;
    height: 100px;
    min-width: 1000px;
    line-height: 100%;
    margin: 0 auto;
    font-size: 35px;
    font-weight: 700;
    font-family: 黑体;
    box-sizing: border-box;
    padding-top: 30px;
    border-bottom: 1px solid var(--themeColor--);
}

.introduction {
    width: 70%;
    min-width: 1000px;
    margin: 0 auto;
    margin-bottom: 20px;
    font-size: 14px;
    box-sizing: border-box;
    border-bottom: 1px solid rgb(245, 245, 245);
    padding-bottom: 20px;
    overflow: hidden;

    p {
        text-indent: 2em;
    }

    .introdimg {
        width: 50%;
        margin: 0 auto;

        img {
            width: 100%;
        }
    }
}

.discussarea {
    width: 70%;
    min-width: 1000px;
    margin: 0 auto;
    margin-bottom: 100px;

    .dispro {
        width: 100%;
        min-width: 1000px;
        margin: 0 auto;
        margin-bottom: 20px;
        background-color: rgb(245, 245, 245);
        box-sizing: border-box;
        padding: 15px 10px;
        border-radius: 15px;
        overflow: hidden;

        .proimg {
            width: 5%;
            height: 50px;
            float: left;
            border-radius: 10px;

            img {
                width: 100%;
                height: 100%;
                border-radius: 10px;
            }
        }

        .protext {
            width: 91%;
            height: 100%;
            float: left;

            h3 {
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
            float: right;
            text-align: center;
            font-size: 12px;
            color: rgb(88, 88, 88);

            span {
                white-space: nowrap;
            }
        }
    }

    .Replyzero {
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

            &>span {
                color: aliceblue;
            }
        }
    }
}

.comment-area {
    position: fixed;
    bottom: 0;
    width: 100%;
    min-width: 1000px;
    height: 50px;
    background-color: rgb(245, 245, 245);
    border-top: 1px solid rgb(170, 170, 170);

    .comment {
        width: 70%;
        margin: 0 auto;
        min-width: 1000px;
        height: 50px;
        line-height: 50px;
        background-color: rgb(245, 245, 245);

        input {
            width: 81%;
            height: 30px;
            border-radius: 10px;
            border: 1px solid rgb(65, 56, 58);
            box-sizing: border-box;
            padding: 0 1em;
        }

        .submit {
            width: 8%;
            height: 30px;
            border-radius: 10px;
            border: 1px solid rgb(65, 56, 58);
            background-color: var(--themeColor--);
            color: white;
            box-sizing: border-box;
            margin-left: 1%;

            &:hover {
                background-color: rgb(88, 88, 88);
            }
        }
    }
}
</style>