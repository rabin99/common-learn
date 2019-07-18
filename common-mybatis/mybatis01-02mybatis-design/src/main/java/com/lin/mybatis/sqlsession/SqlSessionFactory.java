package com.lin.mybatis.sqlsession;

/**
 * @auther : lin
 * @date : 2019/5/24 14:56
 * @desc :
 */
public interface SqlSessionFactory {
    /**
     * 用于打开一个新的SqlSession对象
     * @return
     */
    SqlSession openSession();
}
