package com.scau.zifeng.controller;

import com.scau.zifeng.entities.PayList;
import com.scau.zifeng.jsonFormat.JsonFormat;
import com.scau.zifeng.service.PayClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PayController_Feign {

    @Autowired
    private PayClientService payClientService;

    //查看当前用户待缴费清单
    @RequestMapping(value="/consumer/pay/findselpay/{id}",method = RequestMethod.GET)
    public @ResponseBody
    JsonFormat findSelfPay(@PathVariable("id") Long id, @RequestParam(value="page") String page, @RequestParam(value="limit") String limit){
        return payClientService.findSelfPay(id,page,limit);
    }

    //修改缴费状态
    @RequestMapping(value="/consumer/pay/changestatus/{id}/{payNum}",method = RequestMethod.POST)
    public int changePayStatus(@PathVariable("id") Long id,@PathVariable("payNum") String payNum){
        return this.payClientService.changePayStatus(id,payNum);
    }


    //按时间查看缴费信息
    @RequestMapping(value="/consumer/pay/findtimepay",method = RequestMethod.GET)
    public @ResponseBody  JsonFormat findTimePay(@RequestParam(value="page") String page, @RequestParam(value="limit") String limit,@RequestParam(value="uId") Long uId,@RequestParam(value="date") String date){
        return this.payClientService.findTimePay(page,limit,uId,date);
    }

    //物业管理员插入缴费信息
    @RequestMapping(value="/consumer/pay/addpay",method = RequestMethod.POST)
    public int addPay(@RequestBody  PayList payList){
        return this.payClientService.addPay(payList);
    }

    //物业管理员获取系统当前所有未缴费清单
    @RequestMapping(value = "/consumer/pay/findallnopay",method = RequestMethod.GET)
    public @ResponseBody  JsonFormat findAllNoPay(@RequestParam("page") String page,@RequestParam("limit") String limit){
        return this.payClientService.findAllNoPay(page,limit);
    }

    //物业管理员获取系统当前所有未审核清单
    @RequestMapping(value = "/consumer/pay/findallnocheck",method = RequestMethod.GET)
    public @ResponseBody  JsonFormat findAllNoChecky(@RequestParam("page") String page,@RequestParam("limit") String limit){
        return this.payClientService.findAllNoCheck(page,limit);
    }

    //物业管理员传入uID来查询该用户所有缴费信息
    @RequestMapping(value = "/consumer/pay/findallpay/{id}",method = RequestMethod.GET)
    public @ResponseBody
    JsonFormat findAllPay(@PathVariable("id") Long id, @RequestParam(value="page") String page, @RequestParam(value="limit") String limit){
        return this.payClientService.findAllPay(id,page,limit);
    }

}
