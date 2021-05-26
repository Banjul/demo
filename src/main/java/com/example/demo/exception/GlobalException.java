package com.example.demo.exception;

import com.example.demo.model.CodeMsgModel;

public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private CodeMsgModel cm;

    public GlobalException(CodeMsgModel cm){
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsgModel getCm() {return cm;}

}
