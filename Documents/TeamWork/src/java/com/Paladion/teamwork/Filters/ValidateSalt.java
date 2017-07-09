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
import com.google.common.cache.Cache;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

public class ValidateSalt implements Filter  {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        // Assume its HTTP
        HttpServletRequest httpReq = (HttpServletRequest) request;

        //String salt = (String) httpReq.getSession().getAttribute("csrfPreventionSalt");
        String salt =(String) httpReq.getAttribute("csrfPreventionSalt");
        System.out.println("I am in ValidateSalt : salt: "+salt);

     // Validate that the salt is in the cache
        @SuppressWarnings("unchecked")
        Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>)
            httpReq.getSession().getAttribute("csrfPreventionSaltCache");


        if(csrfPreventionSaltCache !=null && salt !=null && csrfPreventionSaltCache.getIfPresent(salt)!=null)
        {
            // If the salt is in the cache, we move on
            chain.doFilter(request, response);
        }
        else
        {
            // Otherwise we throw an exception aborting the request flow
            throw new ServletException("Potential CSRF detected!! Inform a scary sysadmin ASAP.");
        }

    }

    public void init(FilterConfig arg0) throws ServletException {

    }
    public void destroy() {

    }

}
