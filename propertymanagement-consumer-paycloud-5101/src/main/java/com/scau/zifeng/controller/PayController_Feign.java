package com.scau.zifeng.controller;

import com.scau.zifeng.entities.PayList;
import com.scau.zifeng.service.PayClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PayController_Feign {

    @Autowired
    private PayClientService payClientService;

    //查看当前用户待缴费清单
    @RequestMapping(value="/consumer/pay/findselpay/{id}",method = RequestMethod.GET)
    public @ResponseBody
    List<PayList> findSelfPay(@PathVariable("id") Long id){
        return this.payClientService.findSelfPay(id);
    }

    //修改缴费状态
    @RequestMapping(value="/consumer/pay/changestatus/{id}",method = RequestMethod.POST)
    public int changePayStatus(@PathVariable("id") Long id){
        return this.payClientService.changePayStatus(id);
    }


    //按时间查看缴费信息
    @RequestMapping(value="/consumer/pay/findtimepay",method = RequestMethod.GET)
    public @ResponseBody  List<PayList> findTimePay(@RequestBody PayList payList){
        return this.payClientService.findTimePay(payList);
    }

    //物业管理员插入缴费信息
    @RequestMapping(value="/consumer/pay/addpay",method = RequestMethod.POST)
    public int addPay(@RequestBody  PayList payList){
        return this.payClientService.addPay(payList);
    }

    //物业管理员获取系统当前所有未缴费清单
    @RequestMapping(value = "/consumer/pay/findallnopay",method = RequestMethod.GET)
    public @ResponseBody  List<PayList> findAllNoPay(){
        return this.payClientService.findAllNoPay();
    }

    //物业管理员传入uID来查询该用户所有缴费信息
    @RequestMapping(value = "/consumer/pay/findallpay/{id}",method = RequestMethod.GET)
    public @ResponseBody List<PayList> findAllPay(@PathVariable("id") Long id){
        return this.payClientService.findAllPay(id);
    }

}