import { Message } from "element-ui";

export function registerCheck({ account, password, nickname }) {
  let regPassword = /^(?=\S*[a-z])(?=\S*\d)\S{8,16}$/;
  var regaccount = /^[a-zA-Z0-9_-]{8,20}/;
  if (!password || !account || !nickname) {
    Message({
      message: "The input cannot be empty",
      type: "warning",
    });
    return false;
  } else if (!regPassword.test(password)) {
    Message({
      message: "The password must contain letters, numbers, no spaces, and more than 8 digits",
      type: "warning",
    });
    return false;
  } else if (!regaccount.test(account)) {
    Message({
      message: "The account number is incorrect",
      type: "warning",
    });
    return false;
  } else if (nickname.length > 15) {
    Message.warning("The nickname is too long");
    return false;
  }
  return true;
}
