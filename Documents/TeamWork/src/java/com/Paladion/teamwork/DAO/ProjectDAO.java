/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.ProjectBean;
import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface ProjectDAO {
   public void addProjectDao(ProjectBean pb);
   public List<MapTemplateTaskBean> getAllWeights(int tempID);
   public List<ProjectBean> getAllProjects();
}
