/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.DAO.PasswordDAO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author santosh.babar
 */
public class PasswordServiceImpl  implements PasswordService {
    
    @Autowired
    @Qualifier(value="PasswordDAO")
    PasswordDAO PD;
    
    @Override
    public boolean ForgotPassword(String email){
	    return PD.ForgotPassword(email);
    }
    
    @Override
    public boolean ResetPassword(String otp,String email,String password){
	    return PD.ResetPassword(otp,email,password);
    }
    
     @Override
    public void UpdatePassword(String password, String email){
	    PD.updatePassword(password,email);
    }
    
    @Override
    public boolean validatePassword(String password){
        Pattern pattern;
	Matcher matcher;
	String PASSWORD_PATTERN ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
	pattern = Pattern.compile(PASSWORD_PATTERN);
	matcher = pattern.matcher(password);
	return matcher.matches();
    }
}
