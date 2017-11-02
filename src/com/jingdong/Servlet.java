package com.jingdong;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import java.io.FileReader;
import java.io.IOException;
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
        //ShoppingCart shoppingCart=(ShoppingCart) getServletContext().getAttribute("shoppingCart");
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
//        Cookie[] cookies=request.getCookies();
//        if(cookies!=null){
//            for (Cookie cookie:cookies){
//                if (cookie.getName().equals("account")){
//                    accountValue=cookie.getValue();
//                }
//                if (cookie.getName().equals("password")){
//                    passwordValue=cookie.getValue();
//                }
//            }
//            if (accountValue.equals("111")&&passwordValue.equals("111")){
//                    request.getSession().setAttribute("account", accountValue);
//                    long lastAccessedTime = request.getSession().getLastAccessedTime();
//                    request.getSession().setAttribute("lastAccessedTime", new Date(lastAccessedTime));
//                    response.sendRedirect("webShop.jsp");
//            }
//            else {
//                response.sendRedirect("CheckUserServlet");
//            }
//        }
//        else {
//            response.sendRedirect("CheckUserServlet");
//        }

        accountValue= (String) request.getSession().getAttribute("account");
        passwordValue= (String) request.getSession().getAttribute("password");
        if (accountValue==null||passwordValue==null) {
            response.sendRedirect("CheckUserServlet");
        }
        else {
//            Integer loginNumber= (Integer) request.getSession().getAttribute("loginNumber");
//            if (loginNumber==null){
//                loginNumber=1;
//            }else {
//                loginNumber++;
//            }

            FileReader fileReader=new FileReader(valueOf(request.getSession().getAttribute("loginTimeFilePath")));
            String loginTimeFileString="";
            int loginTimeFileInt=fileReader.read();
            while (loginTimeFileInt!=-1){
                loginTimeFileString+=loginTimeFileInt;
                loginTimeFileInt=fileReader.read();
            }
            Integer loginNumber;
            loginNumber= Integer.valueOf(loginTimeFileString.substring(1,loginTimeFileString.indexOf("\n")));

            request.getSession().setAttribute("loginNumber",loginNumber);
            long lastAccessedTime = request.getSession().getCreationTime();
            request.getSession().setAttribute("lastAccessedTime", new Date(lastAccessedTime));
            response.sendRedirect("webShop.jsp");
        }
    }
}
