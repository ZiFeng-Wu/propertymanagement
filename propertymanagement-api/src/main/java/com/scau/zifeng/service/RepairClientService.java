package com.scau.zifeng.service;

import com.scau.zifeng.entities.FaultRepair;
import com.scau.zifeng.jsonFormat.JsonFormat;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="propertymanagement-repaircloud")
public interface RepairClientService {
    //插入故障报修
    @RequestMapping(value="/repair/addrepair",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int addRepair(@RequestBody FaultRepair faultRepair);

    //修改故障报修状态
    @RequestMapping(value="/repair/changestatus",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int changeStatus(@RequestBody FaultRepair faultRepair);

    //查找个人故障报修历史
    @RequestMapping(value="/repair/findselfrepair/{id}",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JsonFormat findselfRepair(@PathVariable("id") Long id, @RequestParam(value="page") String page, @RequestParam(value="limit") String limit);

    //查找当前未处理故障
    @RequestMapping(value = "/repair/findnorepair",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JsonFormat findNoRepair(@RequestParam("page") String page,@RequestParam("limit") String limit);

    //按日期查找故障报修清单
    @RequestMapping(value="/repair/finddaterepair",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JsonFormat findDateRepair(@RequestParam("page") String page,@RequestParam("limit") String limit,@RequestParam("date") String date);
}
