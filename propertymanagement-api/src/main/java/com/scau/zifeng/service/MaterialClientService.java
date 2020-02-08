package com.scau.zifeng.service;

import com.scau.zifeng.entities.FaultRepair;
import com.scau.zifeng.entities.MaterialPurchase;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value="propertymanagement-materialcloud")
public interface MaterialClientService {

    //插入物资采购清单
    @RequestMapping(value="/material/addmaterial",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int addMateria(@RequestBody MaterialPurchase materialPurchase);


    //传入个人id查看物资采购历史
    @RequestMapping(value="/material/findselfmaterial/{id}",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody  List<MaterialPurchase> findselfMateria(@PathVariable("id") Long id);

    //查看当前所有未审批物资采购清单
    @RequestMapping(value = "/material/findnodeal",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<MaterialPurchase> findNoDeal();

    //传入日期查看物资采购清单
    @RequestMapping(value = "/material/finddate",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<MaterialPurchase> findDate(@RequestBody MaterialPurchase materialPurchase);

    //审批采购清单
    @RequestMapping(value = "/materialchangestatus/{id}",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int changeStatus(@PathVariable("id") Long id);
}
