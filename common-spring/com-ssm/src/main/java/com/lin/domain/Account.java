package com.lin.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @auther : lin
 * @date : 2019/6/28 14:17
 * @desc : 账户
 */
@Data
@Slf4j
public class Account implements Serializable {
    private Integer id;
    private String name;
    private BigDecimal money;
}
