package com.ua.flipPhone.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id")
    private Integer user_id;
    
    @Column(name="password")
    private String password;
    
    @Column(name="name")
    private String name;
    
    @Column(name="salt")
    private String salt;
    
    @Column(name="email")
    private String email;
    
    @Column(name="address")
    private String address;
    
    @Column(name="nif")
    private String nif;
    
    @Column(name="type")
    private String type;

    public User(){}
    
    public User(String password, String name, String salt, String email, String address, String nif, String type) {
        this.password = password;
        this.name = name;
        this.salt = salt;
        this.email = email;
        this.address = address;
        this.nif = nif;
        this.type = type;
    }

    public User(Integer user_id, String password, String name, String salt, String email, String address, String nif, String type) {
        this.user_id = user_id;
        this.password = password;
        this.name = name;
        this.salt = salt;
        this.email = email;
        this.address = address;
        this.nif = nif;
        this.type = type;
    }
    
    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}