package com.lin.domain;

import lombok.Data;

import java.util.Date;

/**
 * @auther : lin
 * @date : 2019/6/20 16:42
 * @desc :
 */
@Data
public class User {
    private String username;
    private String password;
    private Integer age;
    private Date date;
}
