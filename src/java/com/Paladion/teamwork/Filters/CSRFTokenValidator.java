/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.Filters;

/**
 *
 * @author sumukh.r
 */
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CSRFTokenValidator implements Filter  {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse res=(HttpServletResponse)response;
        String url = httpReq.getRequestURL().toString();
        String method=httpReq.getMethod();
       

        if(url.contains("Login")||url.contains("ResetPassword")||url.contains("ForgotPassword")||method.equalsIgnoreCase("get")||!(url.endsWith(".do"))){
            chain.doFilter(request, response);
          }
        
        else{            
            HttpSession sess= httpReq.getSession(false);
            String token=sess.getAttribute("AntiCsrfToken").toString();
            String reqToken =(String)httpReq.getParameter("AntiCSRFToken");
            
            if(reqToken!=null&&reqToken.equals(token)){
                chain.doFilter(request, response);
            }
             else
            {
                System.out.println("Invalid CSRF Token ------------\n");
                sess.invalidate();
                res.sendRedirect("Login.do");
            }
        }
     }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}