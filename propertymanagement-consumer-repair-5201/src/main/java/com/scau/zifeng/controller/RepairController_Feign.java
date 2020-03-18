package com.scau.zifeng.controller;


import com.scau.zifeng.entities.FaultRepair;
import com.scau.zifeng.jsonFormat.JsonFormat;
import com.scau.zifeng.service.RepairClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    JsonFormat findselfRepair(@PathVariable("id") Long id, @RequestParam(value="page") String page, @RequestParam(value="limit") String limit){
        return this.repairClientService.findselfRepair(id,page,limit);
    }

    //查找当前未处理故障
    @RequestMapping(value = "/consumer/repair/findnorepair",method = RequestMethod.GET)
    public @ResponseBody JsonFormat findNoRepair(@RequestParam("page") String page,@RequestParam("limit") String limit){
        return this.repairClientService.findNoRepair(page,limit);
    }

    //按日期查找故障报修清单
    @RequestMapping(value="/consumer/repair/finddaterepair",method = RequestMethod.GET)
    public @ResponseBody JsonFormat findDateRepair(@RequestParam("page") String page,@RequestParam("limit") String limit,@RequestParam("date") String date){
        JsonFormat jsonFormat = this.repairClientService.findDateRepair(page,limit,date);
        return jsonFormat;
    }

}
