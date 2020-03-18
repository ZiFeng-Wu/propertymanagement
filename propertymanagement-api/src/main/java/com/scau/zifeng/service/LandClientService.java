package com.scau.zifeng.service;

import com.scau.zifeng.entities.Role;
import com.scau.zifeng.entities.User;
import com.scau.zifeng.entities.UserDto;
import com.scau.zifeng.jsonFormat.JsonFormat;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value="propertymanagement-landcloud")
public interface LandClientService {

    //插入用户
    @RequestMapping(value="/user/add",method= RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int add(@RequestBody User user);


    //根据id查询用户
    @RequestMapping(value="/user/get/{id}",method=RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    User get(@PathVariable("id") Long id);


    //查找是否有该用户名已存在
    @RequestMapping(value="/user/findname",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody
    Map<String,Object> findname(@RequestBody User user);


    //用户名密码验证
    @RequestMapping(value="/user/checkpass",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    UserDto checkpass(@RequestBody User user);

    //更新用户信息
    @RequestMapping(value="/user/updatepk",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int updatepk(@RequestBody User user);

    //用户名返回id
    @RequestMapping(value="/user/selectid",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User selectid(@RequestBody User user);

    //输入id删除用户
    @RequestMapping(value="/user/deleteuser",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int deleteUser(@RequestBody User user);


    //添加角色
    @RequestMapping(value="/role/add",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int addrole(@RequestBody Role role);

    //更新角色名
    @RequestMapping(value="/role/update",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int update(@RequestBody Role role);

    //获取当前所有角色
    @RequestMapping(value="/role/get",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JsonFormat get(@RequestParam("page") String page,@RequestParam("limit") String limit);

    //删除某个角色
    @RequestMapping(value="/role/delete",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int delete(@RequestBody Role role);

    @RequestMapping(value="/user/getAll",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JsonFormat getAll(@RequestParam("page") String page,@RequestParam("limit") String limit);

    @RequestMapping(value="/user/changeRole/{id}/{rId}",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int changeRole(@PathVariable("id") Long id,@PathVariable("rId") Long rId);

    @RequestMapping(value="/user/changePwd",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int changePwd(@RequestBody User user) throws Exception;

    //输入一个id找USER返回的是标准格式
    @RequestMapping(value="/user/getFormat/{id}",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JsonFormat getFormat(@PathVariable("id") Long id,@RequestParam("page") String page,@RequestParam("limit") String limit);




}
