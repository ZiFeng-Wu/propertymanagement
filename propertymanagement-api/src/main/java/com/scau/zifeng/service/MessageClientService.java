package com.scau.zifeng.service;

import com.scau.zifeng.entities.MessageBoard;
import com.scau.zifeng.jsonFormat.JsonFormat;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="propertymanagement-messagecloud")
public interface MessageClientService {

    //住户留言
    @RequestMapping(value="/message/addmessage",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public int addMessage(@RequestBody MessageBoard messageBoard);

    //查看留言板
    @RequestMapping(value="/message/findmessage",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JsonFormat findMessage(@RequestParam("page") String page, @RequestParam("limit") String limit);
}
