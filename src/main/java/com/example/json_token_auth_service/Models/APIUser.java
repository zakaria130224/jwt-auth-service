package com.example.json_token_auth_service.Models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@ToString
@Entity
@Table(name = "API_USERS",uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "email"})})
public class APIUser extends BaseEntity {


	@Column(name = "userName")
	String userName;

	@Column(name = "password")
	String password;

	@Column(name = "plainPassword")
	String plainPassword;

	@Column(name = "email")
	String email;

	@Column(name = "role")
	String role="USER";



	public APIUser(){}

	public APIUser(String username, String password, String plainPassword, String email) {
		this.userName = username;
		this.password = password;
		this.plainPassword = plainPassword;
		this.email = email;
	}

	public APIUser(String userName, String password, String plainPassword, String email, String role) {
		this.userName = userName;
		this.password = password;
		this.plainPassword = plainPassword;
		this.email = email;
		this.role = role;
	}
}
