package com.jingdong;

import java.util.ArrayList;

public class ShoppingCart {
    ArrayList<Merchandise> shoppingCart;
    ShoppingCart(){
        shoppingCart=new ArrayList<Merchandise>();
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
        return shopString;
    }
}
