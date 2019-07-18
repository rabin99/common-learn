package com.lin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther : lin
 * @date : 2019/6/27 15:03
 * @desc : 自定义拦截器
 */
public class MyInterceptor1 implements HandlerInterceptor {
    /**
     * 预处理，controller方法执行前
     * return true 放行，执行下一个拦截器，如果没有，执行controller中的方法
     * return false不放行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor1执行了...前1111");
        // 如果这里转发了，那么最终的页面就是这个转发的页面，而不是controller返回的success，因为页面已经渲染了
        // 无论多少过滤器以及controller如何调用，总之，谁先渲染就显示谁。
        // request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);

        // 如果一旦false了，那么post和after方法都不会执行，更别说controller执行了
        // return false;

        // 如果这里返回true，第二个拦截器返回false，那么该类中后续2个方法是否会执行？
        // 猜测：不会执行！
        // 实际是：该类pre执行，MyInterceptor2中pre执行（返回是false），该类after执行。（该类post不执行，MyInterceptor1中post、after都不执行）;
        // 当3个拦截器，第一个拦截器pre方法通过，第二个pre通过，第三pre不通过，那么只会执行1 pre，2pre，3pre ，2after，1after，

        // 总结：拦截器和controller有点类似一人执行一个方法，先pre然后controller，然后controller返回值（返回jsp，但未渲染返回到前端前）再post；然后渲染并返回到前端，再after，；
        // 拦截的是自己的post、after，自己包含内的拦截器所有方法，非自己范围内的其他拦截器的的post和controller，对自己范围外的其他拦截器的after并不能拦截。
        // 如以3个拦截器，2 pre false和2pre false为例：
        // 当第2pre 返回false，只会执行 1pre ,2pre (false),1 after
        // 当第3pre 返回false，只会执行1 pre，2pre，3pre(false) ，2after，1after，
        System.out.println(request.getContextPath());

        // 拦截器after方法不能转发forward和重定向，pre、post方法支持
        // response.sendRedirect(request.getContextPath() + "/index.jsp");
        return true;
    }

    /**
     * 后处理方法，controller方法执行后，success.jsp执行之前
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor1执行了...后1111");
        // 这里转发，那么因为post是controller返回jsp，但是jsp渲染之前执行，所以这里转发先渲染了，前端最终显示error
//         request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        // 同样报错，java.lang.IllegalStateException: Cannot call sendRedirect() after the response has been committed
//        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    /**
     * success.jsp页面执行后，该方法会执行
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor1执行了...最后1111");
        // 直接报错！！！不能再after中转发 java.lang.IllegalStateException: Cannot forward after response has been committed
        // request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        // 同样报错，java.lang.IllegalStateException: Cannot call sendRedirect() after the response has been committed
//        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
