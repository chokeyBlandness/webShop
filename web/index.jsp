<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/19
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>shop</title>
  </head>
  <body>
  <%response.sendRedirect("login.jsp");%>
  <form>
    <script>
      function plusNumber() {
        document.getElementById("phone1LabelId").innerHTML++;
      }
      function minusNumber() {
          x=document.getElementById("phone1LabelId").innerHTML;
          if(x>0) {
              x--;
          }
      }
    </script>
    <div style="height: 200px;width: 180px;float: left">
      <img src="57d0d400Nfd249af4.jpg" alt="Error show" width="90px" height="120px"><br/>
      <b>phone</b>
      <input type="button" id="minus" onclick="minusNumber()" value="-"/>
      <label id="phone1LabelId" datatype="">0</label>
      <input type="button" id="plus" onclick="plusNumber()" value="+"/>
    </div>
    <div style="height: 200px;width: 180px;float: left">
      <img src="59bf3c47N91d65c73.jpg" alt="Error show" width="90px" height="120px"><br/>
      <b>phone</b>
    </div>
    <div style="height: 200px;width: 180px;float: left">
      <img src="59521206N527bb108.jpg" alt="Error show" width="90px" height="120px"><br/>
      <b>phone</b>
    </div>
    <input type="submit" value="submit"/>
  </form>
  </body>
</html>
