package com.blue.blueplanserviceapplicationpc.Controller;

import com.blue.blueplanserviceapplicationpc.Model.Role;
import com.blue.blueplanserviceapplicationpc.Service.RoleImp;
import com.blue.blueplanserviceapplicationpc.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class RoleController {
    @Autowired
    RoleImp roleImp;

    @GetMapping("/public/role_list")
    public Result obtainRoleList(){
        List<Role> roles = roleImp.obtainRoleList();
        return Result.success("获取成功",roles);
    }
}
