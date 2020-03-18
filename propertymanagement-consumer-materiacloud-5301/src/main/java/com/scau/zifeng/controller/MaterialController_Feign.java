package com.scau.zifeng.controller;

import com.scau.zifeng.entities.MaterialPurchase;
import com.scau.zifeng.jsonFormat.JsonFormat;
import com.scau.zifeng.service.MaterialClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MaterialController_Feign {

    @Autowired
    private MaterialClientService materialClientService;

    //插入物资采购清单
    @RequestMapping(value="/consumer/material/addmaterial",method = RequestMethod.POST)
    public int addMateria(@RequestBody MaterialPurchase materialPurchase){
        return this.materialClientService.addMateria(materialPurchase);
    }

    //传入个人id查看物资采购历史
    @RequestMapping(value="/consumer/material/findselfmaterial/{id}",method = RequestMethod.GET)
    public @ResponseBody
    JsonFormat findselfMateria(@PathVariable("id") Long id,@RequestParam("page") String page,@RequestParam("limit") String limit){
        return this.materialClientService.findselfMateria(id,page,limit);
    }

    //查看当前所有未审批物资采购清单
    @RequestMapping(value = "/consumer/material/findnodeal",method = RequestMethod.GET)
    public @ResponseBody JsonFormat findNoDeal(@RequestParam("page") String page,@RequestParam("limit") String limit){
        return this.materialClientService.findNoDeal(page,limit);
    }

    //传入日期查看物资采购清单
    @RequestMapping(value = "/consumer/material/finddate",method = RequestMethod.GET)
    public @ResponseBody JsonFormat findDate(@RequestParam("page") String page,@RequestParam("limit") String limit,@RequestParam("date") String date){
        return this.materialClientService.findDate(page,limit,date);
    }

    //查看当前所有物资采购清单历史
    @RequestMapping(value = "/consumer/material/history",method = RequestMethod.GET)
    public @ResponseBody JsonFormat history(@RequestParam("page") String page,@RequestParam("limit") String limit){
        return this.materialClientService.history(page,limit);
    }

    //审批采购清单
    @RequestMapping(value = "/consumer/material/changestatus/{id}",method = RequestMethod.POST)
    public int changeStatus(@PathVariable("id") Long id){
        return this.materialClientService.changeStatus(id);
    }

}
