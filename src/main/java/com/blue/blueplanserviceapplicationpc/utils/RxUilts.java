package com.blue.blueplanserviceapplicationpc.utils;

import java.util.UUID;

public class RxUilts {
    /**
     * 获取唯一的32位字符串
     *
     * @return
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

}
