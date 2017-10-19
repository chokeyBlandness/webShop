package com.jingdong;

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
        String[] phone={"phone1","phone2","phone3"};
        String[] phoneNumber={"phone1Number","phone2Number","phone3Number"};
        double[] price={4600,1900,1000};

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.println("<html><body>");

        for (int i=0;i<purchase.length;i++){
            for (int j=0;j<phone.length;j++){
                if (purchase[i].matches(phone[j])){
                    String stringNum=request.getParameter(phoneNumber[i]);
                    if(stringNum==null){
                        return;
                    }
                    int num=Integer.parseInt(stringNum);
                    out.print(purchase[i]+":"+num+"<br/>");
                    sum+=num*price[i];
                }
            }
        }

        out.println(sum);
        out.print("</body></html>");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
