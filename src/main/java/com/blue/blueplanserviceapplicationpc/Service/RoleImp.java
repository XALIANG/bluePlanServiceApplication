package com.blue.blueplanserviceapplicationpc.Service;

import com.blue.blueplanserviceapplicationpc.Model.Role;
import com.blue.blueplanserviceapplicationpc.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleImp {

    @Autowired
    RoleDao roleDao;

    public List<Role> obtainRoleList(){
        List<Role> roles = roleDao.getRoleList();
        return roles;
    }

}
