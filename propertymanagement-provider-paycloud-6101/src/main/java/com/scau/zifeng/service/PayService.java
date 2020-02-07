package com.scau.zifeng.service;

import com.scau.zifeng.entities.PayList;

import java.util.Date;
import java.util.List;

public interface PayService {
    //查看当前用户待缴费清单
    public List<PayList> findSelfPay(Long uId);

    //修改缴费状态
    public int changePayStatus(Long Pid);

    //按时间查看缴费信息
    public List<PayList> findTimePay(PayList payList);

    //物业管理员插入缴费信息
    public int addPay(PayList payList);

    //物业管理员获取系统当前所有未缴费清单
    public List<PayList> findAllNoPay();

    //物业管理员传入uID来查询该用户所有缴费信息
    public List<PayList> findAllPay(Long uId);
}
