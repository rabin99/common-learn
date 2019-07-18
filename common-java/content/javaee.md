### 1.tomcat
1. 目录结构：

```shell
bin：脚本目录
	启动脚本：startup.bat
	停止脚本：shutdown.bat
conf：配置文件目录
	核心配置文件：server.xml
	用户权限配置文件：tomcat-users.xml
	所有web项目默认配置文件：web.xml
lib：依赖库，tomcat和web项目中需要使用的jar包
logs：日志文件.
	localhost_access_log.*.txt tomcat记录用户访问信息，星*表示时间。
	例如：localhost_access_log.2016-02-28.txt
temp：临时文件目录，文件夹内内容可以任意删除。
webapps：默认情况下发布WEB项目所存放的目录。
work：tomcat处理JSP的工作目录。
```

2. 在JavaEE规范中，WEB项目存在一定的目录结构，具体结构如下：
项目名称
​	|-----静态资源.HTML，CSS，JS
​	|-----WEB-INF
   ​		 |----web.xml  当前WEB项目的核心配置，Servlet2.5必须有，3.0可省略。
   ​		 |----lib       当前WEB项目所需要的第三方的jar的存放位置。
   ​		 |----classes  Java源码编译后生成class文件存放的位置。    

3. 常用的项目发布方式:(虚拟目录映射)
   ★方式1:将项目放到tomcat/webapps下
   (了解)方式2:修改 tomcat/conf/server.xml
   ​	大概130行:
   ​		在host标签下 添加如下代码
   ​			<Context path="/项目名" docBase="项目的磁盘目录"/>
   ​		例如:
   ​			<Context path="/my" docBase="G:\myweb"/>
   (了解)方式3:
   ​	在tomcat/conf/引擎目录/主机目录下 新建一个xml文件
   ​		文件的名称就是项目名 文件的内容如下:
   ​			<Context docBase="G:\myweb"/>