package com.scau.zifeng.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.scau.zifeng.entities.Dept;
import com.scau.zifeng.entities.Role;
import com.scau.zifeng.entities.User;
import com.scau.zifeng.service.RoleService;
import com.scau.zifeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.RequestWrapper;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    //插入User
    @RequestMapping(value="/user/add",method= RequestMethod.POST)
    public int add(@RequestBody User user)
    {
        return userService.add(user);
    }

    //根据id查询用户
    @RequestMapping(value="/user/get/{id}",method=RequestMethod.GET)
    public @ResponseBody  User get(@PathVariable("id") Long id)
    {
        return userService.get(id);
    }

    //查找是否有该用户名已存在
    @RequestMapping(value="/user/findname",method=RequestMethod.GET)
    public int findname(@RequestBody User user){
        List<User> list = userService.findName(user.getName());
        if(list.isEmpty())
            return 1;
        else
            return 0;
    }

    //用户名密码验证
    @RequestMapping(value="/user/checkpass",method=RequestMethod.GET)
    public @ResponseBody User checkpass(@RequestBody User user){
        List<User> list = userService.findName(user.getName());
        if(list.isEmpty())
            return new User();
        User user2 = list.get(0);
        if(user2.getPassword().equals(user.getPassword()))
            return user2;
        else
            return new User();
    }

    //更新用户信息
    @RequestMapping(value="/user/updatepk",method=RequestMethod.POST)
    public int updatepk(@RequestBody User user){
        return userService.updateByPk(user);
    }

    //用户名返回id
    @RequestMapping(value="/user/selectid",method = RequestMethod.GET)
    public @ResponseBody User selectid(@RequestBody User user){
        List<User> list = userService.findName(user.getName());
        if(list.isEmpty())
            return new User();
        else
            return list.get(0);
    }

    //输入id删除用户
    @RequestMapping(value="/user/deleteuser",method=RequestMethod.POST)
    public int deleteUser(@RequestBody User user){
        return userService.delectUser(user);
    }

    //添加角色
    @RequestMapping(value="/role/add",method=RequestMethod.POST)
    public int addrole(@RequestBody Role role){
        return roleService.add(role);
    }

    //更新角色名
    @RequestMapping(value="/role/update",method = RequestMethod.GET)
    public @ResponseBody List<Role> update(@RequestBody Role role){
        roleService.update(role);
        return roleService.get();
    }


    //获取当前所有角色
    @RequestMapping(value="/role/get",method = RequestMethod.GET)
    public @ResponseBody List<Role> get(){
        return roleService.get();
    }

    //删除某个角色
    @RequestMapping(value="/role/delete",method = RequestMethod.GET)
    public int delete(@RequestBody Role role){
        return roleService.delete(role);
    }



    //输入用户

}
