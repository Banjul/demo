package com.example.demo.model;

public class UserModel {
    private String nickName;
    private String password;
    private String phoneNum;
    private String address;

    public String getNickName() {
        return nickName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNum(String telephone) {
        this.phoneNum = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
