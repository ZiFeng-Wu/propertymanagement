package com.scau.zifeng.service;

import com.scau.zifeng.entities.User;
import com.scau.zifeng.jsonFormat.JsonFormat;

import java.util.List;

public interface UserService {
    //添加用户
    public int add(User user) throws Exception;
    //通过id找user
    public User    get(Long id);
    //查找是否有该名字
    public List<User> findName(String name);
    //更新个人信息
    public int updateByPk(User user) throws Exception;
    //通过名字返回对象
    public List<User>  selectid(User user);
    //删除对象
    public int delectUser(User user);
    //密码对比
    public User checkpasswd(User user) throws Exception;
    //获取所有对象
    public JsonFormat getAll(String page,String limit);
    //变更权限
    public int changeRole(Long id,Long rId);
    //密码变更
    public int changePwd(User user) throws Exception;
    //输入一个id找USER返回的是标准格式
    public JsonFormat getFormat(Long id);

}
