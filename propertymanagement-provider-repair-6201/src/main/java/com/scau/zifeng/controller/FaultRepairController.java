package com.scau.zifeng.controller;

import com.scau.zifeng.config.RedisUtils;
import com.scau.zifeng.entities.FaultRepair;
import com.scau.zifeng.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public @ResponseBody  Map<String, Object> findselfRepair(@PathVariable("id") Long id){
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("date",repairService.findselfRepair(id));
        return map;
    }

    //查找当前未处理故障
    @RequestMapping(value = "/repair/findnorepair",method = RequestMethod.GET)
    public @ResponseBody List<FaultRepair> findNoRepair(){
        boolean haskey = redisUtils.exists("allnorepair");
        if(!haskey)
            redisUtils.set("allnorepair",repairService.findNoRepair());
        return (List<FaultRepair>)redisUtils.get("allnorepair");
    }

    //按日期查找故障报修清单
    @RequestMapping(value="/repair/finddaterepair",method = RequestMethod.GET)
    public @ResponseBody List<FaultRepair> findDateRepair(@RequestBody FaultRepair faultRepair){
        return repairService.findDateRepair(faultRepair);
    }


}
