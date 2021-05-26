package com.example.demo.mapper;

import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {

    User findByPhone(@Param("phoneNum") String phoneNum);
    Integer addUser(String nickName, String password, String phoneNum);
}
