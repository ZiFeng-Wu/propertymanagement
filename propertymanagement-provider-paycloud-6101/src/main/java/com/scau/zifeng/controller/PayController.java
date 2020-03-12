package com.scau.zifeng.controller;

import com.scau.zifeng.config.RedisUtils;
import com.scau.zifeng.entities.PayList;
import com.scau.zifeng.jsonFormat.JsonFormat;
import com.scau.zifeng.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PayController {
    @Autowired
    private PayService payService;

    @Autowired
    private RedisUtils redisUtils;

    //查看当前用户待缴费清单
    @RequestMapping(value="/pay/findselpay/{id}",method = RequestMethod.GET)
    public @ResponseBody  JsonFormat findSelfPay(@PathVariable("id") Long id,@RequestParam(value="page") String page, @RequestParam(value="limit") String limit){
        JsonFormat jsonFormat = payService.findSelfPay(id,page,limit);
        Map<String,Object> map = new HashMap<String,Object>();
        return jsonFormat;
    }

    //修改缴费状态
    @RequestMapping(value="/pay/changestatus/{id}",method = RequestMethod.POST)
    public int changePayStatus(@PathVariable("id") Long id){
        redisUtils.remove("allnopay");
        return payService.changePayStatus(id);
    }


    //按时间查看缴费信息
    @RequestMapping(value="/pay/findtimepay",method = RequestMethod.GET)
    public @ResponseBody  JsonFormat findTimePay(@RequestParam(value="page") String page, @RequestParam(value="limit") String limit,@RequestParam(value="uId") Long uId,@RequestParam(value="date") String date) throws Exception {
        System.out.println("yes");
        JsonFormat jsonFormat =  payService.findTimePay(page,limit,uId,date);
        Map<String,Object> map = new HashMap<String,Object>();
        return jsonFormat;
    }

    //物业管理员插入缴费信息
    @RequestMapping(value="/pay/addpay",method = RequestMethod.POST)
    public int addPay(@RequestBody  PayList payList){
        redisUtils.remove("allnopay");
        return payService.addPay(payList);
    }

    //物业管理员获取系统当前所有未缴费清单
    @RequestMapping(value = "/pay/findallnopay",method = RequestMethod.GET)
    public @ResponseBody  List<PayList> findAllNoPay(){
        boolean haskey = redisUtils.exists("allnopay");
        if(!haskey)
            redisUtils.set("allnopay",payService.findAllNoPay());
        return (List<PayList>)redisUtils.get("allnopay");
    }

    //物业管理员传入uID来查询该用户所有缴费信息
    @RequestMapping(value = "/pay/findallpay/{id}",method = RequestMethod.GET)
    public @ResponseBody
    JsonFormat findAllPay(@PathVariable("id") Long id, @RequestParam(value="page") String page, @RequestParam(value="limit") String limit){
        JsonFormat jsonFormat =  payService.findAllPay(id,page,limit);
        Map<String,Object> map = new HashMap<String,Object>();
        return jsonFormat;
    }

}
