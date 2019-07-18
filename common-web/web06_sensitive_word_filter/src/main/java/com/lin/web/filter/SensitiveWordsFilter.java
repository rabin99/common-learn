package com.lin.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther : lin
 * @date : 2019/6/19 14:43
 * @desc :
 */
//@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    // 敏感词汇集合
    private List<String> sensitiveWords = new ArrayList<>();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 使用代理来对req的方法进行处理，过滤敏感词
        ServletRequest proxy_req = (ServletRequest)Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 增强getParmeter方法，判断是否是getParameter方法
                if (method.getName().equals("getParameter")) {
                    // 增强返回值,获取返回值
                    String value = (String) method.invoke(req, args);
                    if (value != null) {
                        for (String sensitiveWord : sensitiveWords) {
                            // 如果有敏感数据替换成***
                            if(value.contains(sensitiveWord)){
                             value = value.replaceAll(sensitiveWord,"***");
                            }
                        }
                    }
                    return value;
                }
                return method.invoke(req,args);
            }
        });
        //  忘记放行导致了很大了问题！！！
        chain.doFilter(proxy_req,resp);
    }

    public void init(FilterConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        String realPath = servletContext.getRealPath("/WEB-INF/classes/sensitive_word");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(realPath));
            String line = null;
            while ((line = bufferedReader.readLine())!=null){
                sensitiveWords.add(line);
            }
            bufferedReader.close();
            System.out.println(sensitiveWords);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
