<%--
  Created by IntelliJ IDEA.
  User: HWINPC171
  Date: 2019/6/26
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>传统文件上传</h3>
<%--<form action="<%=request.getContextPath()%>/user/fileupload1" method="post" enctype="multipart/form-data">--%>
<form action="${pageContext.request.contextPath}/user/fileupload1" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="upload"/></br>
    <input type="submit" value="上传"/>
</form>

<h3>Springmvc文件上传</h3>
<form action="${pageContext.request.contextPath}/user/fileupload2" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/><br/>
    <input type="submit" value="上传"/>
</form>

<h3>跨服务器文件上传</h3>
<form action="${pageContext.request.contextPath}/user/fileupload3" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/><br/>
    <input type="submit" value="上传"/>
</form>

</body>
</html>
