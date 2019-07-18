package com.lin.controller;

import com.lin.domain.User;
import com.lin.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @auther : lin
 * @date : 2019/7/2 11:39
 * @desc :
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        log.info(Thread.currentThread().getName());
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from account");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
//            System.out.println(resultSet.getString(1));
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        log.info(dataSource.toString());
        return "hello,spring boot!";
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAll() {
        List<User> all = userService.findAll();
        if(all==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(all);
    }

    @GetMapping("/commonSelectByExample")
    public List<User> commonFindAll(){
        return userService.commonSelectByExample();
    }
}
