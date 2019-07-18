package com.lin.controller;

import com.lin.exception.SysException;
import com.lin.service.IExceptionService;
import com.lin.service.impl.ExceptionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther : lin
 * @date : 2019/6/27 13:46
 * @desc :
 */
@Controller
@RequestMapping("user")
public class ExceptionController {

    IExceptionService exceptionService = new ExceptionServiceImpl();

    @RequestMapping("testException")
    public String testException() throws SysException {
        System.out.println("testException执行了。。。");
        String s = exceptionService.testException();
        return s;
     /*   try{
            int a = 1 /0;

        }catch (Exception e){
            e.printStackTrace();
            throw new SysException("查询所有用户出现错误了...");
        }
        return "success";*/
    }
}
