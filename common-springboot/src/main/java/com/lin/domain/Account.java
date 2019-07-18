package com.lin.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @auther : lin
 * @date : 2019/5/16 13:43
 * @desc :
 */
public class Account implements Serializable {
    private int id;
    private String name;
    private BigDecimal money;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
