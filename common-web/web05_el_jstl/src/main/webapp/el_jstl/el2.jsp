<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el获取域中的数据</title>
</head>
<body>


<%
    //在域中存储数据
    session.setAttribute("name","李四");

    request.setAttribute("name","张三");
    session.setAttribute("age","23");

    request.setAttribute("str","");

%>

<h3>el获取值</h3>
${requestScope.name}
${sessionScope.age}
${sessionScope.haha}

${name}
${sessionScope.name}

<%------>el获取数据---------%>
<%!
    class  User{
        String name;
        int age;
        Date Birthday;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Date getBirthday() {
            return Birthday;
        }

        public void setBirthday(Date birthday) {
            Birthday = birthday;
        }
    }
%>
<%

    User user = new User();
    user.setName("张三");
    user.setAge(23);
    user.setBirthday(new Date());


    request.setAttribute("u",user);


    List list = new ArrayList();
    list.add("aaa");
    list.add("bbb");
    list.add(user);

    request.setAttribute("list",list);


    Map map = new HashMap();
    map.put("sname","李四");
    map.put("gender","男");
    map.put("user",user);

    request.setAttribute("map",map);

%>

<h3>el获取对象中的值</h3>
${requestScope.u}<br>

<%--
    * 通过的是对象的属性来获取
        * setter或getter方法，去掉set或get，在将剩余部分，首字母变为小写。
        * setName --> Name --> name
--%>

${requestScope.u.name}<br>
${u.age}<br>
${u.birthday}<br>
${u.birthday.month}<br>

${u.birStr}<br>

<h3>el获取List值</h3>
${list}<br>
${list[0]}<br>
${list[1]}<br>
${list[10]}<br>

${list[2].name}

<h3>el获取Map值</h3>
${map.gender}<br>
${map["gender"]}<br>
${map.user.name}


<%------------el隐式对象---------------%>
${pageContext.request}<br>
<h4>在jsp页面动态获取虚拟目录</h4>
${pageContext.request.contextPath}
</body>

</html>
