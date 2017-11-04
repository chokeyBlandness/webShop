<%@ page import="com.jingdong.ShoppingCart" %>
<%@ page import="com.jingdong.Merchandise" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/23
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>shoppingCart</title>
</head>
<body>
    <form action="shoppingCart.do" method="post">
        <b>shoppingcart:</b><br/>
        <b>welcome:</b>${account}<br/>
        ${shoppingCart}
    </form>

</body>
</html>
