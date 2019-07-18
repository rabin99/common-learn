package com.lin0304.dao;

import com.lin0304.domain.Role;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/27 17:48
 * @desc :
 */
public interface IRoleDao {
    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();
}
