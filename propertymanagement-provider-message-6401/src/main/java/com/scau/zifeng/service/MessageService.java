package com.scau.zifeng.service;

import com.scau.zifeng.entities.MessageBoard;
import com.scau.zifeng.jsonFormat.JsonFormat;

public interface MessageService {

    //住户留言
    public int addMessage(MessageBoard messageBoard);

    //查看留言板
    public JsonFormat findMessage(String limit,String page);
}
