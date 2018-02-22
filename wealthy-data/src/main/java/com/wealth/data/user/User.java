package com.wealth.data.user;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
	
@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Size(min=1, max=200, message="Error on insert fullname")
	@NotNull
	@Column(name="fullname")
	private String fullName; 

	@Size(min=1, max=50, message="Error on insert username")
	@NotNull
	@Column(name="username")
	private String userName;
	
	@Size(min=1, max=100, message="Error on insert email")
	@NotNull
	private String email;

	@Size(min=8, message="Error on insert password")
	private String password;

	@NotNull
	private Boolean term;
	
	@NotNull
	private Boolean newsletter;
	
	
	public Boolean getTerm() {
		return term;
	}

	public void setTerm(Boolean term) {
		this.term = term;
	}

	public Boolean getNewsletter() {
		return newsletter;
	}

	public void setNewsletter(Boolean newsletter) {
		this.newsletter = newsletter;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
