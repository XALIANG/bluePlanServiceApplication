package com.blue.blueplanserviceapplicationpc.dao;


import com.blue.blueplanserviceapplicationpc.Model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User selectedUser(Integer id);

    int deleteUser(Integer id);

    int updateUser(User user);

}
