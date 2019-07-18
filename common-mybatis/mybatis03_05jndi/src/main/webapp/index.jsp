<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="com.lin0305.dao.IUserDao" %>
<%@ page import="com.lin0305.domain.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: HWINPC171
  Date: 2019/5/28
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hellow world</title>
</head>
<body>
<%
    InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory factory = builder.build(in);
    SqlSession sqlSession = factory.openSession();
    IUserDao userDao = sqlSession.getMapper(IUserDao.class);
    List<User> users = userDao.findAll();
    /*
     * 使用lamada表达式需要注意tomcat升级到9，否则报错java.lang.ClassNotFoundException: org.apache.jsp.index_jsp
     */
    users.stream().forEach(System.out::println);
    sqlSession.close();
    in.close();
%>
</body>
</html>
