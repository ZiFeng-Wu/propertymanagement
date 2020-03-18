package com.scau.zifeng.controller;

import com.scau.zifeng.config.RedisUtils;
import com.scau.zifeng.entities.MessageBoard;
import com.scau.zifeng.jsonFormat.JsonFormat;
import com.scau.zifeng.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private RedisUtils redisUtils;


    //住户留言
    @RequestMapping(value="/message/addmessage",method = RequestMethod.POST)
    public int addMessage(@RequestBody MessageBoard messageBoard){
        redisUtils.remove("findmessage");
        return messageService.addMessage(messageBoard);
    }

    //查看留言板
    @RequestMapping(value="/message/findmessage",method = RequestMethod.GET)
    public @ResponseBody
    JsonFormat findMessage(@RequestParam("page") String page,@RequestParam("limit") String limit){
        boolean haskey = redisUtils.exists("findmessage");
        if(!haskey)
            redisUtils.set("findmessage",messageService.findMessage(page,limit));
        return (JsonFormat)redisUtils.get("findmessage");
//            return messageService.findMessage(page,limit);
    }

}
