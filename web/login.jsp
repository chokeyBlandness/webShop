<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/29
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
    <form action="CheckUser.do" method="post">
        ${loginMessage}<br/>
        <b>please input your account and password:</b><br/>
        account:<input type="text" name="account"/><br/>
        password:<input type="password" name="password"/><br/>
        <input type="submit" value="submit"/>
        <input type="reset" value="reset"/>
    </form>
</body>
</html>
