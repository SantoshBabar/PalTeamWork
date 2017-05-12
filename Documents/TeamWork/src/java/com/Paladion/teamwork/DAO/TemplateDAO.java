/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.TaskBean;
import com.Paladion.teamwork.beans.TemplateBean;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface TemplateDAO {
    public void addTemplateDao(TemplateBean tempb);
    public void addTaskToTemplate(int[] taskid, int[] weight,int TempID);
    public List<TaskBean> getAllTasksforTemplate();
    
}
