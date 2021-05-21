package com.example.demo.service;

import com.example.demo.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    User findUserByPhone(String phoneNum);
    int addUser(String userNickName, String userPassword, String userPhoneNum);
    //返回token
    String login(HttpServletResponse response, String userPhone, String userPassword);
    User getUserByToken(HttpServletResponse response, String token);
    User getLoginUser(HttpServletRequest request, HttpServletResponse response);
}
