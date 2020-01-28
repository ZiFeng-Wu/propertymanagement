package com.scau.zifeng.service.impl;

import java.util.List;


import com.scau.zifeng.dao.DeptDao;
import com.scau.zifeng.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scau.zifeng.entities.Dept;
import com.scau.zifeng.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService
{
    @Autowired
    private DeptDao dao ;

    @Override
    public boolean add(Dept dept)
    {
        return dao.addDept(dept);
    }

    @Override
    public Dept get(Long id)
    {
        return dao.findById(id);
    }

    @Override
    public List<Dept> list()
    {
        return dao.findAll();
    }

}
