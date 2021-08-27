package com.blue.blueplanserviceapplicationpc.Service;

import com.blue.blueplanserviceapplicationpc.Model.User;
import com.blue.blueplanserviceapplicationpc.Service.Mapper.UserMapper;
import com.blue.blueplanserviceapplicationpc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户实现类
 */

@Service
public class UserServiceImp implements UserMapper {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserDao userDao;

    @Override
    public User findUser(Integer id) {
        User user = userDao.selectfindUser(id);
        return user;
    }

    @Override
    public int addUser(User user) {
        return 0;
    }
}
