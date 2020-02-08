package com.scau.zifeng.service;

import com.scau.zifeng.entities.FaultRepair;

import java.util.List;

public interface RepairService {
    //插入故障报修
    public int addRepair(FaultRepair faultRepair);

    //修改故障报修状态
    public int changeStatus(FaultRepair faultRepair);

    //查找个人故障报修历史
    public List<FaultRepair> findselfRepair(Long id);

    //查找当前未处理故障
    public List<FaultRepair> findNoRepair();

    //按日期查找故障报修清单
    public List<FaultRepair> findDateRepair(FaultRepair faultRepair);

}
