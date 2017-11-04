package com.jingdong;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet(name = "CheckUserServlet")
public class CheckUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginMessage="";
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        if (!account.equals("111")||!password.equals("111")){
            loginMessage="wrong login!";
            request.setAttribute("loginMessage",loginMessage);
            response.sendRedirect("login.jsp");
        }
        else {
            loginMessage="login successfully!";
            request.setAttribute("loginMessage",loginMessage);

            HttpSession session=request.getSession(true);
            session.setAttribute("account",account);
            session.setAttribute("password",password);


            Connection connection=null;
            Statement statement=null;
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                connection= DriverManager.getConnection("jdbc:sqlserver://HASEE-PC:1433;DatabaseNamE=webShop",
                        "sa","111");
                statement=connection.createStatement();
            }catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.executeUpdate("CREATE TABLE loginTime(logintime CHAR(20) PRIMARY KEY)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.executeUpdate("INSERT INTO loginTime VALUES ("+session.getCreationTime()+")");
            } catch (SQLException e) {
                e.printStackTrace();
            } try {
                statement.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


            ShoppingCart shoppingCart=new ShoppingCart();
            session.setAttribute("shoppingCart",shoppingCart);

            response.sendRedirect("Servlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginMessage="";
        request.setAttribute("loginMessage",loginMessage);
        response.sendRedirect("login.jsp");
    }
}
