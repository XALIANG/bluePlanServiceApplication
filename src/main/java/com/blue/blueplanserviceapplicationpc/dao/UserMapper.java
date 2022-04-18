package com.blue.blueplanserviceapplicationpc.dao;

import com.blue.blueplanserviceapplicationpc.Model.User;
import com.blue.blueplanserviceapplicationpc.exception.BlueMAllException;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;

/**
 *  用户管理接口
 * @author alaing
 */

@Repository
public interface UserMapper {
    void register(String username, String password) throws BlueMAllException;

    User userLogin(String userName,String password);

    void updateUserStatusCode(String userId,String userStatus);

    PageInfo<User> getListPage(int page, int size);


}
