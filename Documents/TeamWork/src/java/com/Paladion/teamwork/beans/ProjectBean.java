/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Tasks",catalog="teamwork")
public class ProjectBean implements Serializable{           
           
@Id
@GenericGenerator(name="gen",strategy="increment")
@GeneratedValue(generator="gen")
@Column(name = "projectid", unique = true, nullable = false, precision = 15, scale = 0)
	int projectid;	 

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}
	
	@Column(name = "opid")
	String opid;

	public String getOpid() {
		return opid;
	}

	public void setOpid(String opid) {
		this.opid = opid;
	}
	
	@Column(name = "projectname")
	String projectname;

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
    
	@Column(name = "lead")
	String lead;

	public String getLead() {
		return lead;
	}

	public void setLead(String lead) {
		this.lead = lead;
	}
	
	@Column(name = "engineer")
	String engineer;

	public String getEngineer() {
		return engineer;
	}

	public void setEngineer(String engineer) {
		this.engineer = engineer;
	}
	
	@Column(name = "startdate")
	@DateTimeFormat (pattern="dd.MM.yyyy")
	Date startdate;

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	
	@Column(name = "enddate")
	@DateTimeFormat(pattern="dd.MM.yyyy")
	Date enddate;

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	@Column(name = "templateid")
	int templateid;

	public int getTemplateid() {
		return templateid;
	}

	public void setTemplateid(int templateid) {
		this.templateid = templateid;
	}
	
}
