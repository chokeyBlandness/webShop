package com.jingdong;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet(name = "CheckUserServlet")
public class CheckUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginMessage="";
        String passwordFromDatabase="";
        boolean findAccountFromDatabase=true;

        String loginAccount=request.getParameter("account");
        String loginPassword=request.getParameter("password");

        ConnectDatabase_webShop webShop=new ConnectDatabase_webShop();
        ResultSet resultSet=null;
        try {
            resultSet=webShop.getStatement().executeQuery("SELECT * FROM member WHERE account=\'" +
                    loginAccount+"\'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (resultSet.next()==false){//donnot find account
                findAccountFromDatabase=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!findAccountFromDatabase) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            webShop.closeConnect();
            loginMessage = "no this account!";
            request.setAttribute("loginMessage", loginMessage);
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(request,response);
            return;
        }else {
            try {
                passwordFromDatabase= resultSet.getString("password");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (!loginPassword.equals(passwordFromDatabase)){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            webShop.closeConnect();
            loginMessage="wrong login!";
            request.setAttribute("loginMessage",loginMessage);
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(request,response);
            return;
        }
        else {
            HttpSession session=request.getSession(true);
            loginMessage="successfully login!";
            session.setAttribute("account",loginAccount);
            session.setAttribute("loginMessage",loginMessage);

            try {
                webShop.getStatement().executeUpdate("CREATE TABLE loginTime(logintime VARCHAR(20) PRIMARY KEY)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                webShop.getStatement().executeUpdate("INSERT INTO loginTime VALUES ("+session.getCreationTime()+")");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            webShop.closeConnect();

            ShoppingCart shoppingCart=new ShoppingCart();
            session.setAttribute("shoppingCart",shoppingCart);
            response.sendRedirect("Servlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginMessage="";
        loginMessage= (String) request.getSession().getAttribute("loginMessage");
        if (loginMessage==null||!loginMessage.equals("successfully login!")){
            response.sendRedirect("login.jsp");
        }else {
            response.sendRedirect("Servlet");
        }
    }
}
