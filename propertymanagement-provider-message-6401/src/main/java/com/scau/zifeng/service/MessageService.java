package com.scau.zifeng.service;

import com.scau.zifeng.entities.MessageBoard;

import java.util.List;

public interface MessageService {

    //住户留言
    public int addMessage(MessageBoard messageBoard);

    //查看留言板
    public List<MessageBoard> findMessage();
}
