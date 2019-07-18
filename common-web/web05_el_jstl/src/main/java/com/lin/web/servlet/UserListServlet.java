package com.lin.web.servlet;

import com.lin.domain.User;
import com.lin.service.IUserService;
import com.lin.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @auther : lin
 * @date : 2019/6/14 12:56
 * @desc :
 */
@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserService userService = new UserServiceImpl();
        List<User> users = userService.findAll();
        req.setAttribute("users",users);
        req.getRequestDispatcher("/list.jsp").forward(req,resp);
    }
}
