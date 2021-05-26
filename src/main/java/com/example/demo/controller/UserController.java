package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.ResponseModel;
import com.example.demo.model.ResultModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import com.example.demo.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.util.*;

@RestController
@RequestMapping(value = "/users")
@Api("用户类相关api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;
//    @Autowired
//    private UserMapper userMapper;

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
    public JSONObject login(HttpServletResponse response, @RequestBody Map<String,Object> params){
        System.out.println(params);

        String userPhoneNum = params.get("phone").toString();
        String userPassword = params.get("password").toString();
        String token = userService.login(response,userPhoneNum,userPassword);
        System.out.println(ResultModel.success(token).getJSONObject());
        return ResultModel.success(token).getJSONObject();

    }
    @GetMapping(value = "/testRedis")
    public String testRedis(HttpServletRequest request, HttpServletResponse response){
        User user = userService.getLoginUser(request,response);
        if(user != null)
            return user.toString();
        return "No users in Redis";
    }

    @GetMapping(value = "/cleanRedis")
    public void cleanRedis(HttpServletRequest request){
        redisUtil.clean();
    }
}
