export default (timeStamp) => {
  var time = new Date(timeStamp);

  var year = time.getFullYear();
  var month = time.getMonth() + 1;
  var date = time.getDate();
  var hours = time.getHours();
  var minutes = time.getMinutes();
  return (
    year +
    "-" +
    month +
    "-" +
    date +
    " " +
    hours.toString().padStart(2, "0") +
    ":" +
    minutes.toString().padStart(2, "0")
  );
};

export const format_time = (timeStamp) => {
  var time = new Date(timeStamp);

  var year = time.getFullYear();
  var month = time.getMonth() + 1;
  var date = time.getDate();
  return (
    year +
    "-" +
    month.toString().padStart(2, "0") +
    "-" +
    date.toString().padStart(2, "0")
  );
};

export const formatTime = (timeStamp) => {
  var time = new Date(timeStamp);

  var year = time.getFullYear();
  var month = time.getMonth() + 1;
  var date = time.getDate();
  var hour = time.getHours();
  var min = time.getMinutes();
  var s = time.getSeconds();
  return (
    year +
    "-" +
    month.toString().padStart(2, "0") +
    "-" +
    date.toString().padStart(2, "0") +
    " " +
    hour.toString().padStart(2, "0") +
    ":" +
    min.toString().padStart(2, "0") +
    ":" +
    s.toString().padStart(2, "0")
  );
};
