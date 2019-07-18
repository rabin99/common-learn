package com.lin.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @auther : lin
 * @date : 2019/7/3 14:00
 * @desc :
 */
@Data
public class Account implements Serializable {
    private Integer id;
    private String name;
    private BigDecimal money;
}
