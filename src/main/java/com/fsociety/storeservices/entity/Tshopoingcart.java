package com.fsociety.storeservices.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "tshopoingcart")
@NamedQueries(
        @NamedQuery(name = "Tshopoingcart.findShoppingCartByUserSateC",
                query = "SELECT s FROM Tshopoingcart s WHERE s.idOrder.id = :idOrder and s.idOrder.idUser.id = :idUser and s.status = true and s.idOrder.state = 'C'")
)
public class Tshopoingcart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id")
    private Torders idOrder;
    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private Tproducts idProduct;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "status")
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Torders getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Torders idOrder) {
        this.idOrder = idOrder;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }

}
