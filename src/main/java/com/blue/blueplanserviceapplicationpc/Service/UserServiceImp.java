package com.blue.blueplanserviceapplicationpc.Service;

import com.blue.blueplanserviceapplicationpc.Model.User;
import com.blue.blueplanserviceapplicationpc.Service.Mapper.UserMapper;
import com.blue.blueplanserviceapplicationpc.common.Result;
import com.blue.blueplanserviceapplicationpc.dao.UserDao;
import com.blue.blueplanserviceapplicationpc.exception.BlueExceptionEnum;
import com.blue.blueplanserviceapplicationpc.exception.BlueMAllException;
import com.blue.blueplanserviceapplicationpc.utils.RxUilts;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * 用户实现类
 */

@Service
public class UserServiceImp implements UserMapper {

    @Autowired
    UserDao userDao;

    public User findUserById(Long id){
        User user = userDao.selectByPrimaryKey(id);
        return user;
    };
    /**
     *
     * @param username
     * @param password
     * @throws BlueMAllException
     */
    @Override
    public void register(String username, String password) throws BlueMAllException {
        User resultUser = userDao.selectByName(username);
        if (null != resultUser && resultUser.getUserName() != null) {
            throw new BlueMAllException(BlueExceptionEnum.NAME_EXISTED);
        }
        User user = new User();
//        Date currentDate = new Date();
        user.setUserId(RxUilts.getUUID());
        user.setUserName(username);
        user.setUserPassword(password);
        int count = userDao.insertUser(user);
        if (count == 0) {
            throw new BlueMAllException(BlueExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public User userLogin(String userName,String password) {
        User user = userDao.selectByName(userName);
        return user;
    }


}
