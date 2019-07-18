package com.lin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther : lin
 * @date : 2019/6/27 15:02
 * @desc :
 */
@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping("/testInterceptor")
    public String testInterceptor(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("testInterceptor执行了。。。");
//        response.sendRedirect(request.getContextPath()+"/index.jsp");
        return "success";
    }
}
