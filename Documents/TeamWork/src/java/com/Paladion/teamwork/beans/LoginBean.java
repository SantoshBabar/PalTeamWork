/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.beans;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Generated;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "userstable",catalog="teamwork")
public class LoginBean implements Serializable {

    @Id 
    private String username;
    private String password;
    private String role;
    private String email;
    private String OTP;
     
@OneToOne
@JoinColumn(name = "userId")
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }
    
    

}
