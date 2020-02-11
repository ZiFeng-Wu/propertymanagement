package com.scau.zifeng.service;

import com.scau.zifeng.entities.Complaint;

import java.util.List;

public interface ComplaintService {
    //传入投诉
    public int addComplaint(Complaint complaint);

    //传入日期查看投诉
    public List<Complaint> findDate(Complaint complaint);

}
