package com.blue.entity;

import com.blue.dao.UserDao;
import com.blue.model.User;
import com.blue.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserDao userDao = context.getBean("UserDao", UserDao.class);
        UserService userService = new UserService();
        System.out.println(userService.getUser());

    }
}
