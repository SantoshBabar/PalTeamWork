/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.DAO.ProjectDAO;
import com.Paladion.teamwork.beans.ProjectBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Administrator
 */
public class ProjectServiceImpl implements ProjectService {

	@Autowired
@Qualifier(value="ProjectDAO")
 ProjectDAO PD;
	
	@Override
	public void addProject(ProjectBean pb) {
		PD.addProjectDao(pb);
		
		}
    
}
