package com.lin.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @auther : lin
 * @date : 2019/6/20 16:44
 * @desc :
 */
@Data
public class Account {
    private String username;
    private String password;
    private Double money;

    // private User user;

    private List<User> list;
    private Map<String, User> map;
}
