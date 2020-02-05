package com.scau.zifeng.controller;

import com.scau.zifeng.entities.Dept;
import com.scau.zifeng.entities.User;
import com.scau.zifeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

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
        for(User s1:list)
        System.out.println(s1.getName());
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
}
