/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.beans.TemplateBean;

/**
 *
 * @author Administrator
 */
public interface TemplateService {
	
	public void addTemplate(TemplateBean tempb);
    public void editTemplate(TemplateBean tempb);
    public void deleteTemplate(TemplateBean tempb);
    public void addTaskToTemplate(int []taskid, int[] weight, int Tempid);
    
}
