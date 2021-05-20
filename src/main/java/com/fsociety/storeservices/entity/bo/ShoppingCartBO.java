package com.fsociety.storeservices.entity.bo;

import com.fsociety.storeservices.entity.Tproducts;

public class ShoppingCartBO {
    private Integer id;
    private Tproducts idProduct;
    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tproducts getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Tproducts idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
