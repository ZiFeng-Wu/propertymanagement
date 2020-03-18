package com.scau.zifeng.service.Impl;

import com.scau.zifeng.entities.PayList;
import com.scau.zifeng.entities.PayListExample;
import com.scau.zifeng.jsonFormat.JsonFormat;
import com.scau.zifeng.mapper.PayListMapper;
import com.scau.zifeng.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private PayListMapper payListMapper;

    @Override
    public JsonFormat findSelfPay(Long uId,String page,String limit) {
        PayListExample payListExample = new PayListExample();
        PayListExample.Criteria c = payListExample.createCriteria();
        c.andUIdEqualTo(uId);
        c.andStatusEqualTo("未缴清");
        JsonFormat jsonFormat = new JsonFormat();
        jsonFormat.setCode("0");
        jsonFormat.setMsg("");
        jsonFormat.setCount(payListMapper.selectByExample(payListExample).size()+"");
        int page2 = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
        payListExample.setOrderByClause("id limit "+page2+","+limit);
        jsonFormat.setData(payListMapper.selectByExample(payListExample));
        return jsonFormat;
    }

    @Override
    public int changePayStatus(Long Pid,String payNum) {
        PayList payList = payListMapper.selectByPrimaryKey(Pid);
        if(payNum.equals("-1"))
            payList.setStatus("已缴清");
        else
            payList.setStatus("待审核，订单号:"+payNum);
        return payListMapper.updateByPrimaryKey(payList);
    }



    @Override
    public JsonFormat findTimePay(String page,String limit,Long uId, String date) throws Exception{
        int limit2 = Integer.parseInt(limit);
        int page2 = Integer.parseInt(page);
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
        Date date2 = format1.parse(date);
        PayListExample payListExample = new PayListExample();
        PayListExample.Criteria c = payListExample.createCriteria();
        c.andUIdEqualTo(uId);
        List<PayList> list = payListMapper.selectByExample(payListExample);
        List<PayList> list2 = new ArrayList<PayList>();
        List<PayList> list3 = new ArrayList<PayList>();
        Calendar cal1 =Calendar.getInstance();
        cal1.setTime(date2);
        Calendar cal2 =Calendar.getInstance();
        for(PayList s1:list){
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

    @Override
    public int addPay(PayList payList) {
        payList.setStatus("未缴清");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        payList.setDate(date);
        return payListMapper.insert(payList);
    }

    @Override
    public  JsonFormat findAllNoPay(String page,String limit) {
        PayListExample payListExample = new PayListExample();
        PayListExample.Criteria c = payListExample.createCriteria();
        c.andStatusEqualTo("未缴清");
        JsonFormat jsonFormat = new JsonFormat();
        jsonFormat.setCount(payListMapper.selectByExample(payListExample).size()+"");
        int page2 = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
        payListExample.setOrderByClause("id limit "+page2+","+limit);
        jsonFormat.setData(payListMapper.selectByExample(payListExample));
        return jsonFormat;
    }

    @Override
    public  JsonFormat findAllNoCheck(String page,String limit) {
        PayListExample payListExample = new PayListExample();
        PayListExample.Criteria c = payListExample.createCriteria();
        c.andStatusNotEqualTo("已缴清");
        c.andStatusNotEqualTo("未缴清");
        JsonFormat jsonFormat = new JsonFormat();
        jsonFormat.setCount(payListMapper.selectByExample(payListExample).size()+"");
        int page2 = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
        payListExample.setOrderByClause("id limit "+page2+","+limit);
        jsonFormat.setData(payListMapper.selectByExample(payListExample));
        return jsonFormat;
    }

    @Override
    public JsonFormat findAllPay(Long uId, String page, String limit) {
        PayListExample payListExample = new PayListExample();
        PayListExample.Criteria c = payListExample.createCriteria();
        c.andUIdEqualTo(uId);
        JsonFormat jsonFormat = new JsonFormat();
        jsonFormat.setCount(payListMapper.selectByExample(payListExample).size()+"");
        int page2 = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
        payListExample.setOrderByClause("id limit "+page2+","+limit);
        jsonFormat.setData(payListMapper.selectByExample(payListExample));
        return jsonFormat;
    }
}
