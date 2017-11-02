package com.jingdong;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

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
//            Cookie accoutCookie=new Cookie("account",account);
//            Cookie passwordCookie=new Cookie("password",password);
//            accoutCookie.setMaxAge(60*3);
//            passwordCookie.setMaxAge(60*3);
//            response.addCookie(accoutCookie);
//            response.addCookie(passwordCookie);

            HttpSession session=request.getSession(true);
            session.setAttribute("account",account);
            session.setAttribute("password",password);


            String loginTimeFilePath=this.getServletContext().getRealPath("/");
            loginTimeFilePath=loginTimeFilePath+account+".txt";
            File loginTimeFile=new File(loginTimeFilePath);
            if (!loginTimeFile.exists()){
                loginTimeFile.mkdir();
                FileWriter fileWriter= new FileWriter(loginTimeFile);
                fileWriter.write(1+"\n");
                fileWriter.write(String.valueOf(session.getCreationTime())+"\n");
                fileWriter.close();
            }else {
                FileWriter fileWriter= new FileWriter(loginTimeFile);
                fileWriter.write(String.valueOf(session.getCreationTime())+"\n");
                fileWriter.close();
            }
            session.setAttribute("loginTimeFilePath",loginTimeFilePath);

            ShoppingCart shoppingCart=new ShoppingCart();
            //getServletContext().setAttribute("shoppingCart",shoppingCart);
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
