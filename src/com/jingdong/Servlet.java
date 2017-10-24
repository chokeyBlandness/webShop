package com.jingdong;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;

@javax.servlet.annotation.WebServlet(urlPatterns = {"/shop.do"})
public class Servlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String[] purchase=request.getParameterValues("purchase");
        if (purchase==null){
            return;
        }
        double sum=0;
        String[] phone={"iphone","HUAWEI","XiaoMi"};
        String[] phoneNumber={"phone1Number","phone2Number","phone3Number"};
        double[] price={4600,1900,1000};
        ShoppingCart shoppingCart=new ShoppingCart();

//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter out=response.getWriter();
//        out.println("<html><body>");

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
                    //out.print(purchase[i]+":"+num+"<br/>");

                    newMerchandise.setPrice(price[j]);
                    newMerchandise.setPurchaseNumber(num);
                    shoppingCart.addPurchase(newMerchandise);

                    sum+=num*price[j];
                }
            }
        }

        request.setAttribute("shoppingCart",shoppingCart);
        request.setAttribute("summary",sum);
        RequestDispatcher rd=request.getRequestDispatcher("/ShoppingCart.jsp");
        rd.forward(request,response);

//        out.println(sum);
//        out.print("</body></html>");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
