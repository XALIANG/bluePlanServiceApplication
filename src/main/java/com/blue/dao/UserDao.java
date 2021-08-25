package com.blue.dao;

import com.blue.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    //查
    public User findUserId(Integer id) {
        String sql = "select * from blue_user where u_id = ?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<User>(User.class));
        return user;
    }

    // 增加
    public void addUser(User user) {
        String sql = "insert into blue_user(username,password,user_account,role_id) values(?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{user.getUserName(), user.getPassword(), user.getUserAccount(), user.getRoleId()});
    }

    public int deleteUser(Integer uid) {
        String sql = "delete from blue_user where u_id = ?";
        int count = jdbcTemplate.update(sql, new Object[]{uid});
        return count;
    }
    //更新
    public int updateUser(User user) {
        String sql = "update blue_user set user_account = ? , username = ?, password = ? where u_id = ?";
        int count = jdbcTemplate.update(sql, new Object[]{user.getUserAccount(), user.getUserName(), user.getPassword(), user.getuId()});
        return count;
    }

    @Resource
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
