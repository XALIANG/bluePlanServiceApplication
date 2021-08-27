package com.blue.blueplanserviceapplicationpc.Service.Mapper;

import com.blue.blueplanserviceapplicationpc.Model.User;

/**
 *  用户管理接口
 * @author alaing
 */

public interface UserMapper {

    User findUser(Integer id);

    int addUser(User user);



}
