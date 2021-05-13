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

import java.util.Date;
@Entity
@Table(name= "torders")
public class Torders implements Serializable{ 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "id_user")
	private Integer idUser;
	@Column(name = "total")
	private String total;
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

	public Integer getId(){
		 return id;
	}

	public void setId(Integer id){
		  this.id=id;
	}

	public Integer getIdUser(){
		 return idUser;
	}

	public void setIdUser(Integer idUser){
		  this.idUser=idUser;
	}

	public String getTotal(){
		 return total;
	}

	public void setTotal(String total){
		  this.total=total;
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

	public Date getModifiedAt(){
		 return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt){
		  this.modifiedAt=modifiedAt;
	}

	public Integer getModifiedBy(){
		 return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy){
		  this.modifiedBy=modifiedBy;
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
