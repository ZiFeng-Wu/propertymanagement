package com.scau.zifeng.controller;

import com.scau.zifeng.entities.MessageBoard;
import com.scau.zifeng.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;


    //住户留言
    @RequestMapping(value="/message/addmessage",method = RequestMethod.POST)
    public int addMessage(@RequestBody MessageBoard messageBoard){
        return messageService.addMessage(messageBoard);
    }

    //查看留言板
    @RequestMapping(value="/message/findmessage",method = RequestMethod.GET)
    public @ResponseBody
    List<MessageBoard> findMessage(){
        return messageService.findMessage();
    }
}
