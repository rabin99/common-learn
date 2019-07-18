<%--
  Created by IntelliJ IDEA.
  User: HWINPC171
  Date: 2019/6/12
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h5><%=request.getSession().getAttribute("user")%>，欢迎你！</h5>
</body>
</html>
