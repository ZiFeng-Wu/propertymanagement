package com.scau.zifeng.controller;

import com.scau.zifeng.config.RedisUtils;
import com.scau.zifeng.entities.Role;
import com.scau.zifeng.entities.User;
import com.scau.zifeng.service.RoleService;
import com.scau.zifeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisUtils redisUtils;


    //插入User
    @RequestMapping(value="/user/add",method= RequestMethod.POST)
    public int add(@RequestBody User user) throws Exception {
        redisUtils.set(user.getId().toString(),user,100L, TimeUnit.MINUTES);
        redisUtils.set(user.getName(),user.getId().toString(),100L, TimeUnit.MINUTES);
        return userService.add(user);
    }

    //根据id查询用户
    @RequestMapping(value="/user/get/{id}",method=RequestMethod.GET)
    public @ResponseBody  User get(@PathVariable("id") Long id)
    {
        boolean hasKey = redisUtils.exists(id.toString());
        User user;
        if(hasKey){
            //获取缓存
            Object object =  redisUtils.get(id.toString());
            user = (User)object;
        }else{
            //从数据库中获取信息
            user = userService.get(id);
            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
            redisUtils.set(id.toString(),user,100L, TimeUnit.MINUTES);
        }

        return user;
    }

    //查找是否有该用户名已存在
    @RequestMapping(value="/user/findname",method=RequestMethod.GET)
    public int findname(@RequestBody User user){
        List<User> list = userService.findName(user.getName());
        if(redisUtils.exists(user.getName())||!list.isEmpty())
            return 0;
        else
            return 1;

    }

    //用户名密码验证
    @RequestMapping(value="/user/checkpass",method=RequestMethod.GET)
    public @ResponseBody User checkpass(@RequestBody User user) throws Exception {
        return userService.checkpasswd(user);
    }

    //更新用户信息
    @RequestMapping(value="/user/updatepk",method=RequestMethod.POST)
    public int updatepk(@RequestBody User user) throws Exception {
        int reback = userService.updateByPk(user);
        redisUtils.set(user.getId().toString(),userService.get(user.getId()),100L, TimeUnit.MINUTES);
        redisUtils.set(user.getName(),user.getId().toString(),100L, TimeUnit.MINUTES);
        return reback;
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
        int reback = roleService.add(role);
        redisUtils.set("RoleList",roleService.get(),100L, TimeUnit.MINUTES);
        return reback;
    }

    //更新角色名
    @RequestMapping(value="/role/update",method = RequestMethod.GET)
    public @ResponseBody List<Role> update(@RequestBody Role role){
        int reback = roleService.update(role);
        redisUtils.set("RoleList",roleService.get(),100L, TimeUnit.MINUTES);
        return (List<Role>)redisUtils.get("RoleList");
    }


    //获取当前所有角色
    @RequestMapping(value="/role/get",method = RequestMethod.GET)
    public @ResponseBody List<Role> get(){
        boolean hasKey = redisUtils.exists("RoleList");
        List<Role> roleList;
        if(hasKey){
            //获取缓存
            Object object =  redisUtils.get("RoleList");
            roleList = (List<Role>)object;
        }else{
            //从数据库中获取信息
            roleList = roleService.get();
            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
            redisUtils.set("RoleList",roleService.get(),100L, TimeUnit.MINUTES);
        }
        return roleList;
    }

    //删除某个角色
    @RequestMapping(value="/role/delete",method = RequestMethod.GET)
    public int delete(@RequestBody Role role){
        int reback = roleService.delete(role);
        redisUtils.set("RoleList",roleService.get(),100L, TimeUnit.MINUTES);
        return reback;
    }



}
