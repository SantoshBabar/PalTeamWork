/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.DAO.ProjectDAO;
import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import com.Paladion.teamwork.beans.ProjectBean;
import com.Paladion.teamwork.beans.ProjectTransactionBean;
import java.util.List;
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



	@Override
	public List<ProjectBean> getAllProjects(int userid, String role) {
		return PD.getAllProjects(userid, role);
	}

    @Override
    public ProjectBean getProjectById(int id) {
    
        return PD.getProjectById(id); 
        
    }
    
    @Override
    public void insertProjectTransaction(List<ProjectTransactionBean> PTBList) {
    
         PD.insertProjectTransaction(PTBList); 
        
    }
    
    @Override
    public List<ProjectTransactionBean> getProjectTransaction(int projectid) {
    
         return PD.getProjectTransaction(projectid);
        
    }
    
       @Override
    public boolean updateTaskStatus(int transid, String status) {
    
          return PD.updateTaskStatus(transid, status);
        
    }
    
    
        @Override
    public boolean updateTaskStatus(int projid) {
    
          return PD.updateTaskStatus(projid);
        
    }
    
      @Override
    public boolean updateProjectStatus(int projid, String status) {
    
          return PD.updateProjectStatus(projid, status);
        
    }
    
    @Override
   public void updateProjectTransaction(List<ProjectTransactionBean> PTBList){
        PD.updateProjectTransaction(PTBList);
   }
	
}
