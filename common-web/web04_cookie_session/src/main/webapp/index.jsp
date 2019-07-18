<%--
  Created by IntelliJ IDEA.
  User: HWINPC171
  Date: 2019/6/11
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    System.out.println("hello jsp");
    int i = 5;
    String contextPath = request.getContextPath();
    out.println(contextPath);

%>
<%!
    int i=3;
%>
<%= "hello" %>
System.out.println("hello jsp");
<h3>hi ~ jsp!</h3>
<%
    response.getWriter().write("response....");
%>
</body>
</html>
