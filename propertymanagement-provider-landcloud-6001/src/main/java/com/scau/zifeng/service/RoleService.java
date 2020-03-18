package com.scau.zifeng.service;

import com.scau.zifeng.entities.Role;
import com.scau.zifeng.jsonFormat.JsonFormat;

public interface RoleService {
    public int add(Role role);

    public int update(Role role);

    public JsonFormat get(String page,String limit);

    public int delete(Role role);
}
