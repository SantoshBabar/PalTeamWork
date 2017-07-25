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
import com.Paladion.teamwork.beans.UserDataBean;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

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

    @Override
    public int[] getProjectsCount(javax.servlet.http.HttpServletRequest req) {
         HttpSession sess= req.getSession(false);
        UserDataBean sessuser=(UserDataBean) sess.getAttribute("Luser");
	ModelAndView result=new ModelAndView("Welcome");
        List<ProjectBean> PBList=(List<ProjectBean>)this.getAllProjects(sessuser.getUserid(), sessuser.getRole());
        int total_projects=PBList.size();
        int project_new=0;
        int project_progress=0;
        int project_completed=0;
        for(ProjectBean PB:PBList){
            if(PB.getStatus().equalsIgnoreCase("new")){
                project_new++;
               
            }
            if(PB.getStatus().equalsIgnoreCase("progress")){
                project_progress++;
              
            }
            if(PB.getStatus().equalsIgnoreCase("completed")){
                project_completed++;
              
            }
        }
        System.out.println("No of completed projects : "+project_completed);
        System.out.println("No of on going projects : "+project_progress);
        System.out.println("No of new projects : "+project_new);
         int [] counts = new int[4];
         counts[0]=total_projects;
         counts[1]=project_completed;
         counts[2]=project_progress;
         counts[3]=project_new;
         
         return counts;
    }
	
}
