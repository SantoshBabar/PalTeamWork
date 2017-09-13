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
@Table(name = "template_task",catalog="teamwork")
public class MapTemplateTaskBean implements Serializable{
	
        @Id
	int templateid;

	public int getTemplateid() {
		return templateid;
	}

	public void setTemplateid(int templateid) {
		this.templateid = templateid;
	}
	
	@Id
	int taskid;

	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}
	
	
	int weight;

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
        String taskname;

        public String getTaskname() {
        return taskname;
        }

        public void setTaskname(String taskname) {
        this.taskname = taskname;
        } 
        
      	
}
