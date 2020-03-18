package com.scau.zifeng.service;

import com.scau.zifeng.entities.PayList;
import com.scau.zifeng.jsonFormat.JsonFormat;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="propertymanagement-paycloud")
public interface PayClientService {

    //查看当前用户待缴费清单
    @RequestMapping(value="/pay/findselpay/{id}",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JsonFormat findSelfPay(@PathVariable("id") Long id, @RequestParam(value="page") String page, @RequestParam(value="limit") String limit);

    //修改缴费状态
    @RequestMapping(value="/pay/changestatus/{id}/{payNum}",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int changePayStatus(@PathVariable("id") Long id,@PathVariable("payNum") String payNum);


    //按时间查看缴费信息
    @RequestMapping(value="/pay/findtimepay",method = RequestMethod.GET,consumes = "text/html")
    public @ResponseBody JsonFormat findTimePay( @RequestParam(value="page") String page, @RequestParam(value="limit") String limit, @RequestParam(value="uId") Long uId,@RequestParam(value="date") String date);

    //物业管理员插入缴费信息
    @RequestMapping(value="/pay/addpay",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int addPay(@RequestBody  PayList payList);

    //物业管理员获取系统当前所有未缴费清单
    @RequestMapping(value = "/pay/findallnopay",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody  JsonFormat findAllNoPay(@RequestParam("page") String page,@RequestParam("limit") String limit);

    //物业管理员获取系统当前所有未审核清单
    @RequestMapping(value = "/pay/findallnocheck",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody  JsonFormat findAllNoCheck(@RequestParam("page") String page,@RequestParam("limit") String limit);

    //物业管理员传入uID来查询该用户所有缴费信息
    @RequestMapping(value = "/pay/findallpay/{id}",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JsonFormat findAllPay(@PathVariable("id") Long id, @RequestParam(value="page") String page, @RequestParam(value="limit") String limit);


}
