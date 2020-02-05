package com.scau.zifeng.service.impl;

import com.netflix.discovery.converters.Auto;
import com.scau.zifeng.entities.User;
import com.scau.zifeng.entities.UserExample;
import com.scau.zifeng.mapper.UserMapper;
import com.scau.zifeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int add(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User get(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> findName(String name) {
        UserExample ex = new UserExample();
        UserExample.Criteria c = ex.createCriteria();
        c.andNameEqualTo(name);
        System.out.println(name);
        return userMapper.selectByExample(ex);
    }
}
