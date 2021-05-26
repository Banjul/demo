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
    private static final long serialVersionUID = -5144055068797033748L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    public int getId() {return id;}
    public String getName() {return name;}
    public String getPhone() {return phone;}
    public String getAddress() {return address;}
    public String getPassword() {return password;}

//    public void setId(int userId) { this.id = userId; }
//    public void setNickName(String userNickName) {this.nickName = userNickName;}
//    public void setPhoneNum(String userPhoneNum) {this.phoneNum = userPhoneNum;}
//    public void setPassword(String userPassword) {this.password = userPassword;}
//    public void setAddress(String userAddress) {this.address = userAddress;}


}
