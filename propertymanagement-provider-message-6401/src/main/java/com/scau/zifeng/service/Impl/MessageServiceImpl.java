package com.scau.zifeng.service.Impl;

import com.scau.zifeng.entities.MessageBoard;
import com.scau.zifeng.entities.MessageBoardExample;
import com.scau.zifeng.jsonFormat.JsonFormat;
import com.scau.zifeng.mapper.MessageBoardMapper;
import com.scau.zifeng.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    private MessageBoardMapper messageBoardMapper;
    @Override
    public int addMessage(MessageBoard messageBoard) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        messageBoard.setDate(date);
        return messageBoardMapper.insert(messageBoard);
    }

    @Override
        public JsonFormat findMessage(String limit,String page) {
        JsonFormat jsonFormat = new JsonFormat();
        MessageBoardExample messageBoardExample = new MessageBoardExample();
        MessageBoardExample.Criteria c = messageBoardExample.createCriteria();
        jsonFormat.setCount(messageBoardMapper.selectByExample(messageBoardExample).size()+"");
        int page2 = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
        messageBoardExample.setOrderByClause("id limit "+page2+","+limit);
        jsonFormat.setData((messageBoardMapper.selectByExample(messageBoardExample)));
        return jsonFormat;
    }
}
