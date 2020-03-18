package com.scau.zifeng.controller;

import com.scau.zifeng.config.RedisUtils;
import com.scau.zifeng.entities.Role;
import com.scau.zifeng.entities.User;
import com.scau.zifeng.entities.UserDto;
import com.scau.zifeng.jsonFormat.JsonFormat;
import com.scau.zifeng.service.RoleService;
import com.scau.zifeng.service.UserService;
import com.scau.zifeng.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        int state =userService.add(user);
        redisUtils.set(userService.findName(user.getName()).get(0).getId().toString(),userService.findName(user.getName()).get(0),10L, TimeUnit.MINUTES);
        redisUtils.set(user.getName(),userService.findName(user.getName()).get(0).getId().toString(),10L, TimeUnit.MINUTES);
        return state;
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
            redisUtils.set(id.toString(),user,10l, TimeUnit.MINUTES);
            redisUtils.set(user.getName(),user.getId().toString(),10L, TimeUnit.MINUTES);
        }

        return user;
    }

    //查找是否有该用户名已存在
    @RequestMapping(value="/user/findname",method=RequestMethod.POST)
    public @ResponseBody
    Map<String,Object> findname(@RequestBody User user){
        List<User> list = userService.findName(user.getName());
        Map<String,Object> map = new HashMap<String,Object>();
        if(redisUtils.exists(user.getName())||!list.isEmpty()){
            if(!list.isEmpty())
            map.put("user",list.get(0));
            else
            map.put("user",redisUtils.get(user.getName()));
        }
        else{
            User user2 = new User();
            user2.setId(Long.parseLong("-1"));
            map.put("user",user2);
        }

        return map;


    }

    //用户名密码验证
    @RequestMapping(value="/user/checkpass",method=RequestMethod.POST)
    public @ResponseBody UserDto checkpass(@RequestBody User user) throws Exception {

        UserDto userDto= new UserDto();
        User user2 = userService.checkpasswd(user);
        if(user2!=null){
            if(redisUtils.exists(user2.getId().toString()+"token"))
                userDto.setToken(redisUtils.get(user2.getId().toString()+"token").toString());
            else{
                String token = TokenUtil.genUniqueKey(user2.getName());
                redisUtils.set(token,user2.getId().toString(),30L,TimeUnit.MINUTES);
                redisUtils.set(user2.getId().toString()+"token",token,30L, TimeUnit.MINUTES);
                userDto.setToken(token);
            }
        }

        userDto.setUser(user2);
        System.out.println(userDto.getToken());
        return userDto;
    }

    //更新用户信息
    @RequestMapping(value="/user/updatepk",method=RequestMethod.POST)
    public int updatepk(@RequestBody User user) throws Exception {
        int reback = userService.updateByPk(user);
        System.out.println("okok");
        redisUtils.set(user.getId().toString(),userService.get(user.getId()),10L, TimeUnit.MINUTES);
        redisUtils.set(user.getName(),user.getId().toString(),10L, TimeUnit.MINUTES);

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
        redisUtils.remove(userService.get(user.getId()).getName());
        redisUtils.remove(user.getId().toString());
        return userService.delectUser(user);
    }

    //添加角色
    @RequestMapping(value="/role/add",method=RequestMethod.POST)
    public int addrole(@RequestBody Role role){
        redisUtils.remove("RoleList");
        return roleService.add(role);
    }

    //更新角色名
    @RequestMapping(value="/role/update",method = RequestMethod.POST)
    public int update(@RequestBody Role role){
        int reback = roleService.update(role);
        redisUtils.remove("RoleList");
        return reback;
    }


    //获取当前所有角色
    @RequestMapping(value="/role/get",method = RequestMethod.GET)
    public @ResponseBody JsonFormat get(@RequestParam("page") String page,@RequestParam("limit") String limit){
        boolean hasKey = redisUtils.exists("RoleList");
        if(hasKey){
            //获取缓存
            return  (JsonFormat) redisUtils.get("RoleList");
        }else{
            //从数据库中获取信息
            JsonFormat jsonFormat = roleService.get(page,limit);
            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
            redisUtils.set("RoleList",jsonFormat,10L, TimeUnit.MINUTES);
        }
        return  roleService.get(page,limit);
    }

    //删除某个角色
    @RequestMapping(value="/role/delete",method = RequestMethod.POST)
    public int delete(@RequestBody Role role){
        redisUtils.remove("RoleList");
        return roleService.delete(role);
    }

    //获取所有对象
    @RequestMapping(value="/user/getAll",method = RequestMethod.GET)
    public  @ResponseBody JsonFormat getAll(@RequestParam("page") String page,@RequestParam("limit") String limit){
            return userService.getAll(page,limit);
    }
    //变更权限
    @RequestMapping(value="/user/changeRole/{id}/{rId}",method = RequestMethod.POST)
    public int changeRole(@PathVariable("id") Long id,@PathVariable("rId") Long rId){
        return userService.changeRole(id,rId);
    }
    //密码变更
    @RequestMapping(value="/user/changePwd",method = RequestMethod.POST)
    public int changePwd(@RequestBody User user) throws Exception{
        return userService.changePwd(user);
    }
    //输入一个id找USER返回的是标准格式
    @RequestMapping(value="/user/getFormat/{id}",method = RequestMethod.GET)
    public @ResponseBody JsonFormat getFormat(@PathVariable("id") Long id,@RequestParam("page") String page,@RequestParam("limit") String limit) {
        System.out.println("ok");
        return userService.getFormat(id);
    }

}
