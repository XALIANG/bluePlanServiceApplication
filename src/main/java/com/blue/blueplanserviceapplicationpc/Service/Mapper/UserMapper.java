package com.blue.blueplanserviceapplicationpc.Service.Mapper;

import com.blue.blueplanserviceapplicationpc.Model.User;
import com.blue.blueplanserviceapplicationpc.exception.BlueMAllException;

import java.util.Date;

/**
 *  用户管理接口
 * @author alaing
 */
public interface UserMapper {
    void register(String username, String password) throws BlueMAllException;

    User userLogin(String userName,String password);


}
