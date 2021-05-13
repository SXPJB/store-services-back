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
@Table(name= "tperson")
public class Tperson implements Serializable{ 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "paternal_surname")
	private String paternalSurname;
	@Column(name = "maternal_surname")
	private String maternalSurname;
	@Column(name = "rfc")
	private String rfc;
	@Column(name = "status")
	private Boolean status;

	public Integer getId(){
		 return id;
	}

	public void setId(Integer id){
		  this.id=id;
	}

	public String getName(){
		 return name;
	}

	public void setName(String name){
		  this.name=name;
	}

	public String getPaternalSurname(){
		 return paternalSurname;
	}

	public void setPaternalSurname(String paternalSurname){
		  this.paternalSurname=paternalSurname;
	}

	public String getMaternalSurname(){
		 return maternalSurname;
	}

	public void setMaternalSurname(String maternalSurname){
		  this.maternalSurname=maternalSurname;
	}

	public String getRfc(){
		 return rfc;
	}

	public void setRfc(String rfc){
		  this.rfc=rfc;
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
