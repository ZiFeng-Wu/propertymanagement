package com.scau.zifeng.service;

import com.scau.zifeng.entities.PayList;
import com.scau.zifeng.jsonFormat.JsonFormat;

public interface PayService {
    //查看当前用户待缴费清单
    public JsonFormat findSelfPay(Long uId,String page,String limit);

    //居民缴费修改缴费状态&&//管理员修改缴费状态
    public int changePayStatus(Long Pid,String payNum);


    //按时间查看缴费信息
    public JsonFormat findTimePay(String page, String limit, Long uId, String date) throws Exception;

    //物业管理员插入缴费信息
    public int addPay(PayList payList);

    //物业管理员获取系统当前所有未缴费清单
    public JsonFormat findAllNoPay(String page,String limit);

    //物业管理员获取系统当前所有未审批缴费信息
    public JsonFormat findAllNoCheck(String page,String limit);

    //物业管理员传入uID来查询该用户所有缴费信息(带分页)
    public JsonFormat findAllPay(Long uId, String page, String limit);
}
