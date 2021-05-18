package com.fsociety.storeservices.entity;
import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
@Entity
@Table(name= "ttraking")
public class Ttraking implements Serializable{ 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_order",referencedColumnName = "id")
	private Torders idOrder;
	@Column(name = "direction")
	private String direction;
	@Column(name = "created_by")
	private Integer createdBy;
	@Column(name = "cerated_at")
	private Date ceratedAt;
	@Column(name = "status")
	private Boolean status;

	public Integer getId(){
		 return id;
	}

	public void setId(Integer id){
		  this.id=id;
	}

	public Torders getIdOrder(){
		 return idOrder;
	}

	public void setIdOrder(Torders idOrder){
		  this.idOrder=idOrder;
	}

	public String getDirection(){
		 return direction;
	}

	public void setDirection(String direction){
		  this.direction=direction;
	}

	public Integer getCreatedBy(){
		 return createdBy;
	}

	public void setCreatedBy(Integer createdBy){
		  this.createdBy=createdBy;
	}

	public Date getCeratedAt(){
		 return ceratedAt;
	}

	public void setCeratedAt(Date ceratedAt){
		  this.ceratedAt=ceratedAt;
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
