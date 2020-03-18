package com.scau.zifeng.controller;

import com.scau.zifeng.config.RedisUtils;
import com.scau.zifeng.entities.Complaint;
import com.scau.zifeng.jsonFormat.JsonFormat;
import com.scau.zifeng.service.ComplaintClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ComplaintController {
    @Autowired
    private ComplaintClientService complaintService;
    @Autowired
    private RedisUtils redisUtils;

    //传入投诉
    @RequestMapping(value="/complaint/addcomplaint",method = RequestMethod.POST)
    public int addComplaint(@RequestBody Complaint complaint){
        redisUtils.remove("finddate");
        return complaintService.addComplaint(complaint);
    }

    //传入日期查看投诉
    @RequestMapping(value="/complaint/finddate",method = RequestMethod.GET)
    public @ResponseBody
    JsonFormat findDate(@RequestParam("page") String page, @RequestParam("limit") String limit, @RequestParam("date") String date) throws Exception {
        boolean haskey = redisUtils.exists("finddate");
        if(!haskey)
            redisUtils.set("finddate",complaintService.findDate(page,limit,date));
        return (JsonFormat) redisUtils.get("finddate");
    }
}
