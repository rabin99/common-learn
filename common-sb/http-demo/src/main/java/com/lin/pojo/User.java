package com.lin.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther : lin
 * @date : 2019/7/5 14:42
 * @desc :
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
}
