package com.scau.zifeng.controller;

import com.scau.zifeng.entities.Complaint;
import com.scau.zifeng.jsonFormat.JsonFormat;
import com.scau.zifeng.service.ComplaintClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ComplaintController_Feign {
    @Autowired
    private ComplaintClientService complaintClientService;

    @RequestMapping(value="/consumer/complaint/addcomplaint",method = RequestMethod.POST)
    public int addComplaint(@RequestBody Complaint complaint){
        return this.complaintClientService.addComplaint(complaint);
    }

    //传入日期查看投诉
    @RequestMapping(value="/consumer/complaint/finddate",method = RequestMethod.GET)
    public @ResponseBody
    JsonFormat findDate(@RequestParam("page") String page, @RequestParam("limit") String limit, @RequestParam("date") String date) throws Exception {
        return this.complaintClientService.findDate(page,limit,date);
    }
}
