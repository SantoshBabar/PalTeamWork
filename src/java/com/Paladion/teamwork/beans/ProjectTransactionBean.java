/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.beans;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author root
 */
@Entity
@Table(name = "projects_transaction",catalog="teamwork")
public class ProjectTransactionBean {
    
    @Id
    @GenericGenerator(name="gen",strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name = "transid", unique = true, nullable = false, precision = 15, scale = 0)        
    int transid;
    int projectid;
    String taskname;
    int userid;
    float taskhours;
    String status;
    Date taskstartdate, taskenddate;
    float taskdays;
    String engname;

    public int getTransid() {
        return transid;
    }

    public void setTransid(int transid) {
        this.transid = transid;
    }
    
    

    public String getEngname() {
        return engname;
    }

    public void setEngname(String engname) {
        this.engname = engname;
    }
        
        
        public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public float getTaskhours() {
		return taskhours;
	}

	public void setTaskhours(float taskhours) {
		this.taskhours = taskhours;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
  

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public Date getTaskstartdate() {
        return taskstartdate;
    }

    public void setTaskstartdate(Calendar taskstartdate) {
        
        this.taskstartdate = taskstartdate.getTime();
    }

    public Date getTaskenddate() {
        return taskenddate;
    }

    public void setTaskenddate(Date taskenddate) {
        this.taskenddate = taskenddate;
    }

    public float getTaskdays() {
        return taskdays;
    }

    public void setTaskdays(float taskdays) {
        this.taskdays = taskdays;
    }

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}
    
    
}
