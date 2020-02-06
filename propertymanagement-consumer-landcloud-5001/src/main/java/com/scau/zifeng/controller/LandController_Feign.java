package com.scau.zifeng.controller;

import com.scau.zifeng.entities.Role;
import com.scau.zifeng.entities.User;
import com.scau.zifeng.service.LandClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //用户名返回id
    @RequestMapping(value="/consumer/user/selectid",method = RequestMethod.GET)
    public @ResponseBody User selectid(@RequestBody User user){
        return this.landClientService.selectid(user);
    }


    //用户名密码验证
    @RequestMapping(value="/consumer/user/checkpass",method=RequestMethod.GET)
    public @ResponseBody User checkpass(@RequestBody User user){
        return this.landClientService.checkpass(user);
    }

    //更新用户信息
    @RequestMapping(value="/consumer/user/updatepk",method=RequestMethod.POST)
    public int updatepk(@RequestBody User user){
        return this.landClientService.updatepk(user);
    }

    //输入id删除用户
    @RequestMapping(value="/consumer/user/deleteuser",method=RequestMethod.POST)
    public int deleteUser(@RequestBody User user){
        return this.landClientService.deleteUser(user);
    }

    //添加角色
    @RequestMapping(value="/consumer/role/add",method=RequestMethod.POST)
    public int addrole(@RequestBody Role role){
        return this.landClientService.addrole(role);
    }

    //更新角色名
    @RequestMapping(value="/consumer/role/update",method = RequestMethod.GET)
    public @ResponseBody
    List<Role> update(@RequestBody Role role){
        return this.landClientService.update(role);
    }

    //获取当前所有角色
    @RequestMapping(value="/consumer/role/get",method = RequestMethod.GET)
    public @ResponseBody List<Role> get(){
        return this.landClientService.get();
    }

    //删除某个角色
    @RequestMapping(value="/consumer/role/delete",method = RequestMethod.GET)
    public int delete(@RequestBody Role role){
        return this.landClientService.delete(role);
    }




}
