package com.lin.domain;

import lombok.Data;

/**
 * @auther : lin
 * @date : 2019/6/14 12:44
 * @desc :
 */
@Data
public class User {
    private int id;
    private String name;
    private String gender;
    private int age;
    private String address;
    private String qq;
    private String email;

    private String username;
    private String password;
}
