package com.scau.zifeng.service.impl;

import com.scau.zifeng.entities.User;
import com.scau.zifeng.entities.UserExample;
import com.scau.zifeng.mapper.UserMapper;
import com.scau.zifeng.service.UserService;
import com.scau.zifeng.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;




    @Override
    public int add(User user) throws Exception{
        String password = user.getPassword();
        user.setPassword(Md5Util.md5(password,"19970825"));
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
        return userMapper.selectByExample(ex);
    }

    @Override
    public int updateByPk(User user) throws  Exception{
        User user2 = userMapper.selectByPrimaryKey(user.getId());
        if(!(Md5Util.verify(user.getPassword(),"19970825",user2.getPassword())))
            user2.setPassword(Md5Util.md5(user.getPassword(),"19970825"));
        if(!user2.getName().equals(user.getName())&&user.getName()!=null)
            user2.setName(user.getName());
        if(!user2.getAddress().equals(user.getAddress())&&user.getAddress()!=null)
            user2.setAddress(user.getAddress());
        if(!user2.getTelephone().equals(user.getTelephone())&&user.getTelephone()!=null)
            user2.setTelephone(user.getTelephone());
        if(!user2.getrId().equals(user.getrId())&&user.getrId()!=null)
            user2.setrId(user.getrId());
        return userMapper.updateByPrimaryKey(user2);
    }

    @Override
    public List<User>  selectid(User user) {
        UserExample ex = new UserExample();
        UserExample.Criteria c = ex.createCriteria();
        c.andNameEqualTo(user.getName());
        System.out.println(user.getName());
        return userMapper.selectByExample(ex);
    }

    @Override
    public int delectUser(User user) {
        return userMapper.deleteByPrimaryKey(user.getId());
    }

    @Override
    public User checkpasswd(User user) throws Exception {
        List<User> list = this.findName(user.getName());
        if(list.isEmpty())
            return new User();
        User user2 = list.get(0);
        if(Md5Util.verify(user.getPassword(),"19970825",user2.getPassword()))
            return user2;
        else
            return new User();
    }


}
