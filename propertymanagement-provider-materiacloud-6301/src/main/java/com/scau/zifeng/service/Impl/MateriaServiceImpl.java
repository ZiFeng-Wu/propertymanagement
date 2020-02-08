package com.scau.zifeng.service.Impl;

import com.scau.zifeng.entities.MaterialPurchase;
import com.scau.zifeng.entities.MaterialPurchaseExample;
import com.scau.zifeng.mapper.MaterialPurchaseMapper;
import com.scau.zifeng.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService {

    @Autowired
    private MaterialPurchaseMapper materialPurchaseMapper;

    @Override
    public int addMateria(MaterialPurchase materialPurchase) {
        materialPurchase.setStatus("未处理");
        return materialPurchaseMapper.insert(materialPurchase);
    }

    @Override
    public List<MaterialPurchase> findselfMateria(Long id) {
        MaterialPurchaseExample materialPurchaseExample = new MaterialPurchaseExample();
        MaterialPurchaseExample.Criteria c = materialPurchaseExample.createCriteria();
        c.andUIdEqualTo(id);
        return materialPurchaseMapper.selectByExample(materialPurchaseExample);
    }

    @Override
    public List<MaterialPurchase> findNoDeal() {
        MaterialPurchaseExample materialPurchaseExample = new MaterialPurchaseExample();
        MaterialPurchaseExample.Criteria c = materialPurchaseExample.createCriteria();
        c.andStatusEqualTo("未处理");
        return materialPurchaseMapper.selectByExample(materialPurchaseExample);
    }

    @Override
    public List<MaterialPurchase> findDate(MaterialPurchase materialPurchase) {
        MaterialPurchaseExample materialPurchaseExample = new MaterialPurchaseExample();
        MaterialPurchaseExample.Criteria c = materialPurchaseExample.createCriteria();
        List<MaterialPurchase> list = materialPurchaseMapper.selectByExample(materialPurchaseExample);
        List<MaterialPurchase> list2 = new ArrayList<MaterialPurchase>();
        Calendar cal1 =Calendar.getInstance();
        cal1.setTime(materialPurchase.getDate());
        Calendar cal2 =Calendar.getInstance();
        for(MaterialPurchase s1:list){
            cal2.setTime(s1.getDate());
            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)){
                list2.add(s1);
            }
        }
        return list2;
    }

    @Override
    public int changeStatus(Long id) {
        MaterialPurchase materialPurchase = materialPurchaseMapper.selectByPrimaryKey(id);
        materialPurchase.setStatus("已处理");
        return materialPurchaseMapper.updateByPrimaryKey(materialPurchase);
    }
}
