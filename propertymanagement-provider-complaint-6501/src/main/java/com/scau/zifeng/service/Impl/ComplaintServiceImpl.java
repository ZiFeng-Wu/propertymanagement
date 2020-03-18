package com.scau.zifeng.service.Impl;

import com.scau.zifeng.entities.Complaint;
import com.scau.zifeng.entities.ComplaintExample;
import com.scau.zifeng.jsonFormat.JsonFormat;
import com.scau.zifeng.mapper.ComplaintMapper;
import com.scau.zifeng.service.ComplaintClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintClientService {

    @Autowired
    private ComplaintMapper complaintMapper;

    @Override
    public int addComplaint(Complaint complaint) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        complaint.setDate(date);
        return complaintMapper.insert(complaint);
    }



    @Override
    public JsonFormat findDate(String page,String limit ,String date)  throws Exception{

        int limit2 = Integer.parseInt(limit);
        int page2 = Integer.parseInt(page);
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
        Date date2 = format1.parse(date);
        ComplaintExample complaintExample = new ComplaintExample();
        ComplaintExample.Criteria c = complaintExample.createCriteria();
        List<Complaint> list = complaintMapper.selectByExample(complaintExample);
        List<Complaint> list2 = new ArrayList<Complaint>();
        List<Complaint> list3 = new ArrayList<Complaint>();
        Calendar cal1 =Calendar.getInstance();
        cal1.setTime(date2);
        Calendar cal2 =Calendar.getInstance();
        for(Complaint s1:list){
            cal2.setTime(s1.getDate());
            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)){
                {
                    if(limit2>1&&page2==1){
                        limit2--;
                        list2.add(s1);

                    }
                    else{
                        limit2--;
                        if(limit2==1){
                            page2--;
                            limit2=10;
                        }
                    }
                    list3.add(s1);
                }

            }

        }
        JsonFormat jsonFormat = new JsonFormat();
        jsonFormat.setCount(list3.size()+"");
        jsonFormat.setData(list2);
        return jsonFormat;
    }
}
