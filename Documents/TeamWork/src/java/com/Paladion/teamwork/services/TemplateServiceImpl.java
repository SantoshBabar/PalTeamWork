/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.DAO.TemplateDAO;
import com.Paladion.teamwork.beans.TemplateBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author user
 */
public class TemplateServiceImpl implements TemplateService{
	
@Autowired
@Qualifier(value="TemplateDAO")
 TemplateDAO TempD;

	@Override
	public void addTemplate(TemplateBean tempb) {
		TempD.addTemplateDao(tempb);
		}

	@Override
	public void editTemplate(TemplateBean tempb) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void deleteTemplate(TemplateBean tempb) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void addTaskToTemplate(int[] taskid, int[] weight, int TemplateID) {
		TempD.addTaskToTemplate(taskid, weight, TemplateID);
		
	}
	
}
