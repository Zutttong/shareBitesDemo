package com.example.sharebitesdemo.utils;
import com.alibaba.fastjson.JSONObject;
/*
Json 类被设计为一个工具类，它封装了创建统一格式JSON响应的逻辑。
JSON响应通常由服务器发送到前端，其中包含执行操作的结果信息，可能还包含请求的数据或错误信息。
这个类通过提供一个标准的方法来构建响应，使得前端可以预期和解析结构一致的数据。
 */
public class Json {
    String message;
    Object data;
    Integer status;

    public Json(String m, Object d, Integer s){
        this.data=d;
        this.status=s;
        this.message=m;
    }
    public JSONObject getJson(){
        JSONObject json=new JSONObject();
        json.put("message",message); //这个字段用于传达操作成功或失败的消息给前端，例如"success"或"error"。
        json.put("data",data);//如果请求需要返回数据，data字段会被用来携带这些数据。比如在获取食物详情的请求中，它可以包含食物的属性。
        json.put("status",status);//通常用于表示请求的处理状态。例如，0可能表示成功，而1可能表示出现了某种错误。
        return json;
    }
}
