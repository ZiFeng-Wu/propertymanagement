package com.scau.zifeng.service;

import com.scau.zifeng.entities.Role;

import java.util.List;

public interface RoleService {
    public int add(Role role);

    public int update(Role role);

    public List<Role> get();

    public int delete(Role role);
}
