package com.fsociety.storeservices.entity.bo;

import com.fsociety.storeservices.entity.Torders;

import java.util.List;

public class OrderFullBuilder {

    public static OrdersFullBO getOrderFull(Torders torders, List<ShoppingCartBO> shopoingcartList){

        OrdersFullBO destination = new OrdersFullBO();
        destination.setId(torders.getId());
        destination.setDestinationDir(torders.getDestinationDir());
        destination.setTotal(torders.getTotal());
        destination.setSate(torders.getState());
        destination.setDelivered(torders.getDelivered());
        destination.setShopoingcartList(shopoingcartList);
        return destination;
    }
}
