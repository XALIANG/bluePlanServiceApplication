package com.blue.blueplanserviceapplicationpc.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  用户
 */

@RestController
public class UserController {

    @GetMapping("/blue/user")
    public String firstRequest(){
        return "第一个spring boot 接口";
    }

}
