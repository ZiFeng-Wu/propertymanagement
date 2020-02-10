package com.scau.zifeng.service.Impl;

import com.scau.zifeng.entities.MessageBoard;
import com.scau.zifeng.entities.MessageBoardExample;
import com.scau.zifeng.mapper.MessageBoardMapper;
import com.scau.zifeng.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    private MessageBoardMapper messageBoardMapper;
    @Override
    public int addMessage(MessageBoard messageBoard) {
        return messageBoardMapper.insert(messageBoard);
    }

    @Override
    public List<MessageBoard> findMessage() {
        return messageBoardMapper.selectByExample(new MessageBoardExample());
    }
}
