package com.blue.blueplanserviceapplicationpc.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.blue.blueplanserviceapplicationpc.Model.User;

import java.util.Date;

public class TokenUtil {
    /**
     * @author Zeng
     * @date 2020/2/17 10:56
     */
    //设置过期时间为1个小时
    private static final Long EXPIRE_TIME = Long.valueOf(1 * 60 * 60 * 1000);

    public static String getToken(User user) {
        String token = "";
        Date date = new Date();
        date.setTime(System.currentTimeMillis() + EXPIRE_TIME); //设置token的过期时间为1小时
        token = JWT.create().withAudience(String.valueOf(user.getUserId())).withExpiresAt(date).sign(Algorithm.HMAC256(user.getUserPassword())); //使用HMAC256生成token,密钥是用户的密码
        return token;

    }
}
