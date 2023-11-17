package com.example.sharebitesdemo.utils;
import com.alibaba.fastjson.JSONObject;

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
        json.put("message",message);
        json.put("data",data);
        json.put("status",status);
        return json;
    }
}
