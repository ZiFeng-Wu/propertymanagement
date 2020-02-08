package com.scau.zifeng.controller;

import com.scau.zifeng.entities.MaterialPurchase;
import com.scau.zifeng.service.MaterialClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public @ResponseBody  List<MaterialPurchase> findselfMateria(@PathVariable("id") Long id){
        return this.materialClientService.findselfMateria(id);
    }

    //查看当前所有未审批物资采购清单
    @RequestMapping(value = "/consumer/material/findnodeal",method = RequestMethod.GET)
    public @ResponseBody List<MaterialPurchase> findNoDeal(){
        return this.materialClientService.findNoDeal();
    }

    //传入日期查看物资采购清单
    @RequestMapping(value = "/consumer/material/finddate",method = RequestMethod.GET)
    public @ResponseBody List<MaterialPurchase> findDate(@RequestBody MaterialPurchase materialPurchase){
        return this.materialClientService.findDate(materialPurchase);
    }

    //审批采购清单
    @RequestMapping(value = "/consumer/materialchangestatus/{id}",method = RequestMethod.POST)
    public int changeStatus(@PathVariable("id") Long id){
        return this.materialClientService.changeStatus(id);
    }

}
