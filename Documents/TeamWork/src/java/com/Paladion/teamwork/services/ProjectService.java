/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import com.Paladion.teamwork.beans.ProjectBean;
import com.Paladion.teamwork.beans.ProjectTransactionBean;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface ProjectService {
    public void addProject(ProjectBean pb);
    public List<MapTemplateTaskBean> getAllWeights(int tempID);
    public List<ProjectBean> getAllProjects();
    public ProjectBean getProjectById(int id);
    public void insertProjectTransaction(List <ProjectTransactionBean> PTBList);
    public List<ProjectTransactionBean> getProjectTransaction(int projectid);
}
