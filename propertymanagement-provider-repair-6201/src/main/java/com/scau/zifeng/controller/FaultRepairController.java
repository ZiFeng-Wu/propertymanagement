package com.scau.zifeng.controller;

import com.scau.zifeng.config.RedisUtils;
import com.scau.zifeng.entities.FaultRepair;
import com.scau.zifeng.jsonFormat.JsonFormat;
import com.scau.zifeng.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FaultRepairController {
    @Autowired
    private RepairService repairService;

    @Autowired
    private RedisUtils redisUtils;

    //插入故障报修
    @RequestMapping(value="/repair/addrepair",method = RequestMethod.POST)
    public int addRepair(@RequestBody FaultRepair faultRepair){
        redisUtils.remove("allnorepair");
        return repairService.addRepair(faultRepair);
    }

    //修改故障报修状态
    @RequestMapping(value="/repair/changestatus",method = RequestMethod.POST)
    public int changeStatus(@RequestBody FaultRepair faultRepair){
        redisUtils.remove("allnorepair");
        return repairService.changeStatus(faultRepair);
    }

    //查找个人故障报修历史
    @RequestMapping(value="/repair/findselfrepair/{id}",method = RequestMethod.GET)
    public @ResponseBody
    JsonFormat findselfRepair(@PathVariable("id") Long id, @RequestParam(value="page") String page, @RequestParam(value="limit") String limit){
       JsonFormat jsonFormat = repairService.findselfRepair(id,page,limit);
        return jsonFormat;
    }

    //查找当前未处理故障
    @RequestMapping(value = "/repair/findnorepair",method = RequestMethod.GET)
    public @ResponseBody JsonFormat findNoRepair(@RequestParam("page") String page,@RequestParam("limit") String limit){
        boolean haskey = redisUtils.exists("allnorepair");
        if(!haskey)
            redisUtils.set("allnorepair",repairService.findNoRepair(page,limit));
        JsonFormat jsonFormat = (JsonFormat)redisUtils.get("allnorepair");
        return jsonFormat;
    }

    //按日期查找故障报修清单
    @RequestMapping(value="/repair/finddaterepair",method = RequestMethod.GET)
    public @ResponseBody JsonFormat findDateRepair(@RequestParam("page") String page,@RequestParam("limit") String limit,@RequestParam("date") String date) throws Exception {
        return repairService.findDateRepair(page,limit,date);
    }


}
