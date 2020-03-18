package com.scau.zifeng.service.Impl;

import com.scau.zifeng.entities.MaterialPurchase;
import com.scau.zifeng.entities.MaterialPurchaseExample;
import com.scau.zifeng.jsonFormat.JsonFormat;
import com.scau.zifeng.mapper.MaterialPurchaseMapper;
import com.scau.zifeng.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService {

    @Autowired
    private MaterialPurchaseMapper materialPurchaseMapper;

    @Override
    public int addMateria(MaterialPurchase materialPurchase) {
        materialPurchase.setStatus("未处理");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        materialPurchase.setDate(date);
        return materialPurchaseMapper.insert(materialPurchase);
    }

    @Override
    public JsonFormat findselfMateria(Long id,String page,String limit) {
        MaterialPurchaseExample materialPurchaseExample = new MaterialPurchaseExample();
        MaterialPurchaseExample.Criteria c = materialPurchaseExample.createCriteria();
        c.andUIdEqualTo(id);
        JsonFormat jsonFormat = new JsonFormat();
        jsonFormat.setCount(materialPurchaseMapper.selectByExample(materialPurchaseExample).size()+"");
        int page2 = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
        materialPurchaseExample.setOrderByClause("id limit "+page2+","+limit);
        jsonFormat.setData(materialPurchaseMapper.selectByExample(materialPurchaseExample));
        return jsonFormat;
    }

    @Override
    public JsonFormat findNoDeal(String page,String limit) {
        MaterialPurchaseExample materialPurchaseExample = new MaterialPurchaseExample();
        MaterialPurchaseExample.Criteria c = materialPurchaseExample.createCriteria();
        c.andStatusEqualTo("未处理");
        JsonFormat jsonFormat = new JsonFormat();
        jsonFormat.setCount(materialPurchaseMapper.selectByExample(materialPurchaseExample).size()+"");
        int page2 = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
        materialPurchaseExample.setOrderByClause("id limit "+page2+","+limit);
        jsonFormat.setData(materialPurchaseMapper.selectByExample(materialPurchaseExample));
        return jsonFormat;
    }

    @Override
    public JsonFormat history(String page,String limit) {
        MaterialPurchaseExample materialPurchaseExample = new MaterialPurchaseExample();
        MaterialPurchaseExample.Criteria c = materialPurchaseExample.createCriteria();
        JsonFormat jsonFormat = new JsonFormat();
        jsonFormat.setCount(materialPurchaseMapper.selectByExample(materialPurchaseExample).size()+"");
        int page2 = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
        materialPurchaseExample.setOrderByClause("id limit "+page2+","+limit);
        jsonFormat.setData(materialPurchaseMapper.selectByExample(materialPurchaseExample));
        return jsonFormat;
    }

    @Override
    public JsonFormat findDate(String page,String limit,String date) throws ParseException {
        int limit2 = Integer.parseInt(limit);
        int page2 = Integer.parseInt(page);
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
        Date date2 = format1.parse(date);
        MaterialPurchaseExample materialPurchaseExample = new MaterialPurchaseExample();
        MaterialPurchaseExample.Criteria c = materialPurchaseExample.createCriteria();
        List<MaterialPurchase> list = materialPurchaseMapper.selectByExample(materialPurchaseExample);
        List<MaterialPurchase> list2 = new ArrayList<MaterialPurchase>();
        List<MaterialPurchase> list3 = new ArrayList<MaterialPurchase>();
        Calendar cal1 =Calendar.getInstance();
        cal1.setTime(date2);
        Calendar cal2 =Calendar.getInstance();
        for(MaterialPurchase s1:list){
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
    public int changeStatus(Long id) {
        MaterialPurchase materialPurchase = materialPurchaseMapper.selectByPrimaryKey(id);
        materialPurchase.setStatus("已处理");
        return materialPurchaseMapper.updateByPrimaryKey(materialPurchase);
    }
}
