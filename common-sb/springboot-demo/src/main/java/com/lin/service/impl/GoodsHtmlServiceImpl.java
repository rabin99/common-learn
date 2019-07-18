package com.lin.service.impl;

import com.lin.domain.User;
import com.lin.service.IGoodsHtmlService;
import com.lin.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @auther : lin
 * @date : 2019/7/4 9:35
 * @desc :   针对具体查询id的详情页进行静态化页面
 */
@Slf4j
@Service("goodsHtmlService")
public class GoodsHtmlServiceImpl implements IGoodsHtmlService {

    @Autowired
    private IUserService userService;

    @Autowired
    private TemplateEngine templateEngine;

//    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS,
//            new LinkedBlockingQueue<>(16)
//            , new ThreadPoolExecutor.DiscardPolicy());

        private void createHtml(Long spuId, HttpServletRequest request, HttpServletResponse response) {
//    private void createHtml(Long spuId, WebContext context) {
        try {
            Thread.sleep(1000);
            System.out.println("创建花了10s~~~");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始创建");
        // 获取页面数据
        User user = userService.selectById(spuId);
        // 创建thymeleaf上下文对象
//        Context context = new Context();
        WebContext context = new WebContext(request, response, request.getServletContext());
        // 把数据放入上下文对象
        context.setVariable("users", user);
        File file = new File("E:\\uploads\\" + spuId + ".html");
        try (PrintWriter writer = new PrintWriter(file)) {
            // template名字必须是对应个thymeleaf的文件名
            templateEngine.process("users", context, writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("页面静态化出错：{}" + e, spuId);
        }

        System.out.println("创建完毕！！");
    }

    /**
     * 这个方法有个特殊的地方，
     * 如果使用多线程异步创建，就是必须使用一个线程工具类去异步创建静态化，否则会报错！
     * 猜测：应该是spring接管thymeleaf 处理器相关导致，具体原因就不知道了……
     * @param spuId
     * @param request
     * @param response
     */
    @Override
    public void asyncExecute(Long spuId, HttpServletRequest request, HttpServletResponse response) {
        // 很诡异的问题！！这里多线程创建会报错，不知道什么原因
        //方法一
//         this.createHtml(spuId, request, response);

        // 方法二
        // 阻塞创建，非多线程创建，正常，如果改成多线程，必须要将context放createHtml内部去初始化，否则报错，
//         WebContext webContext = new WebContext(request, response, request.getServletContext());
        // this.createHtml(spuId,webContext);

        // 多线程创建,这里必须是将webcontext放在createHtml中创建，正常，否则报错！！！
        ThreadUtils.execute(()->createHtml(spuId,request,response));
        // 创建关闭
        ThreadUtils.getEs().shutdown();


   /*     synchronized (GoodsHtmlServiceImpl.class) {
            if (threadPoolExecutor == null) {
                System.out.println(1111);
                threadPoolExecutor = new ThreadPoolExecutor(10, 5, 60,
                        TimeUnit.SECONDS, new LinkedBlockingQueue<>(16),
                        new ThreadPoolExecutor.DiscardPolicy());
                System.out.println(2222);
            }
        }*/

        // 报错
        // threadPoolExecutor.execute(() -> this.createHtml(spuId, request, response));
        // 更加报错！
        // threadPoolExecutor.execute(() -> this.createHtml(spuId, webContext));

    }
}