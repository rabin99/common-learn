package com.lin.controller;

import com.lin.domain.User;
import com.lin.service.IGoodsHtmlService;
import com.lin.service.IUserService;
import com.lin.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @auther : lin
 * @date : 2019/7/3 18:01
 * @desc :ThymeleafProperties自动配置Thymeleaf
 */
@Controller
@RequestMapping("/thy")
public class ThymeleafController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGoodsHtmlService goodsHtmlService;

    @GetMapping("/all")
    public String all(ModelMap modelMap) {
        List<User> users = userService.findAll();
        modelMap.addAttribute("users",users);
        // 返回模板名称（就是classpath:/templates/目录下的html文件名）
        return "users";
    }

    @GetMapping("/static/{id}")
    public String toItemPage(@PathVariable("id")Long spuId, Model model, HttpServletRequest request, HttpServletResponse response){
        User users = userService.selectById(spuId);
        model.addAttribute("users",users);
        // 创建静态化页面
        this.goodsHtmlService.asyncExecute(spuId,request,response);
        return "users";
    }

}
