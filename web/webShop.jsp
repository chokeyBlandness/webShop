<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/19
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String loginMessage="";
    loginMessage= (String) request.getSession().getAttribute("loginMessage");
    if (loginMessage==null||!loginMessage.equals("successfully login!")){
        response.sendRedirect("CheckUserServlet");
    }
%>
<html>
<head>
    <title>webShop</title>
</head>
<body>
    <form action="shop.do" method="post">
        <%
            Date lastAccessedTime= (Date) request.getSession().getAttribute("lastAccessedTime");
            int loginNumber= (int) request.getSession().getAttribute("loginNumber");
            String account= (String) request.getSession().getAttribute("account");
        %>
        <b style="color: aqua">login successfully!</b><br/>
        <b style="color: brown">welcome:<%=account%></b><br/>
        <p style="color: chocolate">last loginTime:<%=lastAccessedTime%></p><br/>
        <b style="color: chocolate">loginNumber:<%=loginNumber%></b><br/>

        <div style="height: 200px;width: 180px;float: left">
            <img src="001.jpg" alt="Error show" width="90px" height="120px"><br/>
            <b>iphone</b><br/>
            <b>PRICE:4600</b><br/>
            <input type="checkbox" name="purchase" value="iphone"/>purchase<br/>
            number:<input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" 
                   onafterpaste="this.value=this.value.replace(/\D/g,'')"
                    style="width: 60px" name="phone1Number"/>
        </div>

        <div style="height: 200px;width: 180px;float: left">
            <img src="002.jpg" alt="Error show" width="90px" height="120px"><br/>
            <b>HUAWEI</b><br/>
            <b>PRICE:1900</b><br/>
            <input type="checkbox" name="purchase" value="HUAWEI"/>purchase<br/>
            number:<input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" 
                   onafterpaste="this.value=this.value.replace(/\D/g,'')"
                   style="width: 60px" name="phone2Number"/>
        </div>

        <div style="height: 200px;width: 180px;float: left">
            <img src="003.jpg" alt="Error show" width="90px" height="120px"><br/>
            <b>XiaoMi</b><br/>
            <b>PRICE:1000</b><br/>
            <input type="checkbox" name="purchase" value="XiaoMi"/>purchase<br/>
            number:<input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" 
                   onafterpaste="this.value=this.value.replace(/\D/g,'')"
                   style="width: 60px" name="phone3Number"/>
        </div>
        <br/>
        <input type="submit" value="submit"/>

    </form>
</body>
</html>
