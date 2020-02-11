package com.scau.zifeng.service.Impl;

import com.scau.zifeng.entities.Complaint;
import com.scau.zifeng.entities.ComplaintExample;
import com.scau.zifeng.mapper.ComplaintMapper;
import com.scau.zifeng.service.ComplaintClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintClientService {

    @Autowired
    private ComplaintMapper complaintMapper;

    @Override
    public int addComplaint(Complaint complaint) {
        return complaintMapper.insert(complaint);
    }

    @Override
    public List<Complaint> findDate(Complaint complaint) {
        ComplaintExample complaintExample = new ComplaintExample();
        ComplaintExample.Criteria c = complaintExample.createCriteria();
        List<Complaint> list = complaintMapper.selectByExample(complaintExample);
        List<Complaint> list2 = new ArrayList<Complaint>();
        Calendar cal1 =Calendar.getInstance();
        cal1.setTime(complaint.getDate());
        Calendar cal2 =Calendar.getInstance();
        for(Complaint s1:list){
            cal2.setTime(s1.getDate());
            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)){
                list2.add(s1);
            }
        }
        return list2;

    }
}
