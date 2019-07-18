package com.lin.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther : lin
 * @date : 2019/6/27 13:49
 * @desc : 异常处理类
 */
public class SysExceptionResolver implements HandlerExceptionResolver {
    /*
    处理异常业务逻辑
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ex.printStackTrace();
        // 获取异常对象
        SysException e = null;
        if(ex instanceof SysException){
             e = (SysException) ex;
        }else {
            e = new SysException("系统正在维护...");
        }
        // 创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMsg",e.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
