package com.example.demo.exception;

import com.example.demo.model.CodeMsgModel;
import com.example.demo.model.ResultModel;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindException;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResultModel<String> exceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        if(e instanceof GlobalException) {
            GlobalException ex = (GlobalException) e;
            return ResultModel.error(ex.getCm());
        }else if(e instanceof BindException){
            BindException ex = (org.springframework.validation.BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return ResultModel.error(CodeMsgModel.BIND_ERROR);
        }else{
            return ResultModel.error(CodeMsgModel.SERVER_ERROR);
        }

    }
}
