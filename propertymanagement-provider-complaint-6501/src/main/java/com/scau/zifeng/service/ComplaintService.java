package com.scau.zifeng.service;

import com.scau.zifeng.entities.Complaint;
import com.scau.zifeng.jsonFormat.JsonFormat;

public interface ComplaintService {
    //传入投诉
    public int addComplaint(Complaint complaint);

    //传入日期查看投诉
    public JsonFormat findDate(String page,String limit ,String date);

}
