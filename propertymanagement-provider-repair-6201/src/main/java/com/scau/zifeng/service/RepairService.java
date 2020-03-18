package com.scau.zifeng.service;

import com.scau.zifeng.entities.FaultRepair;
import com.scau.zifeng.jsonFormat.JsonFormat;

public interface RepairService {
    //插入故障报修
    public int addRepair(FaultRepair faultRepair);

    //修改故障报修状态
    public int changeStatus(FaultRepair faultRepair);

    //查找个人故障报修历史
    public JsonFormat findselfRepair(Long id,String page,String limit);

    //查找当前未处理故障
    public JsonFormat findNoRepair(String page,String limit);

    //按日期查找故障报修清单
    public JsonFormat findDateRepair(String page,String limit, String date) throws Exception;

}
