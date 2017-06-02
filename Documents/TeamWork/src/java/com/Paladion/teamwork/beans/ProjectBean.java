/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "projects",catalog="teamwork")
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
	
	@Column(name = "mandays")
	int mandays;

	public int getMandays() {
		return mandays;
	}

	public void setMandays(int mandays) {
		this.mandays = mandays;
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
	
//	@Column(name = "engineer")
//	String engineer;
//
//	public String getEngineer() {
//		return engineer;
//	}
//
//	public void setEngineer(String engineer) {
//		this.engineer = engineer;
//	}
	
	@Column(name = "startdate")
	@DateTimeFormat (pattern="dd/MM/yyyy")
	Date startdate;
        
        
	public Date getStartdate() throws ParseException {
           
		return startdate;
	}

	public void setStartdate(Date startdate) throws ParseException {
		
            this.startdate = startdate;
	}
	
	@Column(name = "enddate")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	Date enddate;

	public Date getEnddate() throws ParseException {
     
		return enddate;
	}

	public void setEnddate(Date enddate) throws ParseException {
		
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
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "projectid")
	@JoinTable(name = "project_transaction", joinColumns = { @JoinColumn(name = "projectid") })
	public Set <ProjectTransactionBean> projectstatusbean;

	public Set<ProjectTransactionBean>Projectstatusbean() {
		return projectstatusbean;
	}

	public void setProjectstatusbean(Set<ProjectTransactionBean>projectstatusbean ) {
		this.projectstatusbean = projectstatusbean;
	}


	
}
