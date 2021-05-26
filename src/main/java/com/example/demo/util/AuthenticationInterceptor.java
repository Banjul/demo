package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisUtil redisUtil;

    public static final String COOKIE_TOKEN_NAME ="token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        User user = JSON.parseObject(redisUtil.get(COOKIE_TOKEN_NAME +"::"+token),User.class);
        request.setAttribute("CURRENT_USER", user);
        return true;
    }
}
