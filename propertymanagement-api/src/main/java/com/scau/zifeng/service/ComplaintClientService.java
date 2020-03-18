package com.scau.zifeng.service;

import com.scau.zifeng.entities.Complaint;
import com.scau.zifeng.jsonFormat.JsonFormat;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="propertymanagement-complaintcloud")
public interface ComplaintClientService {

    @RequestMapping(value="/complaint/addcomplaint",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int addComplaint(@RequestBody Complaint complaint);

    //传入日期查看投诉
    @RequestMapping(value="/complaint/finddate",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JsonFormat findDate(@RequestParam("page") String page, @RequestParam("limit") String limit, @RequestParam("date") String date) throws Exception;

}
