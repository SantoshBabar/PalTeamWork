/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import com.Paladion.teamwork.beans.TaskBean;
import com.Paladion.teamwork.beans.TemplateBean;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface TemplateService {
	
	public void addTemplate(TemplateBean tempb);
    public void editTemplate(TemplateBean tempb);
    public void deleteTemplate(TemplateBean tempb);
    public boolean addTaskToTemplate(MapTemplateTaskBean MTT);
    public List<TaskBean> getAllTasksforTemplate();
   public List<TemplateBean> getAllTemplates(); 
   public boolean deleteTemplate(int id);
   public List<MapTemplateTaskBean> getAllWeights(int tempID);
}
