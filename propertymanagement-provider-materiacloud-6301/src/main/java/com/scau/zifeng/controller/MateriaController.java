package com.scau.zifeng.controller;

import com.scau.zifeng.config.RedisUtils;
import com.scau.zifeng.entities.MaterialPurchase;
import com.scau.zifeng.jsonFormat.JsonFormat;
import com.scau.zifeng.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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
    public @ResponseBody
    JsonFormat findselfMateria(@PathVariable("id") Long id,@RequestParam("page") String page,@RequestParam("limit") String limit){
        JsonFormat jsonFormat =  materiaService.findselfMateria(id,page,limit);
        return jsonFormat;
    }

    //查看当前所有未审批物资采购清单
    @RequestMapping(value = "/material/findnodeal",method = RequestMethod.GET)
    public @ResponseBody JsonFormat findNoDeal(@RequestParam("page") String page,@RequestParam("limit") String limit){
        boolean haskey = redisUtils.exists("allnodeal");
        if(!haskey)
            redisUtils.set("allnodeal",materiaService.findNoDeal(page,limit));
        return (JsonFormat)redisUtils.get("allnodeal");
    }

    //传入日期查看物资采购清单
    @RequestMapping(value = "/material/finddate",method = RequestMethod.GET)
    public @ResponseBody JsonFormat findDate(@RequestParam("page") String page,@RequestParam("limit") String limit,@RequestParam("date") String date) throws ParseException {
        return materiaService.findDate(page,limit,date);
    }

    //查看当前所有物资采购清单历史
    @RequestMapping(value = "/material/history",method = RequestMethod.GET)
    public @ResponseBody JsonFormat history(@RequestParam("page") String page,@RequestParam("limit") String limit){
        return materiaService.history(page,limit);
    }

    //审批采购清单
    @RequestMapping(value = "/material/changestatus/{id}",method = RequestMethod.POST)
    public int changeStatus(@PathVariable("id") Long id){
        redisUtils.remove("allnodeal");
        return materiaService.changeStatus(id);
    }

}
