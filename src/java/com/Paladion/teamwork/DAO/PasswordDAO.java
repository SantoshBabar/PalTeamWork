/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.UserDataBean;

/**
 *
 * @author santosh.babar
 */
public interface PasswordDAO {
    public boolean ForgotPassword(String email);
    public boolean ResetPassword(String otp, String email, String password);
    public void updatePassword(String password, String email);
}
