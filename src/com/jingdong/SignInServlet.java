package com.jingdong;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "SignInServlet")
public class SignInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String signInAccount="";
        String signInPassword="";
        String affirmPassword="";
        String signInMessage="";
        signInAccount=request.getParameter("signInAccount");
        signInPassword=request.getParameter("signInPassword");
        affirmPassword=request.getParameter("affrimPassword");
        boolean signInAccountExist=false;


        ConnectDatabase_webShop webShop=new ConnectDatabase_webShop();
        ResultSet resultSet=null;
        try {
            webShop.getStatement().executeUpdate("CREATE TABLE member(account VARCHAR(20)," +
                    "password VARCHAR(20))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet=webShop.getStatement().executeQuery("SELECT * FROM member WHERE account=\'" +
                    signInAccount+"\'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (resultSet.next()==true){
                signInAccountExist=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (signInAccountExist){
            signInMessage="exist account";
            request.setAttribute("signInMessage",signInMessage);
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("SignIn.jsp");
            requestDispatcher.forward(request,response);
            webShop.closeConnect();
            return;
        }else {
            if (!signInPassword.equals(affirmPassword)){
                signInMessage="password wrong";
                request.setAttribute("signInMessage",signInMessage);
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("SignIn.jsp");
                requestDispatcher.forward(request,response);
                webShop.closeConnect();
                return;
            }else {
                try {
                    webShop.getStatement().executeUpdate("INSERT INTO member VALUES (\'" +
                            signInAccount+"\',\'"+signInPassword+"\')");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                webShop.closeConnect();
                response.sendRedirect("login.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("SignIn.jsp");
    }
}
