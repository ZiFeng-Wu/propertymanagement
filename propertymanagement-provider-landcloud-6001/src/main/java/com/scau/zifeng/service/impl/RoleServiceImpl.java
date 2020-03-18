package com.scau.zifeng.service.impl;

import com.scau.zifeng.entities.Role;
import com.scau.zifeng.entities.RoleExample;
import com.scau.zifeng.jsonFormat.JsonFormat;
import com.scau.zifeng.mapper.RoleMapper;
import com.scau.zifeng.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    public JsonFormat get(String page,String limit) {
        JsonFormat jsonFormat = new JsonFormat();
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria c = roleExample.createCriteria();
        jsonFormat.setCount(roleMapper.selectByExample(roleExample).size()+"");
        int page2 = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
        roleExample.setOrderByClause("id limit "+page2+","+limit);
        jsonFormat.setData(roleMapper.selectByExample(roleExample));
        return jsonFormat;
    }

    @Override
    public int delete(Role role) {
        return roleMapper.deleteByPrimaryKey(role.getId());
    }
}
