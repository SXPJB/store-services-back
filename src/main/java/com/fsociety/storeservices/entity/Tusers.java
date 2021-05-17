package com.fsociety.storeservices.entity;
import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name= "tusers")
@NamedQueries(
		@NamedQuery( name = "Tusers.findByEmailAndPassword"
				, query = "SELECT u FROM  Tusers u where u.email = :email and u.password = :password and u.status=true")
)
public class Tusers implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_rol", referencedColumnName = "id")
	private Troles idRol;
	@Column(name = "id_person")
	private Integer idPerson;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "status")
	private Boolean status;

	public Integer getId(){
		 return id;
	}

	public void setId(Integer id){
		  this.id=id;
	}

	public Troles getIdRol(){
		 return idRol;
	}

	public void setIdRol(Troles idRol){
		  this.idRol=idRol;
	}

	public Integer getIdPerson(){
		 return idPerson;
	}

	public void setIdPerson(Integer idPerson){
		  this.idPerson=idPerson;
	}

	public String getEmail(){
		 return email;
	}

	public void setEmail(String email){
		  this.email=email;
	}

	public String getPassword(){
		 return password;
	}

	public void setPassword(String password){
		  this.password=password;
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
