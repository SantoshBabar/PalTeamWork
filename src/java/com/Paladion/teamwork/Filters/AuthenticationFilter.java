/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.Filters;


import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author santosh.babar
 */
public class AuthenticationFilter implements Filter{
int i=0;
    @Override
    public void init(FilterConfig fc) throws ServletException {
            }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        
        String url;
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        url = req.getRequestURL().toString();
        HttpSession sess=req.getSession(false);
        
        if(url.contains("Login")||url.contains("ResetPassword")||url.contains("ForgotPassword")||!(url.endsWith(".do"))){
            fc.doFilter(request, response);
          }
        
        else{
            //Check for logged in user session
           
            if(sess==null||null==sess.getAttribute("Luser")){
                res.sendRedirect("Login.do");
                return;
            }
            else{
                //Assign CSRF token to request
                String token=sess.getAttribute("AntiCsrfToken").toString();
                req.setAttribute("csrfPreventionSalt", token);
                fc.doFilter(request, response);
            }
        }   
    }

    @Override
    public void destroy() {
  
    }
}
