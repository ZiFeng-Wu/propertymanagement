package com.scau.zifeng.controller;

import com.scau.zifeng.config.RedisUtils;
import com.scau.zifeng.entities.MessageBoard;
import com.scau.zifeng.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    List<MessageBoard> findMessage(){
        boolean haskey = redisUtils.exists("findmessage");
        if(!haskey)
            redisUtils.set("findmessage",messageService.findMessage());
        return (List<MessageBoard>)redisUtils.get("findmessage");
    }
}
