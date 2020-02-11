package com.scau.zifeng.service;

import com.scau.zifeng.entities.User;

import java.util.List;

public interface UserService {
    public int add(User user) throws Exception;
    public User    get(Long id);
    public List<User> findName(String name);
    public int updateByPk(User user) throws Exception;
    public List<User>  selectid(User user);
    public int delectUser(User user);
    public User checkpasswd(User user) throws Exception;
}
