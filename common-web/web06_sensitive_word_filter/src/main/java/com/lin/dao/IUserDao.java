package com.lin.dao;

import com.lin.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @auther : lin
 * @date : 2019/6/18 15:31
 * @desc :
 */
public interface IUserDao {
    List<User> findAll();

    User findUserByUsernameAndPassword(String username, String password);

    void add(User user);

    void delete(int id);

    User findById(int id);

    void update(User user);

    /*
    查询总记录数
     */
    int findTotalCount(Map<String, String[]> condition);

    /*
    分页查询
     */
    List<User> findbyPage(int start, int rows, Map<String, String[]> condition);
}
