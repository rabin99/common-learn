package com.lin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther : lin
 * @date : 2019/7/4 10:07
 * @desc : 这里只实现了创建，如详情页数据被修改可以写入到中间件，然后确保删除对应详情页额（数据一致性保证）
 */
public interface IGoodsHtmlService {

    // 异步执行创建html静态页面
    void asyncExecute(Long spuId, HttpServletRequest request, HttpServletResponse response);
}
