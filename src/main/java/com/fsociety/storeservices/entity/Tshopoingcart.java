package com.fsociety.storeservices.entity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name= "tshopoingcart")
public class Tshopoingcart implements Serializable{ 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "id_order")
	private Integer idOrder;
	@Column(name = "id_product")
	private Integer idProduct;
	@Column(name = "amount")
	private Integer amount;
	@Column(name = "status")
	private Boolean status;

	public Integer getId(){
		 return id;
	}

	public void setId(Integer id){
		  this.id=id;
	}

	public Integer getIdOrder(){
		 return idOrder;
	}

	public void setIdOrder(Integer idOrder){
		  this.idOrder=idOrder;
	}

	public Integer getIdProduct(){
		 return idProduct;
	}

	public void setIdProduct(Integer idProduct){
		  this.idProduct=idProduct;
	}

	public Integer getAmount(){
		 return amount;
	}

	public void setAmount(Integer amount){
		  this.amount=amount;
	}

	public Boolean getStatus(){
		 return status;
	}

	public void setStatus(Boolean status){
		  this.status=status;
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
