package com.scau.zifeng.controller;

import com.scau.zifeng.entities.MessageBoard;
import com.scau.zifeng.service.MessageClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController_Feign {

    @Autowired
    private MessageClientService messageClientService;
    //住户留言
    @RequestMapping(value="/consumer/message/addmessage",method = RequestMethod.POST)
    public int addMessage(@RequestBody MessageBoard messageBoard){
        return this.messageClientService.addMessage(messageBoard);
    }

    //查看留言板
    @RequestMapping(value="/consumer/message/findmessage",method = RequestMethod.GET)
    public @ResponseBody
    List<MessageBoard> findMessage(){
        return this.messageClientService.findMessage();
    }
}
