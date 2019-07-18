package com.lin.service.impl;

import com.lin.service.IExceptionService;

/**
 * @auther : lin
 * @date : 2019/6/27 14:04
 * @desc :
 */
public class ExceptionServiceImpl implements IExceptionService {
    @Override
    public String testException() {
//        int a = 1 / 0;
        int a = 100;
        return a + "";
    }
}
