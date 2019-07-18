package com.lin.service.impl;

import com.lin.dao.IUserDao;
import com.lin.dao.impl.UserDaoImpl;
import com.lin.domain.PageBean;
import com.lin.domain.User;
import com.lin.service.IUserService;

import java.util.List;
import java.util.Map;

/**
 * @auther : lin
 * @date : 2019/6/18 16:18
 * @desc :
 */
public class UserServiceImpl implements IUserService {
    private IUserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用Dao完成查询
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        if (ids != null && ids.length > 0) {
            //1.遍历数组
            for (String id : ids) {
                //2.调用dao删除
                dao.delete(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        if (currentPage <= 0) {
            currentPage = 1;
        }
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);
        int totalCount = dao.findTotalCount(condition);
        pageBean.setTotalCount(totalCount);

        int start = (currentPage - 1) * rows;
        List<User> users = dao.findbyPage(start, rows, condition);
        pageBean.setList(users);

        int totalPage = (totalCount % rows == 0) ? totalCount / rows : (totalCount / rows) + 1;
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }
}
