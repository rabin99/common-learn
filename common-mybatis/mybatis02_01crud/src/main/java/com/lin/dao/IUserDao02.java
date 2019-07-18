package com.lin.dao;

import com.lin.domain.QueryVo;
import com.lin.domain.User02;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/24 16:55
 * @desc :
 */
public interface IUserDao02 {
    List<User02> findAll();

    void saveUser(User02 user);

    void updateUser(User02 user);

    void deleteUser(Integer userId);

    User02 findById(Integer userId);

    List<User02> findByName(String username);

    int findTotal();

    /**
     * 根据queryVo中的条件查询用户
     *
     * @param vo
     * @return
     */
    List<User02> findUserByVo(QueryVo vo);

}
