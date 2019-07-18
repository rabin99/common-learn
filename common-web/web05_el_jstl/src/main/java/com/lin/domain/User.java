package com.lin.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther : lin
 * @date : 2019/6/14 12:31
 * @desc :
 */
@Data
public class User implements Serializable {
    private int id;
    private String name;
    private String gender;
    private int age;
    private String address;
    private String qq;
    private String email;
}
