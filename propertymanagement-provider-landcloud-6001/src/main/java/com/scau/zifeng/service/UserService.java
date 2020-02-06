package com.scau.zifeng.service;

import com.scau.zifeng.entities.User;

import java.util.List;

public interface UserService {
    public int add(User user);
    public User    get(Long id);
    public List<User> findName(String name);
    public int updateByPk(User user);
    public List<User>  selectid(User user);
    public int delectUser(User user);
}
