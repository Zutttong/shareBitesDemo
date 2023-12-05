import axios from "axios";

export default (text) => {
  var data = new FormData();
  data.append("from", "zh");
  data.append("to", "en");
  data.append("q", text);
  console.log(text);
  axios({
    url: `/translation?text=${text}&from=zh-CN&to=en`,
  }).then((res) => {
    console.log(res.data.data);
  });
};
