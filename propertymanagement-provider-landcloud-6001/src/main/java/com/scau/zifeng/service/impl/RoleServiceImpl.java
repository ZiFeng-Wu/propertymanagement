package com.scau.zifeng.service.impl;

import com.scau.zifeng.entities.Role;
import com.scau.zifeng.entities.RoleExample;
import com.scau.zifeng.mapper.RoleMapper;
import com.scau.zifeng.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public int add(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int update(Role role) {
        return roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public List<Role> get() {
        return roleMapper.selectByExample(new RoleExample());
    }

    @Override
    public int delete(Role role) {
        return roleMapper.deleteByPrimaryKey(role.getId());
    }
}
