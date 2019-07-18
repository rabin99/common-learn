package com.lin.proxy;

/**
 * @auther : lin
 * @date : 2019/6/20 13:47
 * @desc :
 */
public class Lenovo implements SaleComputer {
    @Override
    public String sale(double money) {

        System.out.println("花了"+money+"元买了一台联想电脑...");
        return "联想电脑";
    }

    @Override
    public void show() {
        System.out.println("展示电脑....");
    }
}
