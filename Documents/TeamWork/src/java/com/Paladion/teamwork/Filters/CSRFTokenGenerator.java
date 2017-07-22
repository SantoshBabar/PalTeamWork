/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.Filters;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CSRFTokenGenerator implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest)request;
        String url = httpReq.getRequestURL().toString();
        
        if(url.contains("Login")||url.contains("ResetPassword")||url.contains("ForgotPassword")||!(url.endsWith(".do"))){
            chain.doFilter(request, response);
          }
        
        else{
            HttpSession sess= httpReq.getSession(false);
            String token=sess.getAttribute("AntiCsrfToken").toString();
            httpReq.setAttribute("csrfPreventionSalt", token);
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}