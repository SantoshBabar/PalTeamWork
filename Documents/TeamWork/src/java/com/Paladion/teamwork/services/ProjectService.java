/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.beans.ProjectBean;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface ProjectService {
    public void addProject(ProjectBean pb);
    public void getAllWeights(int tempID);
    public List<ProjectBean> getAllProjects();
    public List<Object> getProjectById(int id);
}
