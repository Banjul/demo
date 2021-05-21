package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.model.ResponseModel;
import com.example.demo.model.ResultModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;

@RestController
@RequestMapping(value = "/users")
@Api("用户类相关api")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册", httpMethod = "POST", notes = "手机号唯一")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseModel register (HttpSession session, @RequestBody UserModel userModel){
        String userNickName = userModel.getNickName();
        String userPassword = userModel.getPassword();
        String userPhoneNum = userModel.getPhoneNum();

        ResponseModel response = new ResponseModel();
        User user = userService.findUserByPhone(userPhoneNum);

        if(user!=null){
            response.setMessage("该手机号已注册");
            response.setStatus("500");
        }else{
            int u = userService.addUser(userNickName, userPassword, userPhoneNum);
            if(u!=0){
                response.setStatus("200");
                response.setMessage("注册成功");
                User userInfo = userService.findUserByPhone(userPhoneNum);
                session.setAttribute("userInfo", userInfo);
                response.setModel(userInfo);
            }
        }
        return response;
    }

    @ApiOperation(value = "用户登录", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultModel<String> login(HttpServletResponse response, @RequestBody UserModel userModel){
        String userPhoneNum = userModel.getPhoneNum();
        String userPassword = userModel.getPassword();

        //用户登录，返回token
        String token = userService.login(response,userPhoneNum,userPassword);
        return ResultModel.success(token);
    }
}
