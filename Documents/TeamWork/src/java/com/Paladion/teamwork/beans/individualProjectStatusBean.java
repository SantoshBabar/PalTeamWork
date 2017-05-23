/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.beans;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author root
 */
public class individualProjectStatusBean {
    
    String taskname;

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
    Date taskstartdate, taskenddate;
    float taskdays;
    
}
