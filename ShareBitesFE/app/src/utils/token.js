
export const setToken = (token) => {
  localStorage.setItem("TOKEN", JSON.stringify(token));
};

export const getToken = () => {
  if (localStorage.getItem("TOKEN")) {
    var Token = JSON.parse(localStorage.getItem("TOKEN")) || {};
    return Token;
  }
  return false;
};

export const removeToken = () => {
  localStorage.removeItem("TOKEN");
};
