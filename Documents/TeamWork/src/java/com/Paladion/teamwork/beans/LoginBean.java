/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.beans;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "userstable",catalog="teamwork")
public class LoginBean implements Serializable {

    @Id
    String userid;
       
    String username,password,role;
    
        
        
           @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userid")
	public UserBean userinfo;

	public UserBean getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserBean userinfo) {
		this.userinfo = userinfo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	
	

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    
  
    
}
