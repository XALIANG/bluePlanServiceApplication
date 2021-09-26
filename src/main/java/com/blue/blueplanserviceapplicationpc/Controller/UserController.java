package com.blue.blueplanserviceapplicationpc.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blue.blueplanserviceapplicationpc.Model.User;
import com.blue.blueplanserviceapplicationpc.Service.UserServiceImp;
import com.blue.blueplanserviceapplicationpc.common.Result;
import com.blue.blueplanserviceapplicationpc.exception.BlueExceptionEnum;
import com.blue.blueplanserviceapplicationpc.exception.BlueMAllException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * 用户
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    private CacheManager cacheManager;

    @ApiOperation("查找用户")
    @ResponseBody
    public User findUsers(Integer id) {
        return userServiceImp.findUser(id);
    }

    @ApiOperation("注册用户")
    @PostMapping("/blue/register")
    @ResponseBody
    public Result register(@RequestParam("userName") String userName,@RequestParam("password") String password,@RequestParam("code") String code, HttpSession session) throws BlueMAllException {
        String serverCode = (String) session.getAttribute("code");
//        校验非空
        if (StringUtils.isEmpty(userName)) {
            return Result.error(BlueExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return Result.error(BlueExceptionEnum.NEED_PASSWORD);
        }
        if (password.length() < 8) {
            return Result.error(BlueExceptionEnum.PASSWORD_TOO_SHORT);
        }
        if (null == code || serverCode == null || !serverCode.equalsIgnoreCase(code)) {
            return Result.error(404, "验证码错误");
        }
        userServiceImp.register(userName, password);
        return Result.success("注册成功", 200);
    }

    @ApiOperation("用户登录")
    @PostMapping("/blue/login")
    public Result userLogin(@RequestParam("userName") String userName,@RequestParam("password") String password,@RequestParam("code") String code, HttpSession session) {

        if(null == userName || password ==null){
            Result.error(400,"不得为空");
        }
        User cuUsrer =  userServiceImp.userLogin(userName,password);
        return Result.success(cuUsrer);
    }
}
