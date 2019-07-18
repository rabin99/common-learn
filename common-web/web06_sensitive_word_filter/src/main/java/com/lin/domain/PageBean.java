package com.lin.domain;

import lombok.Data;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/6/18 15:27
 * @desc :
 */
@Data
public class PageBean<T> {
    // 总记录数
    private int totalCount;
    // 总页数
    private int totalPage;
    // 每页的数据
    private List<T> list;
    // 当前码
    private int currentPage;
    // 每页显示的记录数
    private int rows;
}
