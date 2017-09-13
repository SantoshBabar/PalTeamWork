/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.DAO.TaskDAO;
import com.Paladion.teamwork.beans.TaskBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author user
 */

public class TaskServiceImpl implements TaskService{

@Autowired
@Qualifier(value="TaskDAO")
 TaskDAO TD;
	
	@Override
	public void addTask(TaskBean tb) {
		TD.addTaskDao(tb);
            }

	@Override
	public void editTask(TaskBean tb) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

	@Override
	public void deleteTask(TaskBean tb) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

	@Override
	public List<TaskBean> getAllTasks() {
		System.out.println("Inside TaskServiceImpl -getAllTaks method");
	        return  TD.getAllTasks();
            }
        
        @Override
        public boolean deleteTask(int id) {
                return TD.deleteTask(id);
            }
	
}
