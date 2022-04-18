package com.blue.blueplanserviceapplicationpc.dao;


import com.blue.blueplanserviceapplicationpc.Model.User;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Resource
public interface UserDao {

    User selectByPrimaryKey(String id);

    User selectByName(String userName);

    int insertUser(User user);

    int updateUserStatusCode(String userId,String userStatus);

    /**
     * 获取用户列表
     */
    List<User> getUserList();
}
