package com.scau.zifeng.service.Impl;

import com.netflix.discovery.converters.Auto;
import com.scau.zifeng.entities.PayList;
import com.scau.zifeng.entities.PayListExample;
import com.scau.zifeng.mapper.PayListMapper;
import com.scau.zifeng.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private PayListMapper payListMapper;

    @Override
    public List<PayList> findSelfPay(Long uId) {
        PayListExample payListExample = new PayListExample();
        PayListExample.Criteria c = payListExample.createCriteria();
        c.andUIdEqualTo(uId);
        c.andStatusEqualTo("未缴清");
        return payListMapper.selectByExample(payListExample);
    }

    @Override
    public int changePayStatus(Long Pid) {
        PayList payList = payListMapper.selectByPrimaryKey(Pid);
        payList.setStatus("已缴清");
        return payListMapper.updateByPrimaryKey(payList);
    }

    @Override
    public List<PayList> findTimePay(PayList payList) {
        PayListExample payListExample = new PayListExample();
        PayListExample.Criteria c = payListExample.createCriteria();
        c.andUIdEqualTo(payList.getuId());
        List<PayList> list = payListMapper.selectByExample(payListExample);
        List<PayList> list2 = new ArrayList<PayList>();
        Calendar cal1 =Calendar.getInstance();
        cal1.setTime(payList.getDate());
        Calendar cal2 =Calendar.getInstance();
        for(PayList s1:list){
            cal2.setTime(s1.getDate());
            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)){
                list2.add(s1);
            }

        }
        return list2;
    }

    @Override
    public int addPay(PayList payList) {
        return payListMapper.insert(payList);
    }

    @Override
    public List<PayList> findAllNoPay() {
        PayListExample payListExample = new PayListExample();
        PayListExample.Criteria c = payListExample.createCriteria();
        c.andStatusEqualTo("未缴清");
        return payListMapper.selectByExample(payListExample);
    }

    @Override
    public List<PayList> findAllPay(Long uId) {
        PayListExample payListExample = new PayListExample();
        PayListExample.Criteria c = payListExample.createCriteria();
        c.andUIdEqualTo(uId);
        return payListMapper.selectByExample(payListExample);
    }
}
