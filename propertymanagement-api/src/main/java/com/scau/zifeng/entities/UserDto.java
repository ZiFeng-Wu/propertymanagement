package com.scau.zifeng.entities;



import java.io.Serializable;


public class UserDto implements Serializable {
    User user;
    String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
