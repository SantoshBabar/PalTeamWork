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
@Table(name = "tasks",catalog="teamwork")
public class TaskBean implements Serializable{
	
@Id
@GenericGenerator(name="gen",strategy="increment")
@GeneratedValue(generator="gen")
@Column(name = "taskid", unique = true, nullable = false, precision = 15, scale = 0)
	long taskid;	 
	public void setTaskid(long taskid) {
		this.taskid = taskid;
	}
	
	public long getTaskid() {
		return taskid;
	}
	
	@Column(name = "taskname")
	String taskname;

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	
	public String getTaskname() {
		return taskname;
	}

	@Column(name = "Description")
	String Description;
	
	public void setDescription(String Description) {
		this.Description = Description;
	}

	public String getDescription() {
		return Description;
	}
    
}
