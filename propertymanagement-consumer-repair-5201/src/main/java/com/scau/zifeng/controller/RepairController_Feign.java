package com.scau.zifeng.controller;


import com.scau.zifeng.entities.FaultRepair;
import com.scau.zifeng.service.RepairClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RepairController_Feign {
    @Autowired
    private RepairClientService repairClientService;

    //插入故障报修
    @RequestMapping(value="/consumer/repair/addrepair",method = RequestMethod.POST)
    public int addRepair(@RequestBody FaultRepair faultRepair){
        return this.repairClientService.addRepair(faultRepair);
    }

    //修改故障报修状态
    @RequestMapping(value="/consumer/repair/changestatus",method = RequestMethod.POST)
    public int changeStatus(@RequestBody FaultRepair faultRepair){
        return this.repairClientService.changeStatus(faultRepair);
    }

    //查找个人故障报修历史
    @RequestMapping(value="/consumer/repair/findselfrepair/{id}",method = RequestMethod.GET)
    public @ResponseBody
    List<FaultRepair> findselfRepair(@PathVariable("id") Long id){
        return this.repairClientService.findselfRepair(id);
    }

    //查找当前未处理故障
    @RequestMapping(value = "/consumer/repair/findnorepair",method = RequestMethod.GET)
    public @ResponseBody List<FaultRepair> findNoRepair(){
        return this.repairClientService.findNoRepair();
    }

    //按日期查找故障报修清单
    @RequestMapping(value="/consumer/repair/finddaterepair",method = RequestMethod.GET)
    public @ResponseBody List<FaultRepair> findDateRepair(@RequestBody FaultRepair faultRepair){
        return this.repairClientService.findDateRepair(faultRepair);
    }

}
