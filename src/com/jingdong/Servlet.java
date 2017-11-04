package com.jingdong;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

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

        String loginMessage="";
        loginMessage= (String) request.getSession().getAttribute("loginMessage");

        if (loginMessage==null||!loginMessage.equals("successfully login!")){
            response.sendRedirect("CheckUserServlet");
        }
        else {
            //read loginTime from database
            ArrayList<Long> loginTimes=new ArrayList<Long>();
            ConnectDatabase_webShop webShop=new ConnectDatabase_webShop();
            ResultSet resultSet=null;
            try {
                resultSet=webShop.getStatement().executeQuery("SELECT logintime FROM loginTime");
                while (resultSet.next()){
                    loginTimes.add(resultSet.getLong(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            webShop.closeConnect();
            //calculate login number and last login time
            int loginNumber=loginTimes.size();
            long lastAccessedTime=0;
            if (loginNumber==1){
                lastAccessedTime=loginTimes.get(0);
            }else {
                lastAccessedTime=loginTimes.get(loginNumber-2);
            }

            request.getSession().setAttribute("loginNumber",loginNumber);
            request.getSession().setAttribute("lastAccessedTime", new Date(lastAccessedTime));
            response.sendRedirect("webShop.jsp");
        }
    }
}
