package com.example.demo.model;

import org.aspectj.apache.bcel.classfile.Code;

public class CodeMsgModel {
    private int code;
    private String msg;

    private CodeMsgModel(){}

    private CodeMsgModel(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public int getCode(){return code;}
    public String getMsg(){return msg;}

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }

    public static CodeMsgModel SUCCESS = new CodeMsgModel(200,"success");
    public static CodeMsgModel USER_NOT_LOGIN = new CodeMsgModel(500,"用户未登录");
    public static CodeMsgModel USER_NOT_EXIST = new CodeMsgModel(501,"用户名不存在");
    public static CodeMsgModel PASSWORD_ERROR = new CodeMsgModel(502,"密码错误");
    public static CodeMsgModel TOKEN_INVALID = new CodeMsgModel(504,"token无效");







}
