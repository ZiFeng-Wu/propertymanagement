package com.scau.zifeng.service.Impl;

import com.scau.zifeng.entities.FaultRepair;
import com.scau.zifeng.entities.FaultRepairExample;
import com.scau.zifeng.jsonFormat.JsonFormat;
import com.scau.zifeng.mapper.FaultRepairMapper;
import com.scau.zifeng.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {

    @Autowired
    private FaultRepairMapper faultRepairMapper;

    @Override
    public int addRepair(FaultRepair faultRepair) {
        faultRepair.setStatus("未处理");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        faultRepair.setDate(date);
        return faultRepairMapper.insert(faultRepair);
    }

    @Override
    public int changeStatus(FaultRepair faultRepair) {
        FaultRepair faultRepair2 = faultRepairMapper.selectByPrimaryKey(faultRepair.getId());
        faultRepair2.setStatus("已处理");
        faultRepair2.setDeal(faultRepair.getDeal());
        faultRepair2.setFeedback(faultRepair.getFeedback());
        return faultRepairMapper.updateByPrimaryKey(faultRepair2);
    }

    @Override
    public JsonFormat findselfRepair(Long id, String page, String limit) {
        FaultRepairExample faultRepairExample = new FaultRepairExample();
        FaultRepairExample.Criteria c = faultRepairExample.createCriteria();
        c.andOriginatorEqualTo(id);
        JsonFormat jsonFormat = new JsonFormat();
        jsonFormat.setCount(faultRepairMapper.selectByExample(faultRepairExample).size()+"");
        int page2 = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
        faultRepairExample.setOrderByClause("id limit "+page2+","+limit);
        jsonFormat.setData(faultRepairMapper.selectByExample(faultRepairExample));
        return jsonFormat;
    }

    @Override
    public JsonFormat findNoRepair(String page,String limit) {
        FaultRepairExample faultRepairExample = new FaultRepairExample();
        FaultRepairExample.Criteria c = faultRepairExample.createCriteria();
        c.andStatusEqualTo("未处理");
        JsonFormat jsonFormat = new JsonFormat();
        jsonFormat.setCount(faultRepairMapper.selectByExample(faultRepairExample).size()+"");
        int page2 = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
        faultRepairExample.setOrderByClause("id limit "+page2+","+limit);
        jsonFormat.setData(faultRepairMapper.selectByExample(faultRepairExample));
        return jsonFormat;
    }

    @Override
    public JsonFormat findDateRepair(String page,String limit, String date) throws Exception{
        FaultRepairExample faultRepairExample = new FaultRepairExample();
        FaultRepairExample.Criteria c = faultRepairExample.createCriteria();
        JsonFormat jsonFormat = new JsonFormat();
        if (date==null||date.equals("")){
            List<FaultRepair> list = faultRepairMapper.selectByExample(faultRepairExample);
            jsonFormat.setCount(list.size()+"");
            int page2 = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
            faultRepairExample.setOrderByClause("id limit "+page2+","+limit);
            jsonFormat.setData(faultRepairMapper.selectByExample(faultRepairExample));
        }
        else{
            int limit2 = Integer.parseInt(limit);
            int page2 = Integer.parseInt(page);
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
            Date date2 = format1.parse(date);
            List<FaultRepair> list = faultRepairMapper.selectByExample(faultRepairExample);
            List<FaultRepair> list2 = new ArrayList<FaultRepair>();
            List<FaultRepair> list3 = new ArrayList<FaultRepair>();

            Calendar cal1 =Calendar.getInstance();
            cal1.setTime(date2);
            Calendar cal2 =Calendar.getInstance();
            for(FaultRepair s1:list){
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
            jsonFormat.setCount(list3.size()+"");
            jsonFormat.setData(list2);
        }

        return jsonFormat;
    }
}
