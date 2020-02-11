package com.scau.zifeng.controller;

import com.scau.zifeng.entities.Complaint;
import com.scau.zifeng.service.ComplaintClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    List<Complaint> findDate(@RequestBody Complaint complaint){
        return this.complaintClientService.findDate(complaint);
    }
}
