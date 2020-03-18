package com.scau.zifeng.service;

import com.scau.zifeng.entities.MaterialPurchase;
import com.scau.zifeng.jsonFormat.JsonFormat;

import java.text.ParseException;

public interface MateriaService {

    //插入物资采购清单
    public int addMateria(MaterialPurchase materialPurchase);

    //传入个人id查看物资采购历史
    public JsonFormat findselfMateria(Long id,String page,String limit);

    //查看当前所有未审批物资采购清单
    public JsonFormat findNoDeal(String page,String limit);

    //传入日期查看物资采购清单
    public JsonFormat findDate(String page,String limit,String date) throws ParseException;


    //查看当前所有物资采购清单历史
    public JsonFormat history(String page,String limit);

    //审批采购清单
    public int changeStatus(Long id);

}
