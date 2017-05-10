/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.beans;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "CreateUserTable",catalog="teamwork")
public class CreateUserBean implements Serializable {

    @Id
    String userid;
    
    String name, email, phone,team,password,role;

	public String getUserid() {
		return userid;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getTeam() {
		return team;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
