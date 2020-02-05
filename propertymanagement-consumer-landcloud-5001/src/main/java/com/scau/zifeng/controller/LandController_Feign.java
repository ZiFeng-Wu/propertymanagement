package com.scau.zifeng.controller;

import com.scau.zifeng.entities.User;
import com.scau.zifeng.service.LandClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class LandController_Feign {
    @Autowired
    private LandClientService landClientService;

    //插入用户
    @RequestMapping(value="/consumer/user/add",method= RequestMethod.POST)
    public int add(@RequestBody User user){
        return this.landClientService.add(user);
    }


    //根据id查询用户
    @RequestMapping(value="/consumer/user/get/{id}",method=RequestMethod.GET)
    public @ResponseBody
    User get(@PathVariable("id") Long id){
        return this.landClientService.get(id);
    }


    //查找是否有该用户名已存在
    @RequestMapping(value="/consumer/user/findname",method=RequestMethod.GET)
    public int findname(@RequestBody User user){
        System.out.println(user.getName());
        return this.landClientService.findname(user);
    }


    //用户名密码验证
    @RequestMapping(value="/consumer/user/checkpass",method=RequestMethod.GET)
    public @ResponseBody User checkpass(@RequestBody User user){
        return this.landClientService.checkpass(user);
    }

}
