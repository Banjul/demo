package com.example.demo.dao;

import com.example.demo.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Component
@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    @Query(value = "select * from user where phoneNum =?1", nativeQuery = true)
    User findByPhone(String phoneNum);

    @Modifying
    @Transactional
    @Query(value = "insert into user(nickName,password,address,phoneNum) values(?1,?2,null,?3)", nativeQuery = true)
    int addUser(String nickName, String userPassword, String userPhoneNum);


}
