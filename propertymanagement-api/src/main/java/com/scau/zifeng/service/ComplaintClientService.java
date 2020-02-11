package com.scau.zifeng.service;

import com.scau.zifeng.entities.Complaint;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(value="propertymanagement-complaintcloud")
public interface ComplaintClientService {

    @RequestMapping(value="/complaint/addcomplaint",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int addComplaint(@RequestBody Complaint complaint);

    //传入日期查看投诉
    @RequestMapping(value="/complaint/finddate",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Complaint> findDate(@RequestBody Complaint complaint);
}
