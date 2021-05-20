package com.fsociety.storeservices.entity.bo;

import com.fsociety.storeservices.entity.Tshopoingcart;

public class ShoppingBuilder {

    public static ShoppingCartBO fromEntity(Tshopoingcart tshopoingcart){
        ShoppingCartBO destination=new ShoppingCartBO();
        destination.setId(tshopoingcart.getId());
        destination.setIdProduct(tshopoingcart.getIdProduct());
        destination.setAmount(tshopoingcart.getAmount());
        return destination;
    }
}
