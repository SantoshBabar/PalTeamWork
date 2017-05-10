/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.utils;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author user
 */
public class ManDaysCalculator {
	
    public  int getWorkingDays(Date startDate, Date endDate) {
    Calendar startCal = Calendar.getInstance();
    startCal.setTime(startDate);        

    Calendar endCal = Calendar.getInstance();
    endCal.setTime(endDate);

    int workDays = 1;

   
    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
        return 1;
    }

    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
        startCal.setTime(endDate);
        endCal.setTime(startDate);
    }

    do {
       
        startCal.add(Calendar.DAY_OF_MONTH, 1);
        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
           workDays++;
        }
    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); 

    return workDays;
}
	
}
