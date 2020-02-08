package com.scau.zifeng.service.Impl;

import com.scau.zifeng.entities.FaultRepair;
import com.scau.zifeng.entities.FaultRepairExample;
import com.scau.zifeng.mapper.FaultRepairMapper;
import com.scau.zifeng.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {

    @Autowired
    private FaultRepairMapper faultRepairMapper;

    @Override
    public int addRepair(FaultRepair faultRepair) {
        faultRepair.setStatus("未处理");
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
    public List<FaultRepair> findselfRepair(Long id) {
        FaultRepairExample faultRepairExample = new FaultRepairExample();
        FaultRepairExample.Criteria c = faultRepairExample.createCriteria();
        c.andOriginatorEqualTo(id);
        return faultRepairMapper.selectByExample(faultRepairExample);
    }

    @Override
    public List<FaultRepair> findNoRepair() {
        FaultRepairExample faultRepairExample = new FaultRepairExample();
        FaultRepairExample.Criteria c = faultRepairExample.createCriteria();
        c.andStatusEqualTo("未处理");
        return faultRepairMapper.selectByExample(faultRepairExample);
    }

    @Override
    public List<FaultRepair> findDateRepair(FaultRepair faultRepair) {
        FaultRepairExample payListExample = new FaultRepairExample();
        FaultRepairExample.Criteria c = payListExample.createCriteria();
        List<FaultRepair> list = faultRepairMapper.selectByExample(payListExample);
        List<FaultRepair> list2 = new ArrayList<FaultRepair>();
        Calendar cal1 =Calendar.getInstance();
        cal1.setTime(faultRepair.getDate());
        Calendar cal2 =Calendar.getInstance();
        for(FaultRepair s1:list){
            cal2.setTime(s1.getDate());
            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)){
                list2.add(s1);
            }
        }
        return list2;
    }
}
