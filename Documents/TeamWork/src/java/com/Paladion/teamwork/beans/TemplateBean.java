/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "templates",catalog="teamwork")
public class TemplateBean implements Serializable{
      
@Id
@GenericGenerator(name="gen",strategy="increment")
@GeneratedValue(generator="gen")
@Column(name = "templateid", unique = true, nullable = false, precision = 15, scale = 0)
	int templateid;	 
           public void setTemplateid(int templateid) {
		this.templateid = templateid;
	}
	
	public int getTemplateid() {
		return templateid;
	}

	
	@Column(name = "templatename")
	String templatename;
	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}
	
	
		public String getTemplatename() {
		return templatename;
	}

	@Column(name = "templateDesc")	
	String templateDesc;
	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}
	
	public String getTemplateDesc() {
		return templateDesc;
	}
	 
	 
}
