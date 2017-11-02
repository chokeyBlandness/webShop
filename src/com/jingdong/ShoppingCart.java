package com.jingdong;

import java.util.ArrayList;

public class ShoppingCart {
    ArrayList<Merchandise> shoppingCart;
    double summary;
    ShoppingCart(){
        shoppingCart=new ArrayList<Merchandise>();
        summary=0;
    }
    void addPurchase(Merchandise merchandise){
        shoppingCart.add(merchandise);
    }

    @Override
    public String toString() {
        String shopString="";
        for (Merchandise merchandise:shoppingCart){
            shopString+=merchandise.toString()+"<br/>";
        }
        shopString+=String.valueOf(summary);
        return shopString;
    }
}
