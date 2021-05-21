package com.example.demo.serviceImpl;

import org.springframework.util.StringUtils;
import com.example.demo.bean.User;
import com.example.demo.dao.UserDao;
import com.example.demo.exception.GlobalException;
import com.example.demo.model.CodeMsgModel;
import com.example.demo.service.UserService;
import com.example.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;
    public static final String COOKIE_TOKEN_NAME ="token";
    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    @Override
    public User findUserByPhone(String phoneNum) {
        return userDao.findByPhone(phoneNum);
    }

    @Override
    public int addUser(String userNickName, String userPassword, String userPhoneNum) {
        int userId = userDao.addUser(userNickName,userPassword,userPhoneNum);
        return userId;
    }

    @Override
    public String login(HttpServletResponse response, String userPhone, String userPassword) {
        User user = userDao.findByPhone(userPhone);
        //判断用户是否注册
        if(user == null) throw new GlobalException(CodeMsgModel.USER_NOT_EXIST);
        //判断密码
        String realPassword = user.getUserPassword();
        if(!realPassword.equals(userPassword)) throw new GlobalException(CodeMsgModel.PASSWORD_ERROR);

        //生成cookie
        String token = UUID.randomUUID().toString().replace("-","");
        addCookie(response, token, user);
        return token;
    }

    @Override
    public User getUserByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        User user = JSON.parseObject(redisUtil.get(COOKIE_TOKEN_NAME +"::"+token),User.class);
        if(user ==null) throw new GlobalException(CodeMsgModel.USER_NOT_LOGIN);
        if(response!=null) addCookie(response,token,user);
        return user;
    }

    @Override
    public User getLoginUser(HttpServletRequest request, HttpServletResponse response) {
        String paramToken = request.getParameter(UserServiceImpl.COOKIE_TOKEN_NAME);
        String cookieToken = getCookieValue(request, UserServiceImpl.COOKIE_TOKEN_NAME);
        if(StringUtils.isEmpty(paramToken) && StringUtils.isEmpty(cookieToken))
            throw new GlobalException(CodeMsgModel.USER_NOT_LOGIN);
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        return getUserByToken(response, token);
    }

    private String getCookieValue(HttpServletRequest request,String cookieName){
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookies.length == 0)
            throw new GlobalException(CodeMsgModel.TOKEN_INVALID);
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookieName))
                return cookie.getValue();
        }
        return null;
    }



    private void addCookie(HttpServletResponse response,String token, User user){
        //token 存入redis
        redisUtil.set(COOKIE_TOKEN_NAME+"::"+token, JSON.toJSONString(user),TOKEN_EXPIRE);
        //token写入cookie
        Cookie cookie = new Cookie(COOKIE_TOKEN_NAME, token);
        cookie.setMaxAge(TOKEN_EXPIRE);
        //根目录下访问cookie
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
