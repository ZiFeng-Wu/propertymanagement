package com.scau.zifeng.service;

import com.scau.zifeng.entities.MaterialPurchase;

import java.util.List;

public interface MateriaService {

    //插入物资采购清单
    public int addMateria(MaterialPurchase materialPurchase);

    //传入个人id查看物资采购历史
    public List<MaterialPurchase> findselfMateria(Long id);

    //查看当前所有未审批物资采购清单
    public List<MaterialPurchase> findNoDeal();

    //传入日期查看物资采购清单
    public List<MaterialPurchase> findDate(MaterialPurchase materialPurchase);

    //审批采购清单
    public int changeStatus(Long id);

}
