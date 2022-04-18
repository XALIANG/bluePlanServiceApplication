package com.blue.blueplanserviceapplicationpc.Service;

import com.blue.blueplanserviceapplicationpc.Model.User;
import com.blue.blueplanserviceapplicationpc.dao.UserMapper;
import com.blue.blueplanserviceapplicationpc.dao.UserDao;
import com.blue.blueplanserviceapplicationpc.exception.BlueExceptionEnum;
import com.blue.blueplanserviceapplicationpc.exception.BlueMAllException;
import com.blue.blueplanserviceapplicationpc.utils.RxUilts;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户实现类
 */

@Service
public class UserServiceImp implements UserMapper {

    @Autowired
    UserDao userDao;

    public User findUserById(String id) {
        User user = userDao.selectByPrimaryKey(id);
        return user;
    }

    ;

    /**
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
        user.setUserId(RxUilts.getUUID());
        user.setUserName(username);
        user.setUserPassword(password);
        user.setUserMoney(0);
        user.setCreateTime(new java.util.Date());
        user.setUpdateTime(new java.util.Date());
        int count = userDao.insertUser(user);
        if (count == 0) {
            throw new BlueMAllException(BlueExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public User userLogin(String userName, String password) {
        User user = userDao.selectByName(userName);
        return user;
    }

    @Override
    public PageInfo<User> getListPage(int page, int size) {
        List<User> userPage = userDao.getUserList();
        PageHelper.startPage(page, size);
        PageInfo<User> list = new PageInfo<>(userPage);
        return list;
    }

    @Override
    public void updateUserStatusCode(String userId, String userStatus) {
       int count =  userDao.updateUserStatusCode(userId, userStatus);
        System.out.println(count);

    }


}
