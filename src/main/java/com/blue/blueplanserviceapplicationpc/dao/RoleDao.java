package com.blue.blueplanserviceapplicationpc.dao;

import com.blue.blueplanserviceapplicationpc.Model.Role;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface RoleDao {
    List<Role> getRoleList();
}
