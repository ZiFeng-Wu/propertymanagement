package com.scau.zifeng.controller;

import com.scau.zifeng.config.RedisUtils;
import com.scau.zifeng.entities.MaterialPurchase;
import com.scau.zifeng.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MateriaController {
    @Autowired
    private MateriaService materiaService;
    @Autowired
    private RedisUtils redisUtils;

    //插入物资采购清单
    @RequestMapping(value="/material/addmaterial",method = RequestMethod.POST)
    public int addMateria(@RequestBody MaterialPurchase materialPurchase){
        redisUtils.remove("allnodeal");
        return materiaService.addMateria(materialPurchase);
    }

    //传入个人id查看物资采购历史
    @RequestMapping(value="/material/findselfmaterial/{id}",method = RequestMethod.GET)
    public @ResponseBody  List<MaterialPurchase> findselfMateria(@PathVariable("id") Long id){
        return materiaService.findselfMateria(id);
    }

    //查看当前所有未审批物资采购清单
    @RequestMapping(value = "/material/findnodeal",method = RequestMethod.GET)
    public @ResponseBody List<MaterialPurchase> findNoDeal(){
        boolean haskey = redisUtils.exists("allnodeal");
        if(!haskey)
            redisUtils.set("allnodeal",materiaService.findNoDeal());
        return (List<MaterialPurchase>)redisUtils.get("allnodeal");
    }

    //传入日期查看物资采购清单
    @RequestMapping(value = "/material/finddate",method = RequestMethod.GET)
    public @ResponseBody List<MaterialPurchase> findDate(@RequestBody MaterialPurchase materialPurchase){
        return materiaService.findDate(materialPurchase);
    }

    //审批采购清单
    @RequestMapping(value = "/materialchangestatus/{id}",method = RequestMethod.POST)
    public int changeStatus(@PathVariable("id") Long id){
        redisUtils.remove("allnodeal");
        return materiaService.changeStatus(id);
    }

}
