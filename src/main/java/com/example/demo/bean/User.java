package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int user_id;

    @Column(name = "nickName")
    private String user_nickName;

    @Column(name = "phoneNum")
    private String user_phoneNum;

    @Column(name = "password")
    private String user_pwd;

    @Column(name = "address")
    private String user_ads;

    public int getUserId() {return user_id;}
    public String getUserNickName() {return user_phoneNum;}
    public String getUserPhoneNum() {return user_phoneNum;}
    public String getUserAddress() {return user_ads;}
    public String getUserPassword() {return user_pwd;}

    public void setId(int userId) { this.user_id = userId; }
    public void setNickName(String userNickName) {this.user_nickName = userNickName;}
    public void setPhoneNum(String userPhoneNum) {this.user_phoneNum = userPhoneNum;}
    public void setPassword(String userPassword) {this.user_pwd = userPassword;}
    public void setAddress(String userAddress) {this.user_ads = userAddress;}


}
