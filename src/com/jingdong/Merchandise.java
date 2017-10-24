package com.jingdong;

public class Merchandise {
    String name;
    double price;
    int purchaseNumber;
    Merchandise(){
        name="";
        price=0;
        purchaseNumber=0;
    }
    void setName(String name){
        this.name=name;
    }
    void setPrice(double price){
        this.price=price;
    }
    void setPurchaseNumber(int purchaseNumber) {
        this.purchaseNumber=purchaseNumber;
    }

    @Override
    public String toString() {
        return name+"  price:"+price+"  number:"+purchaseNumber;
    }
}
