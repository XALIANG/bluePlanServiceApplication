package com.blue.blueplanserviceapplicationpc.dao;


import com.blue.blueplanserviceapplicationpc.Model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User selectfindUser(Integer id);


}
