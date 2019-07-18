package spring03_03springAOP.utils;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @auther : lin
 * @date : 2019/5/21 15:03
 * @desc :
 * <aop:advisor>大多用于事务管理。
 * <aop:aspect>大多用于日志、缓存。
 * 如果用<aop:advisor>配置切面的话也可以配置，但切面类跟aspect有所不同，需要实现接口
 */
public class LoggerAdvisor implements MethodBeforeAdvice, AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("after advisor...");
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before advisor...");
    }
}
