package com.ua.flipPhone.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="admin")
public class Admin {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="admin_id")
    private Integer admin_id;
    
    @Column(name="password")
    private String password;
    
    @Column(name="hash")
    private String hash;
    
    @Column(name="email")
    private String email;

    public Admin(){}
    
    public Admin(String password, String hash, String email) {
        this.password = password;
        this.hash = hash;
        this.email = email;
    }

    public Admin(Integer admin_id, String password, String hash, String email) {
        this.admin_id = admin_id;
        this.password = password;
        this.hash = hash;
        this.email = email;
    }
    
    
    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
    