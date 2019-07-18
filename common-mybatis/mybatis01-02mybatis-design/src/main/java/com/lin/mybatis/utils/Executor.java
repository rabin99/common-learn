package com.lin.mybatis.utils;

import com.lin.mybatis.cfg.Mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/24 11:43
 * @desc : 负责执行SQL语句，并且封装结果集
 */
public class Executor {
    public <E> List<E> selectList(Mapper mapper, Connection conn){
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            //1.取出mapper中的数据,mapper中存放了sql语句，以及sql语句的返回值
            String queryString = mapper.getQueryString();
            String resultType = mapper.getResultType();
            Class<?> domainClass = Class.forName(resultType);
            //2.获取PreparedStatement对象
             pstm = conn.prepareStatement(queryString);
            //3.执行SQL语句，获取结果集
             rs = pstm.executeQuery();
            //4.封装结果集,定义返回值
             List<E> list = new ArrayList<>();
             while (rs.next()){
                 //实例化要封装的实体类对象
                 E obj = (E) domainClass.newInstance();
                 ResultSetMetaData rsmd = rs.getMetaData();
                 int columnCount = rsmd.getColumnCount();
                 for (int i = 1; i <= columnCount; i++) {
                     //获取每列的名称，列名的序号是从1开始的
                     String columnName = rsmd.getColumnName(i);
                     //根据得到列名，获取每列的值
                     Object columnValue = rs.getObject(columnName);
                     // 传入2个值，前者是类的字段名，后者是对应的类，这样就可以拿到类中与该字段相关的内容，如常见拿getter/setter
                     PropertyDescriptor pd = new PropertyDescriptor(columnName, domainClass);
                     // 获取setter方法
                     Method writeMethod = pd.getWriteMethod();
                     // 调用类的set方法，将值存入字段
                     writeMethod.invoke(obj,columnValue);
                 }
                 list.add(obj);
             }
             return list;
        }catch (Exception e){
            throw  new RuntimeException(e);
        }finally {
            release(pstm,rs);
        }
    }
    public void release(PreparedStatement preparedStatement, ResultSet resultSet){
        if(resultSet !=null){
            try {
                resultSet.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(preparedStatement !=null){
            try {
                preparedStatement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
