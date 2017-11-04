package com.jingdong;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.String.valueOf;

@javax.servlet.annotation.WebServlet(name = "Servlet")
public class Servlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String[] purchase=request.getParameterValues("purchase");
        if (purchase==null){
            return;
        }
        String[] phone={"iphone","HUAWEI","XiaoMi"};
        String[] phoneNumber={"phone1Number","phone2Number","phone3Number"};
        double[] price={4600,1900,1000};
        ShoppingCart shoppingCart= (ShoppingCart) request.getSession().getAttribute("shoppingCart");

        for (int i=0;i<purchase.length;i++){
            for (int j=0;j<phone.length;j++){
                if (purchase[i].matches(phone[j])){
                    Merchandise newMerchandise=new Merchandise();
                    newMerchandise.setName(purchase[i]);
                    String stringNum=request.getParameter(phoneNumber[j]);
                    if(stringNum==null){
                        return;
                    }
                    int num=Integer.parseInt(stringNum);
                    newMerchandise.setPrice(price[j]);
                    newMerchandise.setPurchaseNumber(num);
                    int addOrChangeFlag=0;
                    for (Merchandise purchasedMerchandise:shoppingCart.shoppingCart){
                        if (newMerchandise.name.equals(purchasedMerchandise.name)){
                            purchasedMerchandise.purchaseNumber+=newMerchandise.purchaseNumber;
                            addOrChangeFlag=1;
                            break;
                        }
                    }
                    if (addOrChangeFlag==0){
                        shoppingCart.addPurchase(newMerchandise);
                    }
                    shoppingCart.summary+=num*price[j];
                }
            }
        }






        //request.setAttribute("shoppingCart",shoppingCart);
        RequestDispatcher rd=request.getRequestDispatcher("/ShoppingCart.jsp");
        rd.forward(request,response);

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String accountValue="";
        String passwordValue="";

        accountValue= (String) request.getSession().getAttribute("account");
        passwordValue= (String) request.getSession().getAttribute("password");
        if (accountValue==null||passwordValue==null) {
            response.sendRedirect("CheckUserServlet");
        }
        else {

            //read loginTime from database
            ArrayList<Long> loginTimes=new ArrayList<Long>();
            Connection connection=null;
            Statement statement=null;
            ResultSet resultSet=null;
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
                resultSet=statement.executeQuery("SELECT logintime FROM loginTime");
                while (resultSet.next()){
                    loginTimes.add(resultSet.getLong(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int loginNumber=loginTimes.size();
            long lastAccessedTime=0;
            if (loginNumber==1){
                lastAccessedTime=loginTimes.get(0);
            }else {
                lastAccessedTime=loginTimes.get(loginNumber-2);
            }


            request.getSession().setAttribute("loginNumber",loginNumber);
            //long lastAccessedTime = request.getSession().getCreationTime();
            request.getSession().setAttribute("lastAccessedTime", new Date(lastAccessedTime));
            response.sendRedirect("webShop.jsp");
        }
    }
}
