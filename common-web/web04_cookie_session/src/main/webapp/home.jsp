<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.net.URLDecoder" %>
<%--
  Created by IntelliJ IDEA.
  User: HWINPC171
  Date: 2019/6/12
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%!
    final static String LAST_TIME = "lastTime";
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
%>

<%
    Cookie[] cookies = request.getCookies();
    boolean flag = false;
    if (cookies != null && cookies.length > 0) {
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if (LAST_TIME.equals(name)) {
                flag = true;
                String format = dateTimeFormatter.format(LocalDateTime.now());
                format = URLEncoder.encode(format, "utf-8");
                cookie.setValue(format);
                cookie.setMaxAge(60 * 60 * 24 * 30);
                response.addCookie(cookie);

                String value = URLDecoder.decode(cookie.getValue(),"utf-8");
%>
<h5>欢迎回来，您上次访问时间为：<%=value %>
</h5>
<%
                break;
            }
        }
    }
    if (cookies == null || cookies.length == 0 || flag == false) {
        String firstVisitTime = dateTimeFormatter.format(LocalDateTime.now());
        firstVisitTime = URLEncoder.encode(firstVisitTime, "utf-8");
        Cookie cookie = new Cookie("lastTime", firstVisitTime);
        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);
%>
<h1>您好，欢迎您首次访问</h1>
<span></span>
<%
    }
%>
</body>
</html>
