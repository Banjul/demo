package com.example.demo.model;


import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResultModel<T> {
    private int code;
    private String msg;
    private T data;


    private ResultModel(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ResultModel(CodeMsgModel codeMsgModel){
        if(codeMsgModel != null) {
            this.code = codeMsgModel.getCode();
            this.msg = codeMsgModel.getMsg();
        }
    }

    public static <T> ResultModel<T> success(T data) {
        return new ResultModel<T>(200, "成功", data);
    }
    public static <T> ResultModel<T> error(CodeMsgModel codeMsgModel) {return new ResultModel<T>(codeMsgModel);}

    public JSONObject getJSONObject(){
        Map<String,Object> map = new HashMap<>();
        map.put("code",this.code);
        map.put("msg",this.msg);
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject;
    }
}
