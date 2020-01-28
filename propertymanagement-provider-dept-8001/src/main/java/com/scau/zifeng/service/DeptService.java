package com.scau.zifeng.service;

import java.util.List;

import com.scau.zifeng.entities.Dept;

public interface DeptService
{
    public boolean add(Dept dept);
    public Dept    get(Long id);
    public List<Dept> list();
}