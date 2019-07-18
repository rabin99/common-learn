<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/29
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%--常用的注解--%>
    <%--anno/testRequestParam 不要在访问 URL 前面加/，否则无法找到资源--%>
    <a href="anno/testRequestParam?name=哈哈">RequestParam</a>

    <br>

    <form action="anno/testRequestBody" method="post">
        用户姓名：<input type="text" name="username" /><br/>
        用户年龄：<input type="text" name="age" /><br/>
        <input type="submit" value="提交" />
    </form>

    <a href="anno/testPathVariable/10">testPathVariable</a>

    <br>

    <a href="anno/testRequestHeader">RequestHeader</a>

    <br>

    <a href="anno/testCookieValue">CookieValue</a>

    <br>

    <form action="anno/testModelAttribute" method="post">
        用户姓名：<input type="text" name="uname" /><br/>
        用户年龄：<input type="text" name="age" /><br/>
        <input type="submit" value="提交" />
    </form>

    <br>


    <a href="anno/testSessionAttributes">testSessionAttributes</a>
    <a href="anno/getSessionAttributes">getSessionAttributes</a>
    <a href="anno/delSessionAttributes">delSessionAttributes</a>


</body>
</html>
