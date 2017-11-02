<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/19
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String accountValue="";
    String passwordValue="";
    accountValue= (String) request.getSession().getAttribute("account");
    passwordValue= (String) request.getSession().getAttribute("password");
    if (accountValue==null||passwordValue==null) {
        response.sendRedirect("CheckUserServlet");
    }else{
        if (!accountValue.equals("111")||!passwordValue.equals("111")){
            response.sendRedirect("CheckUserServlet");
        }
    }
%>
<html>
<head>
    <title>webShop</title>
</head>
<body>
    <form action="shop.do" method="post">
        <b>login successfully!</b><br/>
        <b>welcome:</b>${account}<br/>
        <p>last login:</p>${lastAccessedTime}<br/>
        <b>loginNumber:</b>${loginNumber}<br/>

        <div style="height: 200px;width: 180px;float: left">
            <img src="57d0d400Nfd249af4.jpg" alt="Error show" width="90px" height="120px"><br/>
            <b>iphone</b><br/>
            <b>PRICE:4600</b><br/>
            <input type="checkbox" name="purchase" value="iphone"/>purchase<br/>
            number:<input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" 
                   onafterpaste="this.value=this.value.replace(/\D/g,'')"
                    style="width: 60px" name="phone1Number"/>
        </div>

        <div style="height: 200px;width: 180px;float: left">
            <img src="59bf3c47N91d65c73.jpg" alt="Error show" width="90px" height="120px"><br/>
            <b>HUAWEI</b><br/>
            <b>PRICE:1900</b><br/>
            <input type="checkbox" name="purchase" value="HUAWEI"/>purchase<br/>
            number:<input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" 
                   onafterpaste="this.value=this.value.replace(/\D/g,'')"
                   style="width: 60px" name="phone2Number"/>
        </div>

        <div style="height: 200px;width: 180px;float: left">
            <img src="59521206N527bb108.jpg" alt="Error show" width="90px" height="120px"><br/>
            <b>XiaoMi</b><br/>
            <b>PRICE:1000</b><br/>
            <input type="checkbox" name="purchase" value="XiaoMi"/>purchase<br/>
            number:<input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" 
                   onafterpaste="this.value=this.value.replace(/\D/g,'')"
                   style="width: 60px" name="phone3Number"/>
        </div>

        <input type="submit" value="submit"/>

    </form>
</body>
</html>
