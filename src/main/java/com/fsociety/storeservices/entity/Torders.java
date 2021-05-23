package com.fsociety.storeservices.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

@Entity
@Table(name = "torders")
@NamedQueries({
        @NamedQuery(name = "Torders.findOrderActiveByUserStateC", query = "SELECT o FROM Torders o WHERE o.id = :idOrder and o.status = true and o.idUser.id = :idUser and o.state='C'")
        , @NamedQuery(name = "Torders.findOrderByUserPage", query = "SELECT o FROM Torders o WHERE o.status = true and o.idUser.id = :idUser and o.state = 'T'")
        , @NamedQuery(name = "Torders.findAllStateT", query = "SELECT o FROM Torders o WHERE o.status = true and o.state = 'T'")
        , @NamedQuery(name = "Torders.findOrderByUserSateC", query = "SELECT o FROM Torders o WHERE o.status = true and o.idUser.id = :idUser and o.state = 'C'")
})
public class Torders implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private Tusers idUser;
    @Column(name = "destination_dir")
    private String destinationDir;
    @Column(name = "total")
    private Double total;
    @Column(name = "is_delivered")
    private Boolean isDelivered;
    @Column(name = "state")
    private String state;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "cerated_at")
    private Date ceratedAt;
    @Column(name = "modified_at")
    private Date modifiedAt;
    @Column(name = "modified_by")
    private Integer modifiedBy;
    @Column(name = "status")
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tusers getIdUser() {
        return idUser;
    }

    public void setIdUser(Tusers idUser) {
        this.idUser = idUser;
    }

    public String getDestinationDir() {
        return destinationDir;
    }

    public void setDestinationDir(String destinationDir) {
        this.destinationDir = destinationDir;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getDelivered() {
        return isDelivered;
    }

    public void setDelivered(Boolean delivered) {
        isDelivered = delivered;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCeratedAt() {
        return ceratedAt;
    }

    public void setCeratedAt(Date ceratedAt) {
        this.ceratedAt = ceratedAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
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
