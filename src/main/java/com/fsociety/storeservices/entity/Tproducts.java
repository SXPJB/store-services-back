package com.fsociety.storeservices.entity;
import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
@Entity
@Table(name= "tproducts")
@NamedQueries({
				@NamedQuery(name = "Tproducts.findActivePageOrder",query = "SELECT p FROM Tproducts p WHERE p.status = true ")
})
public class Tproducts implements Serializable{ 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "price")
	private Double price;
	@Column(name = "image_1")
	private String image1;
	@Column(name = "inventary")
	private Integer inventary;
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

	public String getTitle(){
		 return title;
	}

	public void setTitle(String title){
		  this.title=title;
	}

	public String getDescription(){
		 return description;
	}

	public void setDescription(String description){
		  this.description=description;
	}

	public Double getPrice(){
		 return price;
	}

	public void setPrice(Double price){
		  this.price=price;
	}

	public String getImage1(){
		 return image1;
	}

	public void setImage1(String image1){
		  this.image1=image1;
	}

	public Integer getInventary(){
		 return inventary;
	}

	public void setInventary(Integer inventary){
		  this.inventary=inventary;
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
