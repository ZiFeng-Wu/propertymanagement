package com.scau.zifeng.service;

import com.scau.zifeng.entities.MaterialPurchase;
import com.scau.zifeng.jsonFormat.JsonFormat;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="propertymanagement-materialcloud")
public interface MaterialClientService {

    //插入物资采购清单
    @RequestMapping(value="/material/addmaterial",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int addMateria(@RequestBody MaterialPurchase materialPurchase);


    //传入个人id查看物资采购历史
    @RequestMapping(value="/material/findselfmaterial/{id}",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JsonFormat findselfMateria(@PathVariable("id") Long id,@RequestParam("page") String page,@RequestParam("limit") String limit);

    //查看当前所有未审批物资采购清单
    @RequestMapping(value = "/material/findnodeal",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JsonFormat findNoDeal(@RequestParam("page") String page,@RequestParam("limit") String limit);

    //传入日期查看物资采购清单
    @RequestMapping(value = "/material/finddate",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JsonFormat findDate(@RequestParam("page") String page,@RequestParam("limit") String limit,@RequestParam("date") String date);

    //查看当前所有物资采购清单历史
    @RequestMapping(value = "/material/history",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JsonFormat history(@RequestParam("page") String page,@RequestParam("limit") String limit);

    //审批采购清单
    @RequestMapping(value = "/material/changestatus/{id}",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int changeStatus(@PathVariable("id") Long id);
}
